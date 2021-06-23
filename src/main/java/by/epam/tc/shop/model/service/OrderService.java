package by.epam.tc.shop.model.service;

import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.entity.Order;

import java.util.List;

public interface OrderService {
    int add(int userId, int paymentMethodId, int addressId, float sumToPay, String comment, List<CartItem> cart) throws ServiceException;
    List<Order> getOrdersPage(int page, int recordsPerPage) throws ServiceException;
    List<Order> getRangeByUser(int start, int offset, int userId) throws ServiceException;
    int getOrderCount() throws ServiceException;
    List<Order> getByUser(int userId) throws ServiceException;
    Order getById(int orderId) throws ServiceException;
}
