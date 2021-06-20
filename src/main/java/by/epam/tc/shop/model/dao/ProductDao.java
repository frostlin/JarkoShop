package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

     List<Product> getRange(int page, int recordsPerPage) throws DaoException;

     int getProductCount() throws DaoException;

     Product getById(int productId) throws DaoException;

}
