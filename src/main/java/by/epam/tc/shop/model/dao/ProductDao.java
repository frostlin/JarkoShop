package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Brand;
import by.epam.tc.shop.model.entity.Product;
import com.mysql.cj.conf.ConnectionUrlParser;

import java.util.HashMap;
import java.util.List;

public interface ProductDao {
     int add(int brandId, int categoryId, float price, String model, String description, int warranty, int amount_stock) throws DaoException;
     int addBrand(String name) throws DaoException;
     int addPhoto(int productId, String name)throws DaoException;

     List<Product> getRange(int start, int offset) throws DaoException;

     List<Product> getRangeByCategory(int start, int offset, int categoryId) throws DaoException;
     List<Product> getRangeByCategoryOrderByPrice(int start, int offset, int categoryId, String direction) throws DaoException;
     List<Product> getRangeByCategoryOrderByAvgRating(int start, int offset, int categoryId) throws DaoException;

     List<Product> getBySearch(String searchString) throws DaoException;
     List<Product> getBySearch(int categoryId, String searchString) throws DaoException;
     int getProductCount() throws DaoException;
     int getProductCount(int categoryId) throws DaoException;
     Product getById(int productId) throws DaoException;

     List<Brand>  getBrandList() throws DaoException;

}
