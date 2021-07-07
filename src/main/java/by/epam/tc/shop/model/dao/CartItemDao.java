package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.CartItem;

import java.util.List;

public interface CartItemDao {

    boolean updateCartItemToOrder(int orderId, int productId, int userId) throws DaoException;
    boolean addCartItem(int productId, int userId) throws DaoException;
    List<CartItem> getCart(int userId) throws DaoException;
    List<CartItem> getOrderItems(int orderId) throws DaoException;
    boolean deleteCartItem(int userId, int productId) throws DaoException;
    CartItem getCartItem(int productId, int userId) throws DaoException;
}
