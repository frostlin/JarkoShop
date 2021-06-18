package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Product;

import java.util.List;

public interface ProductDao {

    public List<Product> getRange(int page, int recordsPerPage) throws DaoException;

    public int getProductCount() throws DaoException;
}
