package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Product;

import java.util.List;

public interface ProductDao {

     List<Product> getRange(int page, int recordsPerPage) throws DaoException;
     List<Product> getRange(int start, int offset, int categoryId) throws DaoException;
     int getProductCount() throws DaoException;
     int getProductCount(int categoryId) throws DaoException;
     Product getById(int productId) throws DaoException;

}
