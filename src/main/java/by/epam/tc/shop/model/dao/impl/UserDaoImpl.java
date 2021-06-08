package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.UserDao;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final String ADD = "INSERT INTO user (role_id,email,login,password,date_registered) VALUES (?,?,?,?,?)";

    @Override
    public boolean add(int role_id, String email, String login, String password, Date date_registered) throws DaoException {
        boolean isSuccessful = false;

        try(Connection connection = ConnectionPool.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(ADD);)
        {
            statement.setInt(1, role_id);
            statement.setString(2, email);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setDate(5, date_registered);

            statement.executeUpdate();
            isSuccessful = true;
        } catch(SQLException e){
            throw new DaoException("Error inserting user " + login, e);
        }
        return isSuccessful;
    }
}
