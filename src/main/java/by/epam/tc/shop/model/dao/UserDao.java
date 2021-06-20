package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.dao.impl.UserDaoImpl;
import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.entity.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean add(int role_id, String email , String login, String password) throws DaoException;
    List<User> getAll() throws DaoException;
    Optional<User> getByLogin(String login) throws DaoException;
    Optional<User> getByEmail(String login) throws DaoException;
    Optional<User> getById(int id) throws DaoException;

    boolean addCartItem(int product_id, int user_id) throws DaoException;
    List<CartItem>  getCart(int user_id) throws DaoException;
    boolean deleteCartItem(int user_id, int product_id) throws DaoException;
    CartItem getCartItem(int product_id, int user_id) throws DaoException;


}
