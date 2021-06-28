package by.epam.tc.shop.model.service;

import by.epam.tc.shop.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductPage(int pageNumber, int recordsPerPage) throws ServiceException;
    List<Product> getProductPageByCategory(int pageNumber, int recordsPerPage, int categoryId) throws ServiceException;
    List<Product> getLatest(int count) throws ServiceException;
    List<Product> getProductPageBySearch(int pageNumber, int recordsPerPage, int categoryId, String searchString) throws ServiceException;
    int getProductCount() throws ServiceException;
    int getProductCount(int categoryId) throws ServiceException;
    Product getProductById(int id) throws ServiceException;

}
