package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Order;
import by.epam.tc.shop.model.entity.Product;

import java.util.List;

public interface OrderDao {

    boolean add(int userId, int paymentMethodId, int addressId, float sumToPay, String comment) throws DaoException;
    List<Order> getRange(int page, int recordsPerPage) throws DaoException;
    List<Order> getRangeByUser(int start, int offset, int userId) throws DaoException;
    int getOrderCount() throws DaoException;
    List<Order> getByUser(int userId) throws DaoException;
    Order getById(int orderId) throws DaoException;

}
