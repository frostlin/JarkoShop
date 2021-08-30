package by.epam.tc.shop.model.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User  {

    private int id;
    private String email;
    private UserRole role;
    private List<Address> addresses;
    private List<CartItem> cart;
    private List<PaymentCard> cards;

    private String login;
    private String password;

    private String surname;
    private String name;
    private String lastname;

    private String fio;

    private String telephone;
    private Date dateRegistered;

    public User(){}

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public List<PaymentCard> getCards() {
        return cards;
    }

    public void setCards(List<PaymentCard> cards) {
        this.cards = cards;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
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

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && role == user.role && Objects.equals(addresses, user.addresses) && Objects.equals(cart, user.cart) && Objects.equals(cards, user.cards) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(surname, user.surname) && Objects.equals(name, user.name) && Objects.equals(lastname, user.lastname) && Objects.equals(fio, user.fio) && Objects.equals(telephone, user.telephone) && Objects.equals(dateRegistered, user.dateRegistered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, role, addresses, cart, cards, login, password, surname, name, lastname, fio, telephone, dateRegistered);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", addresses=" + addresses +
                ", cart=" + cart +
                ", cards=" + cards +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", fio='" + fio + '\'' +
                ", telephone='" + telephone + '\'' +
                ", dateRegistered=" + dateRegistered +
                '}';
    }
}
