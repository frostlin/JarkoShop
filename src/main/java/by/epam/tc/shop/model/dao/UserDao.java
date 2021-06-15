package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.dao.impl.UserDaoImpl;
import by.epam.tc.shop.model.entity.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    public boolean add(int role_id, String email , String login, String password) throws DaoException;
    public List<User> getAll() throws DaoException;
    public Optional<User> getByLogin(String login) throws DaoException;
    public Optional<User> getByEmail(String login) throws DaoException;
    public Optional<User> getById(int id) throws DaoException;

}
