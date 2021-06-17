package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.UserDao;
import by.epam.tc.shop.model.entity.Address;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.entity.UserRole;
import by.epam.tc.shop.model.pool.ConnectionPool;

import javax.swing.text.html.Option;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final AddressDaoImpl addressDao = AddressDaoImpl.getInstance();

    private static final String ADD = "INSERT INTO user (role_id,email,login,password) VALUES (?,?,?,?)";
    private static final String GET_ALL =
            "SELECT user.id,role.name,email,login,password,surname,user.name,lastname,telephone,date_registered " +
            "FROM user " +
            "JOIN role ON user.role_id=role.id";
    private static final String GET_BY_LOGIN = GET_ALL + " WHERE login LIKE ?";
    private static final String GET_BY_EMAIL = GET_ALL + " WHERE email LIKE ?";
    private static final String GET_BY_ID = GET_ALL + " WHERE id LIKE ?";

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
             PreparedStatement statement = connection.prepareStatement(GET_ALL);
             ResultSet resultSet = statement.executeQuery();)
        {
            while(resultSet.next())
                users.add(getUserFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return users;
    }

    @Override
    public Optional<User> getByLogin(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN);)
        {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                user = Optional.of(getUserFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return user;
    }

    @Override
    public Optional<User> getByEmail(String email) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_EMAIL);)
        {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                user =  Optional.of(getUserFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return user;
    }

    @Override
    public Optional<User> getById(int id) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                user = Optional.of(getUserFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return user;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws DaoException, SQLException {
        User user = new User();

        int id = resultSet.getInt(ColumnNames.USER_ID);
        String email = resultSet.getString(ColumnNames.EMAIL);
        String role_name = resultSet.getString(ColumnNames.ROLE_NAME);
        List<Address> addresses = addressDao.getByUser(id);
        String login = resultSet.getString(ColumnNames.LOGIN);
        String password = resultSet.getString(ColumnNames.PASSWORD);
        String surname = resultSet.getString(ColumnNames.SURNAME);
        String name = resultSet.getString(ColumnNames.NAME);
        String lastname = resultSet.getString(ColumnNames.LASTNAME);
        String telephone = resultSet.getString(ColumnNames.TELEPHONE);
        Date dateRegistered = resultSet.getDate(ColumnNames.DATE_REGISTERED);

        user.setId(id);
        user.setEmail(email);
        user.setRole(UserRole.valueOf(role_name.toUpperCase()));
        user.setAddresses(addresses);
        user.setLogin(login);
        user.setPassword(password);
        user.setSurname(surname);
        user.setName(name);
        user.setLastname(lastname);
        user.setTelephone(telephone);
        user.setDateRegistered(dateRegistered);

        return user;
    }
}
