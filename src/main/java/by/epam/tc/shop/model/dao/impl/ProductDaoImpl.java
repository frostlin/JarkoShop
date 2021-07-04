package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.ProductDao;
import by.epam.tc.shop.model.entity.*;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDaoImpl instance = new ProductDaoImpl();

    private static final ProductCharacteristicDaoImpl characteristicDao = ProductCharacteristicDaoImpl.getInstance();
    private static final CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
    private static final ReviewDaoImpl reviewDao = ReviewDaoImpl.getInstance();

    private static final String ADD = "INSERT INTO user (role_id,email,login,password) VALUES (?,?,?,?)";

    private static final String GET_ALL =
            "SELECT product.id,price,model,product.description,warranty," +
                    "stock_amount,brand.name,category.id,category.name,category.description " +
                "FROM product " +
                "JOIN brand ON product.brand_id=brand.id " +
                "JOIN category ON product.category_id=category.id ";
    private static final String ORDER_BY_ID = "ORDER BY product.id ";
    private static final String ORDER_BY_PRICE = "ORDER BY product.price ";
    private static final String LIMIT_RANGE = "LIMIT ?, ? ";

    private static final String GET_RANGE = GET_ALL + ORDER_BY_ID + "DESC " + LIMIT_RANGE;
    private static final String GET_RANGE_ORDER_BY_PRICE = GET_ALL + ORDER_BY_PRICE + "DESC " + LIMIT_RANGE;
    private static final String GET_RANGE_ORDER_BY_AVG_RATING =
            "SELECT product.id,price,model,product.description,warranty," +
                    "stock_amount,brand.name,category.id,category.name," +
                    "category.description,COALESCE(AVG(review.rating), '0') AS avgRating " +
                    "FROM product " +
                    "JOIN brand ON product.brand_id=brand.id " +
                    "JOIN category ON product.category_id=category.id " +
                    "LEFT JOIN review ON review.product_id=product.id " +
                    "GROUP BY product.id ORDER BY avgRating DESC " +
                    "LIMIT ?,?";

    private static final String GET_RANGE_BY_CATEGORY = GET_ALL + " WHERE product.category_id LIKE ? " + ORDER_BY_ID + "DESC " + LIMIT_RANGE;
    private static final String GET_RANGE_BY_CATEGORY_ORDER_BY_PRICE = GET_ALL + " WHERE product.category_id LIKE ? " +  ORDER_BY_PRICE + "DESC " + LIMIT_RANGE;
    private static final String GET_RANGE_BY_CATEGORY_ORDER_BY_AVG_RATING =
            "SELECT product.id,price,model,product.description,warranty," +
                    "stock_amount,brand.name,category.id,category.name," +
                    "category.description,COALESCE(AVG(review.rating), '0') AS avgRating " +
                    "FROM product " +
                    "JOIN brand ON product.brand_id=brand.id " +
                    "JOIN category ON product.category_id=category.id " +
                    "LEFT JOIN review ON review.product_id=product.id " +
                    "WHERE product.category_id LIKE ? " +
                    "GROUP BY product.id ORDER BY avgRating DESC " +
                    "LIMIT ?,?";

    private static final String GET_PRODUCT_COUNT = "SELECT COUNT(id) AS recordCount FROM product";
    private static final String GET_PRODUCT_COUNT_FOR_CATEGORY = GET_PRODUCT_COUNT + " WHERE category_id LIKE ?";

    private static final String GET_BY_BRAND = GET_ALL + " WHERE brand.id LIKE ?";
    private static final String GET_BY_ID = GET_ALL + " WHERE product.id LIKE ?";

    private static final String GET_PHOTOS = "SELECT path FROM photo WHERE product_id LIKE ?";

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
    public List<Product> getRangeOrderByPrice(int start, int offset, String direction) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL + ORDER_BY_PRICE + direction + LIMIT_RANGE))
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
    public List<Product> getRangeOrderByAvgRating(int start, int offset) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RANGE_ORDER_BY_AVG_RATING))
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
    public List<Product> getRangeByCategory(int start, int offset, int categoryId) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RANGE_BY_CATEGORY))
        {
            statement.setInt(1, categoryId);
            statement.setInt(2, start);
            statement.setInt(3, offset);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
                products.add(getProductFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return products;
    }

    @Override
    public List<Product> getRangeByCategoryOrderByPrice(int start, int offset, int categoryId, String direction) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL + " WHERE product.category_id LIKE ? " + ORDER_BY_PRICE + direction + LIMIT_RANGE))
        {
            statement.setInt(1, categoryId);
            statement.setInt(2, start);
            statement.setInt(3, offset);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
                products.add(getProductFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return products;
    }

    @Override
    public List<Product> getRangeByCategoryOrderByAvgRating(int start, int offset, int categoryId) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_RANGE_BY_CATEGORY_ORDER_BY_AVG_RATING))
        {
            statement.setInt(1, categoryId);
            statement.setInt(2, start);
            statement.setInt(3, offset);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next())
                products.add(getProductFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return products;
    }

    //    @Override
//    public List<Product> getRangeByBrand(int start, int offset, int brandId) throws DaoException{
//        List<Product> products = new ArrayList<>();
//        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
//             PreparedStatement statement = connection.prepareStatement(GET_BY_BRAND);)
//        {
//            statement.setInt(1, brandId);
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next())
//                products.add(getProductFromResultSet(resultSet));
//        } catch(SQLException e){
//            throw new DaoException("Error getting product by brand=" + brandId, e);
//        }
//        return products;
//    }


    @Override
    public Product getById(int id) throws DaoException {
        Product product = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID);)
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                product = getProductFromResultSet(resultSet);
        } catch(SQLException e){
            throw new DaoException("Error getting product by id=" + id, e);
        }
        return product;
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
    @Override
    public int getProductCount(int categoryId) throws DaoException {
        int size = 0;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_COUNT_FOR_CATEGORY))
        {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                size = resultSet.getInt("recordCount");
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }

        return size;
    }

    @Override
    public List<Product> getBySearch(String searchString) throws DaoException {
        List<Product> products = new ArrayList<>();

        return products;
    }

    @Override
    public List<Product> getBySearch(int categoryId, String searchString) throws DaoException {
        return null;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException, DaoException{
        Product product = new Product();

        int id =             resultSet.getInt(ColumnNames.PRODUCT_ID);
        float price =        resultSet.getFloat(ColumnNames.PRODUCT_PRICE);
        String model =       resultSet.getString(ColumnNames.PRODUCT_MODEL);
        String productDesc = resultSet.getString(ColumnNames.PRODUCT_PRODUCT_DESCRIPTION);
        int warranty =       resultSet.getInt(ColumnNames.PRODUCT_WARRANTY);
        int stock =          resultSet.getInt(ColumnNames.PRODUCT_STOCK_AMOUNT);
        String brandName =   resultSet.getString(ColumnNames.PRODUCT_BRAND_NAME);
        int categoryId =     resultSet.getInt(ColumnNames.CATEGORY_ID);

        reviewDao.getByProductId(id).stream().mapToInt(Review::getRating).average().ifPresent(product::setRating);
        product.setId(id);
        product.setPrice(price);
        product.setModel(model);
        product.setDescription(productDesc);
        product.setWarranty(warranty);
        product.setStockAmount(stock);
        product.setBrand(brandName);
        product.setCategory(categoryDao.getById(categoryId));
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
