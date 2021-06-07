package by.epam.tc.shop.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private User user;
    private Address address;

    private String status;
    private float totalSum;
    private Date dateOrdered;
    private Date dateShipping;
    private String comment;

    private Order(){}

    public Order(int id, User user, Address address, String status, float totalSum, Date dateOrdered, Date dateShipping, String comment) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.status = status;
        this.totalSum = totalSum;
        this.dateOrdered = dateOrdered;
        this.dateShipping = dateShipping;
        this.comment = comment;
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

    public float getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
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
        return id == order.id
                && Float.compare(order.totalSum, totalSum) == 0
                && Objects.equals(user, order.user)
                && Objects.equals(address, order.address)
                && Objects.equals(status, order.status)
                && Objects.equals(dateOrdered, order.dateOrdered)
                && Objects.equals(dateShipping, order.dateShipping)
                && Objects.equals(comment, order.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, address, status, totalSum, dateOrdered, dateShipping, comment);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", address=" + address +
                ", status='" + status + '\'' +
                ", totalSum=" + totalSum +
                ", dateOrdered=" + dateOrdered +
                ", dateShipping=" + dateShipping +
                ", comment='" + comment + '\'' +
                '}';
    }
}
