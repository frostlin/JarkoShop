package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.UserDao;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.entity.UserRole;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl instance = new UserDaoImpl();

    private static final String ADD = "INSERT INTO user (role_id,email,login,password,date_registered) VALUES (?,?,?,?,?)";
    private static final String GET_ALL = "SELECT user.id,role.role,email,login,password,surname,name,lastname,telephone,date_registered," +
            " FROM user JOIN role ON role_id=role.id";
    private static final String CHECK_LOGIN = "SELECT id FROM user WHERE login LIKE ?";
    private static final String CHECK_EMAIL = "SELECT id FROM user WHERE email LIKE ?";


    private UserDaoImpl() {}
    public static UserDaoImpl getInstance(){ return instance; }


    @Override
    public boolean add(int role_id, String email, String login, String password) throws DaoException {
        boolean isSuccessful = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD);)
        {
            statement.setInt(1, role_id);
            statement.setString(2, email);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setDate(5, new Date(System.currentTimeMillis()));

            statement.executeUpdate();
            isSuccessful = true;
        } catch(SQLException e){
            throw new DaoException("Error inserting user " + login, e);
        }
        return isSuccessful;
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD);
             ResultSet resultSet = statement.executeQuery();)
        {
            while(resultSet.next()){
                User user = new User(
                        resultSet.getInt(ColumnNames.USER_ID),
                        UserRole.valueOf(resultSet.getString(ColumnNames.ROLE_NAME).toUpperCase()),
                        resultSet.getString(ColumnNames.EMAIL),
                        resultSet.getString(ColumnNames.LOGIN),
                        resultSet.getString(ColumnNames.PASSWORD),
                        resultSet.getDate(ColumnNames.DATE_REGISTERED)
                        );
                users.add(user);
            }
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return users;
    }

    @Override
    public Optional<User> getByLogin(String login) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<User> getByEmail(String login) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(int id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean isLoginRegistered(String login) throws DaoException {
        return false;
    }

    @Override
    public boolean isEmailRegistered(String email) throws DaoException {
        return false;
    }
}
