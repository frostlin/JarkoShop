package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Order;
import by.epam.tc.shop.model.entity.Product;

import java.util.List;

public interface OrderDao {

    int add(int userId, int paymentMethodId, int addressId, float sumToPay, String comment) throws DaoException;
    List<Order> getRange(int page, int recordsPerPage) throws DaoException;
    List<Order> getRangeByUser(int start, int offset, int userId) throws DaoException;
    int getOrderCount() throws DaoException;
    int getActiveOrderCountByUser(int userId) throws DaoException;
    int getNotActiveOrderCountByUser(int userId) throws DaoException;
    List<Order> getByUser(int userId) throws DaoException;
    Order getById(int orderId) throws DaoException;

    List<Order> getRangeActive(int start, int offset) throws DaoException;
    List<Order> getRangeNotActive(int start, int offset) throws DaoException;
}
