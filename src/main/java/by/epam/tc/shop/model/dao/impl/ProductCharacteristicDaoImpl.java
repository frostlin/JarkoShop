package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.ProductCharacteristicDao;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.entity.ProductCharacteristic;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCharacteristicDaoImpl implements ProductCharacteristicDao {
    private static final ProductCharacteristicDaoImpl instance = new ProductCharacteristicDaoImpl();

    private static final String GET_PRODUCT_CHARACTERISTICS =
            "SELECT characteristic.name, characteristic.description, product_characteristic.value " +
                    "FROM characteristic " +
                    "LEFT JOIN product_characteristic ON product_characteristic.characteristic_id=characteristic.id " +
                    "WHERE product_characteristic.product_id LIKE ? " +
                    "AND characteristic.category_id LIKE ?";

    private static final String GET_PRODUCT_CHARACTERISTICS_FOR_CATEGORY =
            "SELECT characteristic.name, characteristic.description " +
            "FROM characteristic WHERE category_id LIKE ?";

    private ProductCharacteristicDaoImpl() {}
    public static ProductCharacteristicDaoImpl getInstance(){ return instance; }

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

    private ProductCharacteristic getProductCharacteristicsFromResultSet(ResultSet resultSet) throws SQLException, DaoException{
        ProductCharacteristic characteristic = new ProductCharacteristic();

        String desc =  resultSet.getString(ColumnNames.CHARACTERISTIC_DESCRIPTION);
        String name =  resultSet.getString(ColumnNames.CHARACTERISTIC_NAME);
        String value = resultSet.getString(ColumnNames.CHARACTERISTIC_VALUE);;

        characteristic.setDescription(desc);
        characteristic.setName(name);
        characteristic.setValue(value);

        return characteristic;
    }

    private ProductCharacteristic getProductCharacteristicsForCategoryFromResultSet(ResultSet resultSet) throws SQLException, DaoException{
        ProductCharacteristic characteristic = new ProductCharacteristic();

        String desc =  resultSet.getString(ColumnNames.CHARACTERISTIC_DESCRIPTION);
        String name =  resultSet.getString(ColumnNames.CHARACTERISTIC_NAME);

        characteristic.setDescription(desc);
        characteristic.setName(name);

        return characteristic;
    }
}
