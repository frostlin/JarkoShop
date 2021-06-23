package by.epam.tc.shop.model.service.impl;

import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.service.CartItemService;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.dao.impl.OrderDaoImpl;
import by.epam.tc.shop.model.entity.Order;
import by.epam.tc.shop.model.service.OrderService;
import by.epam.tc.shop.model.service.UserService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
    CartItemService cartItemService = new CartItemServiceImpl();
    @Override
    public int add(int userId, int paymentMethodId, int addressId, float sumToPay, String comment, List<CartItem> cart) throws ServiceException {
        try {
            int orderId = orderDao.add(userId, paymentMethodId, addressId, sumToPay, comment);
            cartItemService.updateCartItemsWithOrder(cart, orderId, userId);
            return orderId;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getRange(int pageNumber, int recordsPerPage) throws ServiceException {
        try {
            int start = (pageNumber - 1) * recordsPerPage;
            return orderDao.getRange(start, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getRangeByUser(int pageNumber, int recordsPerPage, int userId) throws ServiceException {
        try {
            int start = (pageNumber - 1) * recordsPerPage;
            return orderDao.getRangeByUser(start, recordsPerPage, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getOrderCount() throws ServiceException {
        try {
            return orderDao.getOrderCount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getByUser(int userId) throws ServiceException {
        try {
            return orderDao.getByUser(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order getById(int orderId) throws ServiceException {
        return null;
    }
}
