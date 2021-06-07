package by.epam.tc.shop.model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Review implements Serializable {
    private int id;
    private int userId;
    private int productId;

    private String content;
    private Date date;
    private int rating;

    public Review (){}

    public Review(int id, int userId, int productId, String content, Date date, int rating) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.date = date;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id
                && userId == review.userId
                && productId == review.productId
                && rating == review.rating
                && Objects.equals(content, review.content)
                && Objects.equals(date, review.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, content, date, rating);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", rating=" + rating +
                '}';
    }
}
