package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.CartItemDao;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDaoImpl implements CartItemDao {
    private static final CartItemDaoImpl instance = new CartItemDaoImpl();
    private static final ProductDaoImpl productDao = ProductDaoImpl.getInstance();

    private static final String GET_CART =
            "SELECT product_id,count FROM cart_item " +
                    "WHERE (order_id IS NULL) AND (user_id LIKE ?)";
    private static final String GET_CART_ITEM = GET_CART + " AND (product_id LIKE ?)";

    private static final String ADD_CART_ITEM = "INSERT INTO cart_item (product_id,user_id) VALUES (?,?)";
    private static final String UPDATE_CART_ITEM_TO_ORDER = "UPDATE cart_item SET order_id = ? " +
            "WHERE product_id LIKE ? AND user_id LIKE ?";

    private static final String DELETE_CART = "DELETE FROM cart_item WHERE user_id LIKE ?";
    private static final String DELETE_CART_ITEM = DELETE_CART + " AND product_id LIKE ?";

    private CartItemDaoImpl() {}
    public static CartItemDaoImpl getInstance(){ return instance; }


    @Override
    public boolean addCartItem(int product_id, int user_id) throws DaoException{
        boolean isSuccessful = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_CART_ITEM))
        {
            statement.setInt(1, product_id);
            statement.setInt(2, user_id);

            statement.executeUpdate();
            isSuccessful = true;
        } catch(SQLException e){
            throw new DaoException("Error inserting cart item product " + product_id + " for user " + user_id, e);
        }
        return isSuccessful;
    }
    @Override
    public boolean updateCartItemToOrder(int orderId, int productId, int userId) throws DaoException {
        boolean isSuccessful = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CART_ITEM_TO_ORDER);)
        {
            statement.setInt(1, orderId);
            statement.setInt(2, productId);
            statement.setInt(3, userId);

            statement.executeUpdate();
            isSuccessful = true;
        } catch(SQLException e){
            throw new DaoException("Error inserting user " + orderId, e);
        }
        return isSuccessful;
    }

    @Override
    public CartItem getCartItem(int product_id, int user_id) throws DaoException{
        CartItem item = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CART_ITEM);)
        {
            statement.setInt(1, user_id);
            statement.setInt(2, product_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                item = getUserCartItemFromResultSet(resultSet);
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return item;
    }

    @Override
    public List<CartItem> getCart(int user_id) throws DaoException{
        List<CartItem> items = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CART))
        {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                items.add(getUserCartItemFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return items;
    }
    @Override
    public boolean deleteCartItem(int user_id, int product_id) throws DaoException{
        boolean isSuccessful = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CART_ITEM);)
        {
            statement.setInt(1, user_id);
            statement.setInt(2, product_id);

            statement.executeUpdate();
            isSuccessful = true;
        } catch(SQLException e){
            throw new DaoException("Error deleting cart items for user " + user_id, e);
        }
        return isSuccessful;
    }

    private CartItem getUserCartItemFromResultSet(ResultSet resultSet) throws DaoException, SQLException {
        CartItem item = new CartItem();

        int productId = resultSet.getInt("product_id");
        int count = resultSet.getInt("count");

        item.setProduct(productDao.getById(productId));
        item.setCount(count);
        return item;
    }
}
