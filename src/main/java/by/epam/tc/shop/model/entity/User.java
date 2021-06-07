package by.epam.tc.shop.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Serializable {

    private int id;
    private String email;
    private UserRole role;
    private ArrayList<Address> addresses;

    private String login;
    private String password;

    private String surname;
    private String name;
    private String lastname;

    private String telephone;
    private Date dateRegistered;

    public User(){}
    public User(int id, String email, String login, String password, Date dateRegistered) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.dateRegistered = dateRegistered;
    }
    public User(int id, String email, String login, String password, String surname, String name, String lastname, String telephone, Date dateRegistered) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.lastname = lastname;
        this.telephone = telephone;
        this.dateRegistered = dateRegistered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id == user.id
                && Objects.equals(email, user.email)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(surname, user.surname)
                && Objects.equals(name, user.name)
                && Objects.equals(lastname, user.lastname)
                && Objects.equals(telephone, user.telephone)
                && Objects.equals(dateRegistered, user.dateRegistered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, login, password, surname, name, lastname, telephone, dateRegistered);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", dateRegistered=" + dateRegistered +
                '}';
    }
}
