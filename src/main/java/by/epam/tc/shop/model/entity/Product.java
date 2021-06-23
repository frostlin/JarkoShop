package by.epam.tc.shop.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product implements Serializable {
    private int id;
    private String brand;
    private Category category;
    private List<ProductCharacteristic> characteristics = new ArrayList<>();
    private List<String> photos = new ArrayList<>();

    private float price;
    private String model;
    private String description;
    private int warranty;
    private int stockAmount;

    public Product (){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<ProductCharacteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Float.compare(product.price, price) == 0 && warranty == product.warranty && stockAmount == product.stockAmount && Objects.equals(brand, product.brand) && Objects.equals(category, product.category) && Objects.equals(characteristics, product.characteristics) && Objects.equals(photos, product.photos) && Objects.equals(model, product.model) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, category, characteristics, photos, price, model, description, warranty, stockAmount);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", category=" + category +
                ", characteristics=" + characteristics +
                ", photos=" + photos +
                ", price=" + price +
                ", model='" + model + '\'' +
                ", description='" + description + '\'' +
                ", warranty=" + warranty +
                ", stockAmount=" + stockAmount +
                '}';
    }
}
