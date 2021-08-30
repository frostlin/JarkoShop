package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.ProductCharacteristicDao;
import by.epam.tc.shop.model.entity.ProductCharacteristic;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCharacteristicDaoImpl implements ProductCharacteristicDao {
    private static final ProductCharacteristicDaoImpl instance = new ProductCharacteristicDaoImpl();

    private static final String ADD = "INSERT INTO characteristic (category_id,name,description) VALUES (?,?,?)";

    private static final String GET_PRODUCT_CHARACTERISTICS =
            "SELECT characteristic.id, characteristic.name, characteristic.description, product_characteristic.value " +
                    "FROM characteristic " +
                    "LEFT JOIN product_characteristic ON product_characteristic.characteristic_id=characteristic.id " +
                    "WHERE product_characteristic.product_id LIKE ? " +
                    "AND characteristic.category_id LIKE ?";

    private static final String GET_PRODUCT_CHARACTERISTICS_FOR_CATEGORY =
            "SELECT characteristic.name, characteristic.description, id " +
            "FROM characteristic WHERE category_id LIKE ?";

    private static final String GET_CHARACTERISTICS_ID_BY_CATEGORY =
            "SELECT characteristic.id FROM characteristic WHERE category_id LIKE ?";
    private static final String INITIALIZE_PRODUCT_CHARACTERISTIC =
            "INSERT INTO product_characteristic (characteristic_id,product_id) VALUES (?,?)";
    private static final String INITIALIZE_CHARACTERISTIC =
            "INSERT INTO product_characteristic (characteristic_id,product_id) VALUES (?,?)";
    private static final String UPDATE_PRODUCT_CHARACTERISTIC = "UPDATE product_characteristic SET value = ? " +
            "WHERE product_id LIKE ? AND characteristic_id LIKE ?";
    private static final String UPDATE_CHARACTERISTIC_NAME = "UPDATE characteristic SET name = ? WHERE id LIKE ?";
    private static final String UPDATE_CHARACTERISTIC_DESC = "UPDATE characteristic SET description = ? WHERE id LIKE ?";

    private ProductCharacteristicDaoImpl() {}
    public static ProductCharacteristicDaoImpl getInstance(){ return instance; }

    @Override
    public int add(int categoryId, String name, String desc) throws DaoException {
        int id = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD,Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, categoryId);
            statement.setString(2, name);
            statement.setString(3, desc);

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                id = resultSet.getInt(1);

        } catch(SQLException e){
            throw new DaoException("Error adding characteristic " + id, e);
        }
        return id;
    }

    @Override
    public List<ProductCharacteristic> getProductCharacteristics(int productId, int categoryId) throws DaoException {
        List<ProductCharacteristic> characteristics = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_CHARACTERISTICS))
        {
            statement.setInt(1, productId);
            statement.setInt(2, categoryId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                characteristics.add(getProductCharacteristicsFromResultSet(resultSet));

        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return characteristics;
    }

    @Override
    public List<ProductCharacteristic> getProductCharacteristicsForCategory(int categoryId) throws DaoException {
        List<ProductCharacteristic> characteristics = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_CHARACTERISTICS_FOR_CATEGORY))
        {
            statement.setInt(1 , categoryId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                characteristics.add(getProductCharacteristicsForCategoryFromResultSet(resultSet));

        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return characteristics;
    }

    @Override
    public List<Integer> getCharacteristicsIdByCategory(int categoryId) throws DaoException {
        List<Integer> characteristics = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CHARACTERISTICS_ID_BY_CATEGORY))
        {
            statement.setInt(1 , categoryId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                characteristics.add(resultSet.getInt("characteristic.id"));

        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return characteristics;
    }

    @Override
    public void initializeProductCharacteristic(int characteristicId, int productId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(INITIALIZE_PRODUCT_CHARACTERISTIC))
        {
            statement.setInt(1, characteristicId);
            statement.setInt(2, productId);

            statement.executeUpdate();
        } catch(SQLException e){
            throw new DaoException("Error adding characteristic for " + productId, e);
        }
    }

    @Override
    public boolean updateProductCharacteristic(int characteristicId, int productId, String value) throws DaoException {
        boolean isSuccessful = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_CHARACTERISTIC);)
        {
            statement.setString(1, value);
            statement.setInt(2, productId);
            statement.setInt(3, characteristicId);

            statement.executeUpdate();
            isSuccessful = true;
        } catch(SQLException e){
            throw new DaoException("Error updating characteristic " + characteristicId, e);
        }
        return isSuccessful;
    }

    @Override
    public boolean updateCharacteristic(int characteristicId, String name, String desc) throws DaoException {
        boolean isSuccessful = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();)
        {
            if (!"".equals(name)){
                PreparedStatement statement = connection.prepareStatement(UPDATE_CHARACTERISTIC_NAME);

                statement.setString(1, name);
                statement.setInt(2, characteristicId);

                statement.executeUpdate();
                statement.close();
            }
            if (!"".equals(desc)){
                PreparedStatement statement = connection.prepareStatement(UPDATE_CHARACTERISTIC_DESC);

                statement.setString(1, desc);
                statement.setInt(2, characteristicId);

                statement.executeUpdate();
                statement.close();
            }
            isSuccessful = true;
        } catch(SQLException e){
            throw new DaoException("Error updating characteristic " + characteristicId, e);
        }
        return isSuccessful;
    }
    private ProductCharacteristic getProductCharacteristicsFromResultSet(ResultSet resultSet) throws SQLException, DaoException{
        ProductCharacteristic characteristic = new ProductCharacteristic();

        int id = resultSet.getInt("characteristic.id");
        String desc =  resultSet.getString(ColumnNames.CHARACTERISTIC_DESCRIPTION);
        String name =  resultSet.getString(ColumnNames.CHARACTERISTIC_NAME);
        String value = resultSet.getString(ColumnNames.CHARACTERISTIC_VALUE);;

        characteristic.setId(id);
        characteristic.setDescription(desc);
        characteristic.setName(name);
        characteristic.setValue(value);

        return characteristic;
    }

    private ProductCharacteristic getProductCharacteristicsForCategoryFromResultSet(ResultSet resultSet) throws SQLException, DaoException{
        ProductCharacteristic characteristic = new ProductCharacteristic();

        int id = resultSet.getInt("id");
        String desc =  resultSet.getString(ColumnNames.CHARACTERISTIC_DESCRIPTION);
        String name =  resultSet.getString(ColumnNames.CHARACTERISTIC_NAME);

        characteristic.setId(id);
        characteristic.setDescription(desc);
        characteristic.setName(name);

        return characteristic;
    }


}
