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
    List<User> getRange(int start, int offset) throws DaoException;
    Optional<User> getByLogin(String login) throws DaoException;
    Optional<User> getByEmail(String login) throws DaoException;
    Optional<User> getById(int id) throws DaoException;
    int getUserCount() throws DaoException;


}
