package by.premiya.olga.project.util.auth;

import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.service.UserService;
import by.premiya.olga.project.util.annotations.ActiveUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;
import java.security.Principal;

/**
 * @author vabramov
 */
@Component
public class CustomUserDetailsMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return supportsAnnotation(methodParameter, ActiveUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        if (this.supportsParameter(methodParameter)) {
            if (methodParameter.getParameterAnnotation(ActiveUser.class) != null) {
                return resolveActiveUserArgument(methodParameter, nativeWebRequest);
            }
        }
        return WebArgumentResolver.UNRESOLVED;
    }

    private User resolveActiveUserArgument(MethodParameter methodParameter, NativeWebRequest nativeWebRequest) {
        UserRole role = methodParameter.getParameterAnnotation(ActiveUser.class).withRole();
        CustomUserDetails userDetails = getUserDetails(nativeWebRequest);
        if (userDetails.getAuthorities().contains(new GrantedAuthorityImpl(role.toString()))) {
            return userDetails.getUser();
        } else {
            throw new AccessDeniedException("Access is denied");
        }
    }

    private <T extends Annotation> boolean supportsAnnotation(MethodParameter methodParameter, Class<T> annotationClass) {
        return methodParameter.getParameterAnnotation(annotationClass) != null
                && methodParameter.getParameterType().equals(User.class);
    }

    private CustomUserDetails getUserDetails(NativeWebRequest nativeWebRequest) {
        Principal principal = nativeWebRequest.getUserPrincipal();
        return (CustomUserDetails) ((Authentication) principal).getPrincipal();
    }
}
