package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.AdderssDao;
import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.entity.Address;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AdderssDao {
    private static final AddressDaoImpl instance = new AddressDaoImpl();

    private static final String GET_BY_USER_ID = "SELECT * FROM address WHERE user_id LIKE ?";

    private AddressDaoImpl() {}
    public static AddressDaoImpl getInstance(){ return instance; }

    @Override
    public List<Address> getByUser(int userId) throws DaoException {
        List<Address> addresses = new ArrayList<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_USER_ID))
        {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                   addresses.add(new Address(
                           resultSet.getInt(ColumnNames.ADDRESS_ID),
                           resultSet.getString(ColumnNames.COUNTRY),
                           resultSet.getString(ColumnNames.REGION),
                           resultSet.getString(ColumnNames.CITY),
                           resultSet.getString(ColumnNames.STREET),
                           resultSet.getString(ColumnNames.BUILDING),
                           resultSet.getString(ColumnNames.APARTMENT),
                           resultSet.getString(ColumnNames.INDEX))
                   );
            }
            return addresses;
        } catch (SQLException e){
            throw new DaoException("Error getting addresses for userId " + userId, e);
        }
    }
}
