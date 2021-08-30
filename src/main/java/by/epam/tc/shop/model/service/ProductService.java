package by.epam.tc.shop.model.service;

import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.entity.Brand;
import by.epam.tc.shop.model.entity.Product;

import java.util.List;

public interface ProductService {
    int addNewProduct(int brandId, int categoryId, float price, String model, String description, int warranty, int amount_stock) throws ServiceException;
    int addNewBrand(String name) throws ServiceException;
    int addPhotos(int productId, String name)throws ServiceException;
    int addNewCharacteristic(int categoryId, String name, String desc) throws ServiceException;

    List<Product> getProductPage(int pageNumber, int recordsPerPage) throws ServiceException;
    List<Product> getProductPageByCategory(int pageNumber, int recordsPerPage, int categoryId) throws ServiceException;
    List<Product> getProductPageByCategorySortedByPrice(int pageNumber, int recordsPerPage, int categoryId, String direction) throws ServiceException;
    List<Product> getProductPageByCategorySortedByAvgRating(int pageNumber, int recordsPerPage, int categoryId) throws ServiceException;
    List<Product> getProductPageBySearch(int pageNumber, int recordsPerPage, int categoryId, String searchString) throws ServiceException;

    int getProductCount() throws ServiceException;
    int getProductCount(int categoryId) throws ServiceException;
    Product getProductById(int id) throws ServiceException;
    Product getLatest() throws ServiceException;

    boolean updateProductCharacteristic(int characteristicId, int productId, String value) throws ServiceException;
    boolean updateCharacteristic(int characteristicId, String productName, String productDesc) throws ServiceException;
    List<Brand> getBrandList() throws ServiceException;
}
