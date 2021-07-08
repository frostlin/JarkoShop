package by.epam.tc.shop.model.entity;

import java.sql.Date;
import java.util.Objects;

public class Review  {
    private int id;
    private User user;
    private Product product;

    private String userName;
    private String content;
    private Date date;
    private int rating;

    public Review (){}

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && rating == review.rating && Objects.equals(user, review.user) && Objects.equals(product, review.product) && Objects.equals(userName, review.userName) && Objects.equals(content, review.content) && Objects.equals(date, review.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product, userName, content, date, rating);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", rating=" + rating +
                '}';
    }
}
