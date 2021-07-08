package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.entity.ProductCharacteristic;

import java.util.List;

public interface ProductCharacteristicDao {
    List<ProductCharacteristic> getProductCharacteristicsForCategory(int categoryId) throws DaoException;
    List<ProductCharacteristic> getProductCharacteristics(int productId, int categoryId) throws DaoException;
    List<Integer> getCharacteristicsIdByCategory(int categoryId)throws DaoException;
    void initializeProductCharacteristic(int characteristicId, int productId) throws DaoException;
}
