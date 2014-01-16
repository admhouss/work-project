package by.premiya.olga.project.entity;

import by.premiya.olga.project.util.auth.UserRole;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author vabramov
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private static final long serialVersionUID = 6749777977259143560L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "USER_ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.ROLE_ADMINISTRATOR;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "SECOND_NAME", nullable = false)
    private String lastName;

    public User() {
    }

    public User(String login, String passwordHash, UserRole role, String firstName, String lastName) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String email) {
        this.login = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return "{"
                + id + ","
                + login + ","
                + passwordHash + ","
                + role + ","
                + firstName + ","
                + lastName
                + "}";
    }

    @Override
    public boolean equals(Object userObj) {
        if (this == userObj) {
            return true;
        } else if (userObj == null) {
            return false;
        } else if (userObj instanceof User) {
            User user = (User) userObj;

            return id == user.id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}