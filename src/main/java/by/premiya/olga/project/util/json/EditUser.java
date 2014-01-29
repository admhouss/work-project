package by.premiya.olga.project.util.json;

import by.premiya.olga.project.entity.User;
import by.premiya.olga.project.util.auth.UserRole;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author Vlad Abramov
 */
public class EditUser implements Externalizable {
    private static final long serialVersionUID = 8356606643899956949L;
    private boolean loginIsFree = true;
    private boolean success = false;
    private String login;
    private String firstName;
    private String lastName;
    private String newLogin;
    private String newPassword;
    private String newFirstName;
    private String newLastName;
    private String role;

    public EditUser() {
    }

    public EditUser(User user) {
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserRole(UserRole role) {
        switch (role) {
            case ROLE_ADMINISTRATOR: this.role = "1"; break;
            case ROLE_SUPERVISOR: this.role = "2"; break;
        }
    }

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

    @Override
    public String toString() {
        return "EditUser[" +
                "loginIsFree=" + loginIsFree +
                ", success=" + success +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", newLogin='" + newLogin + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", newFirstName='" + newFirstName + '\'' +
                ", newLastName='" + newLastName + '\'' +
                ", role='" + role + '\'' +
                ']';
    }

    @Override
    public EditUser clone() throws CloneNotSupportedException {
        super.clone();
        EditUser inst = new EditUser();
        inst.loginIsFree = loginIsFree;
        inst.success = success;
        inst.login = login;
        inst.firstName = firstName;
        inst.lastName = lastName;
        inst.newLogin = newLogin;
        inst.newPassword = newPassword;
        inst.newFirstName = newFirstName;
        inst.newLastName = newLastName;
        inst.role = role;
        return inst;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeBoolean(loginIsFree);
        out.writeBoolean(success);
        out.writeObject(login);
        out.writeObject(firstName);
        out.writeObject(lastName);
        out.writeObject(newLogin);
        out.writeObject(newPassword);
        out.writeObject(newFirstName);
        out.writeObject(newLastName);
        out.writeObject(role);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        loginIsFree = in.readBoolean();
        success = in.readBoolean();
        login = (String) in.readObject();
        firstName = (String) in.readObject();
        lastName = (String) in.readObject();
        newLogin = (String) in.readObject();
        newPassword = (String) in.readObject();
        newFirstName = (String) in.readObject();
        newLastName = (String) in.readObject();
        role = (String) in.readObject();
    }
}
