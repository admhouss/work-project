package by.premia.olga.project.util.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author vabramov
 */
public class CustomUserDetails extends User {

    private by.premia.olga.project.entity.User user;

    public CustomUserDetails(by.premia.olga.project.entity.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getLogin(), user.getPasswordHash(), true, true, true, true, authorities);
        this.user = user;
    }

    public by.premia.olga.project.entity.User getUser() {
        return user;
    }
}
