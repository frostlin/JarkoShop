package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.entity.ProductCharacteristic;

import java.util.List;

public interface ProductCharacteristicDao {
    public List<ProductCharacteristic> getProductCharacteristics(int productId, int categoryId) throws DaoException;

}
