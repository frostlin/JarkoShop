package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.CartItem;

import java.util.List;

public interface CartItemDao {

    boolean updateCartItemToOrder(int orderId, int productId, int userId) throws DaoException;
    boolean addCartItem(int product_id, int user_id) throws DaoException;
    List<CartItem> getCart(int user_id) throws DaoException;
    boolean deleteCartItem(int user_id, int product_id) throws DaoException;
    CartItem getCartItem(int product_id, int user_id) throws DaoException;
}
