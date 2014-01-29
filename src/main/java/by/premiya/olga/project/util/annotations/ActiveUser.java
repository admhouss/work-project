package by.premiya.olga.project.util.annotations;

import by.premiya.olga.project.util.auth.UserRole;

import java.lang.annotation.*;

/**
 * This annotation was made for retrieving registered users from Spring context.
 *
 * @see java.security.Principal
 *
 * @author Vlad Abramov
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActiveUser {

    UserRole value() default UserRole.ROLE_ADMINISTRATOR;
}
