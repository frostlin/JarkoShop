package by.epam.tc.shop.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {
    private int id;

    private String country;
    private String region;
    private String city;
    private String street;
    private String building;
    private String apartment;
    private String index;

    public Address(){}

    public Address(int id, String country, String region, String city, String street, String building, String apartment, String index) {
        this.id = id;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id
                && Objects.equals(country, address.country)
                && Objects.equals(region, address.region)
                && Objects.equals(city, address.city)
                && Objects.equals(street, address.street)
                && Objects.equals(building, address.building)
                && Objects.equals(apartment, address.apartment)
                && Objects.equals(index, address.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, region, city, street, building, apartment, index);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
