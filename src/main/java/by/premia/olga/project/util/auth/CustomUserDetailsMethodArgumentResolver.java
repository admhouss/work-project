package by.premia.olga.project.util.auth;

import by.premia.olga.project.entity.User;
import by.premia.olga.project.util.annotations.ActiveUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.security.Principal;

/**
 * @author vabramov
 */
@Component
public class CustomUserDetailsMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(ActiveUser.class) != null
                && methodParameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        if (this.supportsParameter(methodParameter)) {
            Principal principal = nativeWebRequest.getUserPrincipal();
            return ((CustomUserDetails) ((Authentication) principal).getPrincipal()).getUser();
        } else {
            return WebArgumentResolver.UNRESOLVED;
        }
    }
}
