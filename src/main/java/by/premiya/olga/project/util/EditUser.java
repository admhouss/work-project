package by.premiya.olga.project.util;

import java.io.Serializable;

/**
 * @author Vlad Abramov
 */
public class EditUser implements Serializable {
    private static final long serialVersionUID = -5837265027542846948L;
    private boolean loginIsFree;
    private boolean success = false;
    private String login;
    private String firstName;
    private String lastName;
    private String newLogin;
    private String newPassword;
    private String newFirstName;
    private String newLastName;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isLoginIsFree() {
        return loginIsFree;
    }

    public void setLoginIsFree(boolean loginIsFree) {
        this.loginIsFree = loginIsFree;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getNewLogin() {
        return newLogin;
    }

    public void setNewLogin(String newLogin) {
        this.newLogin = newLogin;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }
}
