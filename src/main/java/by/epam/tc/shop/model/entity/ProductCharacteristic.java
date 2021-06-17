package by.epam.tc.shop.model.entity;

import java.util.Objects;

public class ProductCharacteristic {
    private int id;
    private String name;
    private String value;
    private String description;

    public ProductCharacteristic(){};

    public ProductCharacteristic(int id, String name, String value, String description) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCharacteristic that = (ProductCharacteristic) o;
        return id == that.id
                && Objects.equals(name, that.name)
                && Objects.equals(value, that.value)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, description);
    }

    @Override
    public String toString() {
        return "ProductCharacteristic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
