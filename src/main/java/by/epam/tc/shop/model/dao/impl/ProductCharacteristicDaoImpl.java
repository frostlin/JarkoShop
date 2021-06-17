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

    private static final String GET_ALL =
            "SELECT pc.id, c.name, c.description, pc.value " +
                    "FROM product_characteristic pc " +
                    "JOIN characteristic ch ON pc.characteristic_id=ch.id " +
                    "JOIN category c ON ch.category_id=c.id " +
                    "WHERE pc.product_id LIKE ? " +
                    "AND ch.category_id IN ( " +
                    "   SELECT c.id FROM c WHERE c.id LIKE ?)";

    private ProductCharacteristicDaoImpl() {}
    public static ProductCharacteristicDaoImpl getInstance(){ return instance; }

    @Override
    public List<ProductCharacteristic> getProductCharacteristics(int productId, int categoryId) throws DaoException {
        List<ProductCharacteristic> characteristics = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL))
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

    private ProductCharacteristic getProductCharacteristicsFromResultSet(ResultSet resultSet) throws SQLException, DaoException{
        ProductCharacteristic characteristic = new ProductCharacteristic();

        int id =       resultSet.getInt(ColumnNames.CHARACTERISTIC_ID);
        String desc =  resultSet.getString(ColumnNames.CHARACTERISTIC_DESCRIPTION);
        String name =  resultSet.getString(ColumnNames.CHARACTERISTIC_NAME);
        String value = resultSet.getString(ColumnNames.CHARACTERISTIC_VALUE);;

        characteristic.setId(id);
        characteristic.setDescription(desc);
        characteristic.setName(name);
        characteristic.setValue(value);

        return characteristic;
    }
}
