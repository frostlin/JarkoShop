package by.epam.tc.shop.model.entity;

import java.sql.Date;
import java.util.Objects;

public class Discount {
    private int id;
    private int value;
    private Date start;
    private Date end;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return id == discount.id && value == discount.value && Objects.equals(start, discount.start) && Objects.equals(end, discount.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, start, end);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", value=" + value +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
