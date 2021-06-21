package by.epam.tc.shop.model.entity;

import by.epam.tc.shop.controller.PagePath;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class PaymentCard implements Serializable {
    private int number;
    private String ownerName;
    private Date date;
    private int crv;

    public PaymentCard(){}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCrv() {
        return crv;
    }

    public void setCrv(int crv) {
        this.crv = crv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentCard that = (PaymentCard) o;
        return number == that.number && crv == that.crv && Objects.equals(ownerName, that.ownerName) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, ownerName, date, crv);
    }

    @Override
    public String toString() {
        return "PaymentCard{" +
                "number=" + number +
                ", ownerName='" + ownerName + '\'' +
                ", date=" + date +
                ", crv=" + crv +
                '}';
    }
}
