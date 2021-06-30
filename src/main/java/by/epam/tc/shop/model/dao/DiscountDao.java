package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Discount;

import java.sql.Date;
import java.util.List;

public interface DiscountDao {
    int add(int value, Date start, Date end) throws DaoException;
    List<Discount> getAll() throws DaoException;
    Discount getByProductId(int productId) throws DaoException;

}
