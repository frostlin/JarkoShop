package by.epam.tc.shop.model.entity;

import java.util.List;
import java.util.Objects;

public class Category  {
    private int id;
    private String name;
    private String description;
    private List<ProductCharacteristic> characteristics;
    private List<Category> categories;

    public Category(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductCharacteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<ProductCharacteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(name, category.name) && Objects.equals(description, category.description) && Objects.equals(characteristics, category.characteristics) && Objects.equals(categories, category.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, characteristics, categories);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", characteristics=" + characteristics +
                ", categories=" + categories +
                '}';
    }
}
