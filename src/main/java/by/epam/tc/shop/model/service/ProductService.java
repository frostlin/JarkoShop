package by.epam.tc.shop.model.service;

import by.epam.tc.shop.model.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getProductPage(int pageNumber, int recordsPerPage) throws ServiceException;

    public int getProductCount() throws ServiceException;

    Product getProductById(int id) throws ServiceException;
}
