package by.premiya.olga.project.util.auth.basic;

import by.premiya.olga.project.util.Utils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Processes a HTTP request's BASIC authorization headers, putting the result into the
 * <code>SecurityContextHolder</code>.
 * <p/>
 * <p/>
 * For a detailed background on what this filter is designed to process, refer to
 * <a href="http://www.faqs.org/rfcs/rfc1945.html">RFC 1945, Section 11.1</a>. Any realm name presented in
 * the HTTP request is ignored.
 * <p/>
 * <p/>
 * In summary, this filter is responsible for processing any request that has a HTTP request header of
 * <code>Authorization</code> with an authentication scheme of <code>Basic</code> and a Base64-encoded
 * <code>username:password</code> token. For example, to authenticate user "Aladdin" with password "open sesame" the
 * following header would be presented:
 * <pre>
 *
 * Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==
 * </pre>
 * <p/>
 * <p/>
 * This filter can be used to provide BASIC authentication services to both remoting protocol clients (such as
 * Hessian and SOAP) as well as standard user agents (such as Internet Explorer and Netscape).
 * <p/>
 * If authentication is successful, the resulting {@link Authentication} object will be placed into the
 * <code>SecurityContextHolder</code>.
 * <p/>
 * <p/>
 * If authentication fails and <code>ignoreFailure</code> is <code>false</code> (the default), an {@link
 * AuthenticationEntryPoint} implementation is called (unless the <tt>ignoreFailure</tt> property is set to
 * <tt>true</tt>). Usually this should be {@link org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint}, which will prompt the user to
 * authenticate again via BASIC authentication.
 * <p/>
 * <p/>
 * Basic authentication is an attractive protocol because it is simple and widely deployed. However, it still
 * transmits a password in clear text and as such is undesirable in many situations. Digest authentication is also
 * provided by Spring Security and should be used instead of Basic authentication wherever possible. See {@link
 * org.springframework.security.web.authentication.www.DigestAuthenticationFilter}.
 * <p/>
 * Note that if a {@link RememberMeServices} is set, this filter will automatically send back remember-me
 * details to the client. Therefore, subsequent requests will not need to present a BASIC authentication header as
 * they will be authenticated using the remember-me mechanism.
 *
 * @author Vlad
 */
public class CustomBasicAuthenticationFilter extends GenericFilterBean {

    //~ Instance fields ================================================================================================

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
    private AuthenticationEntryPoint authenticationEntryPoint;
    private AuthenticationManager authenticationManager;
    private RememberMeServices rememberMeServices = new NullRememberMeServices();
    private boolean ignoreFailure = false;
    private String credentialsCharset = "UTF-8";

    /**
     * @deprecated Use constructor injection
     */
    public CustomBasicAuthenticationFilter() {
    }

    /**
     * Creates an instance which will authenticate against the supplied {@code AuthenticationManager}
     * and which will ignore failed authentication attempts, allowing the request to proceed down the filter chain.
     *
     * @param authenticationManager the bean to submit authentication requests to
     */
    public CustomBasicAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        ignoreFailure = true;
    }

    /**
     * Creates an instance which will authenticate against the supplied {@code AuthenticationManager} and
     * use the supplied {@code AuthenticationEntryPoint} to handle authentication failures.
     *
     * @param authenticationManager    the bean to submit authentication requests to
     * @param authenticationEntryPoint will be invoked when authentication fails. Typically an instance of
     *                                 {@link org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint}.
     */
    public CustomBasicAuthenticationFilter(AuthenticationManager authenticationManager,
                                           AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    //~ Methods ========================================================================================================

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.authenticationManager, "An AuthenticationManager is required");

        if (!isIgnoreFailure()) {
            Assert.notNull(this.authenticationEntryPoint, "An AuthenticationEntryPoint is required");
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        final boolean debug = logger.isDebugEnabled();
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic ")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String[] tokens = extractAndDecodeHeader(header, request);
            assert tokens.length == 2;

            String username = tokens[0];

//            if (Utils.hasCookie(request, "loc") && !Utils.hasCookie(request, "lic")) {
//
//                processAuthenticationException(request, response, new AuthenticationException("Must be authorised") {
//                });
//                return;
//            }

//            if (debug) {
//                logger.debug("Basic Authentication Authorization header found for user '" + username + "'");
//            }

            if (authenticationIsRequired(username)) {
//                if (Utils.hasCookie(request, "loc")) {
//                    Utils.removeLogoutCookie(response);
//                    processAuthenticationException(request, response, new AuthenticationException("Must be authorised") {
//                        private static final long serialVersionUID = 5930005044218950413L;
//                    });
//                }

                UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(username, tokens[1]);
                authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
                Authentication authResult = authenticationManager.authenticate(authRequest);

                if (debug) {
                    logger.debug("Authentication success: " + authResult);
                }

                SecurityContextHolder.getContext().setAuthentication(authResult);

                rememberMeServices.loginSuccess(request, response, authResult);

                onSuccessfulAuthentication(request, response, authResult);
            }

        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();

            if (debug) {
                logger.debug("Authentication request for failed: " + failed);
            }

            rememberMeServices.loginFail(request, response);

            onUnsuccessfulAuthentication(request, response, failed);

            if (ignoreFailure) {
                chain.doFilter(request, response);
            } else {
                authenticationEntryPoint.commence(request, response, failed);
            }

            return;
        }

        chain.doFilter(request, response);
    }

    private void processAuthenticationException(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();

//        Utils.removeLogoutCookie(response);

        if (logger.isDebugEnabled()) {
            logger.debug("Set logout cookie and send basic authorization");
        }

        authenticationEntryPoint.commence(request, response, failed);
    }

    /**
     * Decodes the header into a username and password.
     *
     * @throws org.springframework.security.authentication.BadCredentialsException
     *          if the Basic header is not present or is not valid Base64
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {

        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, getCredentialsCharset(request));

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }

    private boolean authenticationIsRequired(String username) {
        // Only reauthenticate if username doesn't match SecurityContextHolder and user isn't authenticated
        // (see SEC-53)
        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

        if (existingAuth == null || !existingAuth.isAuthenticated()) {
            return true;
        }

        // Limit username comparison to providers which use usernames (ie UsernamePasswordAuthenticationToken)
        // (see SEC-348)

        if (existingAuth instanceof UsernamePasswordAuthenticationToken && !existingAuth.getName().equals(username)) {
            return true;
        }

        // Handle unusual condition where an AnonymousAuthenticationToken is already present
        // This shouldn't happen very often, as BasicProcessingFitler is meant to be earlier in the filter
        // chain than AnonymousAuthenticationFilter. Nevertheless, presence of both an AnonymousAuthenticationToken
        // together with a BASIC authentication request header should indicate reauthentication using the
        // BASIC protocol is desirable. This behaviour is also consistent with that provided by form and digest,
        // both of which force re-authentication if the respective header is detected (and in doing so replace
        // any existing AnonymousAuthenticationToken). See SEC-610.
        return existingAuth instanceof AnonymousAuthenticationToken;

    }

    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              Authentication authResult) throws IOException {
    }

    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException failed) throws IOException {
    }

    protected AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return authenticationEntryPoint;
    }

    /**
     * @deprecated Use constructor injection
     */
    @Deprecated
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    protected AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    /**
     * @deprecated Use constructor injection
     */
    @Deprecated
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    protected boolean isIgnoreFailure() {
        return ignoreFailure;
    }

    /**
     * @deprecated Use the constructor which takes a single AuthenticationManager parameter
     */
    @Deprecated
    public void setIgnoreFailure(boolean ignoreFailure) {
        this.ignoreFailure = ignoreFailure;
    }

    public void setAuthenticationDetailsSource(AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices) {
        Assert.notNull(rememberMeServices, "rememberMeServices cannot be null");
        this.rememberMeServices = rememberMeServices;
    }

    public void setCredentialsCharset(String credentialsCharset) {
        Assert.hasText(credentialsCharset, "credentialsCharset cannot be null or empty");
        this.credentialsCharset = credentialsCharset;
    }

    protected String getCredentialsCharset(HttpServletRequest httpRequest) {
        return credentialsCharset;
    }
}