package by.epam.tc.shop.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private User user;
    private Address address;
    private String paymentMethod;

    private String status;
    private float sumToPay;
    private float payedSum;
    private Date dateOrdered;
    private Date dateShipping;
    private String comment;

    public Order(){}

    public Order(int id, User user, Address address, String paymentMethod, String status, float sumToPay, float payedSum, Date dateOrdered, Date dateShipping, String comment) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.sumToPay = sumToPay;
        this.payedSum = payedSum;
        this.dateOrdered = dateOrdered;
        this.dateShipping = dateShipping;
        this.comment = comment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getSumToPay() {
        return sumToPay;
    }

    public void setSumToPay(float sumToPay) {
        this.sumToPay = sumToPay;
    }

    public float getPayedSum() {
        return payedSum;
    }

    public void setPayedSum(float payedSum) {
        this.payedSum = payedSum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Date getDateShipping() {
        return dateShipping;
    }

    public void setDateShipping(Date dateShipping) {
        this.dateShipping = dateShipping;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Float.compare(order.sumToPay, sumToPay) == 0 && Float.compare(order.payedSum, payedSum) == 0 && Objects.equals(user, order.user) && Objects.equals(address, order.address) && Objects.equals(paymentMethod, order.paymentMethod) && Objects.equals(status, order.status) && Objects.equals(dateOrdered, order.dateOrdered) && Objects.equals(dateShipping, order.dateShipping) && Objects.equals(comment, order.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, address, paymentMethod, status, sumToPay, payedSum, dateOrdered, dateShipping, comment);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", address=" + address +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", status='" + status + '\'' +
                ", sumToPay=" + sumToPay +
                ", payedSum=" + payedSum +
                ", dateOrdered=" + dateOrdered +
                ", dateShipping=" + dateShipping +
                ", comment='" + comment + '\'' +
                '}';
    }
}
