package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.entity.ProductCharacteristic;

import java.util.List;

public interface ProductCharacteristicDao {
    List<ProductCharacteristic> getProductCharacteristicsForCategory(int categoryId) throws DaoException;
    List<ProductCharacteristic> getProductCharacteristics(int productId, int categoryId) throws DaoException;
    List<Integer> getCharacteristicsIdByCategory(int categoryId)throws DaoException;
    void initializeProductCharacteristic(int characteristicId, int productId) throws DaoException;
    boolean updateProductCharacteristic(int characteristicId, int productId, String value) throws DaoException;
    boolean updateCharacteristic(int characteristicId, String characteristicName, String characteristicDesc) throws DaoException;
    int add(int categoryId, String name, String desc) throws DaoException;

}
