package by.epam.tc.shop.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {
    private int id;
    private Product product;
    private Discount discount;
    private int count;

    public CartItem(){
        super();
    }

    public CartItem(Product product, int count){
        this.product = product;
        this.count = count;
    }
    public CartItem(Product product, int count, Discount discount){
        this.product = product;
        this.count = count;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id && count == cartItem.count && Objects.equals(product, cartItem.product) && Objects.equals(discount, cartItem.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, discount, count);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", discount=" + discount +
                ", count=" + count +
                '}';
    }
}
