package by.premiya.olga.project.util.auth;

import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.util.annotations.ActiveUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    private Object resolveActiveUserArgument(MethodParameter methodParameter, NativeWebRequest nativeWebRequest) {
        UserRole role = methodParameter.getParameterAnnotation(ActiveUser.class).value();
        CustomUserDetails userDetails = getUserDetails(nativeWebRequest);
        if (userDetails == null)
            return WebArgumentResolver.UNRESOLVED;
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority(role.toString()))) {
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
