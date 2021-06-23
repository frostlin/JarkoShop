package by.epam.tc.shop.model.service.impl;

import by.epam.tc.shop.model.dao.CartItemDao;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.impl.CartItemDaoImpl;
import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.service.CartItemService;
import by.epam.tc.shop.model.service.ServiceException;

import java.util.List;

public class CartItemServiceImpl implements CartItemService {
    private final CartItemDao cartItemDao = CartItemDaoImpl.getInstance();

    @Override
    public boolean updateCartItemsWithOrder(List<CartItem> cart, int orderId, int userId) throws ServiceException {
        try {
            for (CartItem item : cart){
                cartItemDao.updateCartItemToOrder(orderId, item.getProduct().getId(), userId);
            }
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addProductToCart(int productId, int userId) throws ServiceException{
        boolean isAdded = false;
        try {
            isAdded = cartItemDao.addCartItem(productId, userId);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public CartItem getCartItem(int productId, int userId) throws ServiceException{
        try {
            return cartItemDao.getCartItem(productId, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public boolean deleteCartItem(int productId, int userId) throws ServiceException{
        try {
            return cartItemDao.deleteCartItem(userId, productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
