package by.epam.tc.shop.model.service;

import by.epam.tc.shop.model.entity.CartItem;

import java.util.List;

public interface CartItemService {

    boolean addProductToCart(int productId, int userId) throws ServiceException;
    CartItem getCartItem(int productId, int userId) throws ServiceException;
    boolean deleteCartItem(int productId, int userId) throws ServiceException;
    boolean updateCartItemsWithOrder(List<CartItem> cart, int orderId, int userId) throws ServiceException;

}
