package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.ProductCharacteristicDao;
import by.epam.tc.shop.model.dao.ProductDao;
import by.epam.tc.shop.model.entity.Address;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.entity.UserRole;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final ProductDaoImpl instance = new ProductDaoImpl();
    private static final ProductCharacteristicDaoImpl characteristicDao = ProductCharacteristicDaoImpl.getInstance();

    private static final String ADD = "INSERT INTO user (role_id,email,login,password) VALUES (?,?,?,?)";
    private static final String GET_RANGE =
        "SELECT product.id,price,model,product.description,warranty,stock_amount,brand.name,category.id,category.name,category.description " +
        "FROM product " +
        "JOIN brand ON product.brand_id=brand.id " +
        "JOIN category ON product.category_id=category.id " +
        "LIMIT ?, ?";
    private static final String GET_PHOTOS = "SELECT path FROM photo WHERE product_id LIKE ?";
    private static final String GET_PRODUCT_COUNT = "SELECT COUNT(id) AS recordCount FROM product";

    private ProductDaoImpl() {}
    public static ProductDaoImpl getInstance(){ return instance; }

    @Override
    public List<Product> getRange(int start, int offset) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RANGE))
        {
            statement.setInt(1, start);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
                products.add(getProductFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return products;
    }

    @Override
    public int getProductCount() throws DaoException {
        int size = 0;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_COUNT);
             ResultSet resultSet = statement.executeQuery())
        {
            if (resultSet.next())
                size = resultSet.getInt("recordCount");
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }

        return size;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException, DaoException{
        Product product = new Product();

        int id =             resultSet.getInt(ColumnNames.PRODUCT_ID);
        float price =        resultSet.getFloat(ColumnNames.PRICE);
        String model =       resultSet.getString(ColumnNames.MODEL);
        String productDesc = resultSet.getString(ColumnNames.PRODUCT_DESCRIPTION);
        int warranty =       resultSet.getInt(ColumnNames.WARRANTY);
        int stock =          resultSet.getInt(ColumnNames.STOCK_AMOUNT);
        String brandName =   resultSet.getString(ColumnNames.BRAND_NAME);

        int categoryId =      resultSet.getInt(ColumnNames.CATEGORY_ID);
        String categoryName = resultSet.getString(ColumnNames.CATEGORY_NAME);
        String categoryDesc = resultSet.getString(ColumnNames.CATEGORY_DESCRIPTION);


        product.setId(id);
        product.setPrice(price);
        product.setModel(model);
        product.setDescription(productDesc);
        product.setWarranty(warranty);
        product.setStockAmount(stock);
        product.setBrand(brandName);
        product.setCategory(categoryName);
        product.setPhotos(getProductPhotos(id));
        product.setCharacteristics(characteristicDao.getProductCharacteristics(id, categoryId));
        return product;
    }

    public List<String> getProductPhotos(int productId) throws DaoException {
        List<String> photos = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PHOTOS))
        {
            statement.setInt(1, productId);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                photos.add(resultSet.getString(ColumnNames.PHOTO_PATH));
            }

        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return photos;
    }

}
