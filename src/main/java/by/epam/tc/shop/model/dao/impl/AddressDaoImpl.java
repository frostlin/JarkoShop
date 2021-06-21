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
    private static final String GET_BY_ID = "SELECT * FROM address WHERE id LIKE ?";

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
                           resultSet.getString(ColumnNames.ADDRESS_COUNTRY),
                           resultSet.getString(ColumnNames.ADDRESS_REGION),
                           resultSet.getString(ColumnNames.ADDRESS_CITY),
                           resultSet.getString(ColumnNames.ADDRESS_STREET),
                           resultSet.getString(ColumnNames.ADDRESS_BUILDING),
                           resultSet.getString(ColumnNames.ADDRESS_APARTMENT),
                           resultSet.getString(ColumnNames.ADDRESS_INDEX))
                   );
            }
            return addresses;
        } catch (SQLException e){
            throw new DaoException("Error getting addresses for userId " + userId, e);
        }
    }
    @Override
    public Address getById(int addressId) throws DaoException {
        Address address = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID))
        {
            statement.setInt(1, addressId);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                address = new Address(
                        resultSet.getInt(ColumnNames.ADDRESS_ID),
                        resultSet.getString(ColumnNames.ADDRESS_COUNTRY),
                        resultSet.getString(ColumnNames.ADDRESS_REGION),
                        resultSet.getString(ColumnNames.ADDRESS_CITY),
                        resultSet.getString(ColumnNames.ADDRESS_STREET),
                        resultSet.getString(ColumnNames.ADDRESS_BUILDING),
                        resultSet.getString(ColumnNames.ADDRESS_APARTMENT),
                        resultSet.getString(ColumnNames.ADDRESS_INDEX)
                );
            }
            return address;
        } catch (SQLException e){
            throw new DaoException("Error getting address with id=" + addressId, e);
        }
    }


}
