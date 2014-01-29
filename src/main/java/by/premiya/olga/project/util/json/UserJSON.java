package by.premiya.olga.project.util.json;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author Vlad Abramov
 */
public class UserJSON implements Externalizable {
    private static final long serialVersionUID = 8361452501148473440L;
    private boolean loginIsFree = true;
    private String login;
    private String firstName;
    private String lastName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "UserJSON{" +
                "loginIsFree=" + loginIsFree +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeBoolean(loginIsFree);
        out.writeObject(login);
        out.writeObject(firstName);
        out.writeObject(lastName);
        out.writeObject(password);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        loginIsFree = in.readBoolean();
        login = (String) in.readObject();
        firstName = (String) in.readObject();
        lastName = (String) in.readObject();
        password = (String) in.readObject();
    }
}
