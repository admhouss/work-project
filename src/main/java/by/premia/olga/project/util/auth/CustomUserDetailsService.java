package by.premia.olga.project.util.auth;

import by.premia.olga.project.dao.UserDao;
import by.premia.olga.project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author vabramov
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private boolean superAdminSet = false;

    @Override
    public CustomUserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        if (!superAdminSet) {
            addSuperAdmin();
        }
        by.premia.olga.project.entity.User domainUser = userDao.getUserByLogin(userEmail);

        if (domainUser == null) {

            throw new UsernameNotFoundException(userEmail);
        }

        return new CustomUserDetails(domainUser, getAuthorities(domainUser.getRole()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(UserRole role) {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new GrantedAuthorityImpl(role.toString()));
        return authList;
    }


    private void addSuperAdmin() {
        String login = "olga-admin";
        if (userDao.getUserByLogin(login) == null) {
            userDao.addUser(new User(login, passwordEncoder.encodePassword("project-1",login), UserRole.ROLE_ADMINISTRATOR, "Ольга", "Климушка"));
        }
        superAdminSet = true;
    }
}
