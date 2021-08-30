package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.ProductDao;
import by.epam.tc.shop.model.entity.*;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDaoImpl instance = new ProductDaoImpl();

    private static final ProductCharacteristicDaoImpl characteristicDao = ProductCharacteristicDaoImpl.getInstance();
    private static final CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
    private static final ReviewDaoImpl reviewDao = ReviewDaoImpl.getInstance();

    private static final String ADD = "INSERT INTO product (brand_id,category_id,price,model,description,warranty,stock_amount)" +
            " VALUES (?,?,?,?,?,?,?)";
    private static final String ADD_BRAND = "INSERT INTO brand (name) VALUES ?";
    private static final String ADD_PHOTO = "INSERT INTO photo (product_id,path) VALUES (?,?)";

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

    private static final String GET_RANGE_BY_CATEGORY = GET_ALL + " WHERE product.category_id LIKE ? " + ORDER_BY_ID + "DESC " + LIMIT_RANGE;
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
    private static final String GET_LATEST = GET_ALL + " ORDER BY product.id DESC LIMIT 1";

    private static final String GET_BY_BRAND = GET_ALL + " WHERE brand.id LIKE ?";
    private static final String GET_BY_ID = GET_ALL + " WHERE product.id LIKE ?";

    private static final String GET_PHOTO_LIST = "SELECT path FROM photo WHERE product_id LIKE ?";
    private static final String GET_BRAND_LIST = "SELECT * FROM brand";


    private ProductDaoImpl() {}
    public static ProductDaoImpl getInstance(){ return instance ; }

    @Override
    public int add(int brandId, int categoryId, float price, String model, String description, int warranty, int amount_stock) throws DaoException {
        int id = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD,Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, brandId);
            statement.setInt(2, categoryId);
            statement.setFloat(3, price);
            statement.setString(4, model);
            statement.setString(5, description);
            statement.setInt(6, warranty);
            statement.setInt(7, amount_stock);

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                id = resultSet.getInt(1);

        } catch(SQLException e){
            throw new DaoException("Error adding product " + model, e);
        }
        return id;
    }

    @Override
    public int addBrand(String name) throws DaoException {
        int id = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_BRAND,Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, name);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                id = resultSet.getInt(1);

        } catch(SQLException e){
            throw new DaoException("Error adding brand " + name, e);
        }
        return id;
    }

    @Override
    public int addPhoto(int productId, String name) throws DaoException {
        int id = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PHOTO,Statement.RETURN_GENERATED_KEYS))
        {
            statement.setInt(1, productId);
            statement.setString(1, name);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                id = resultSet.getInt(1);

        } catch(SQLException e){
            throw new DaoException("Error adding photo for product " + productId, e);
        }
        return id;
    }

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
    public Product getLatest() throws DaoException {
        Product product = null;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_LATEST);
             ResultSet resultSet = statement.executeQuery())
        {
            if (resultSet.next())
                product = getProductFromResultSet(resultSet);
        } catch(SQLException e){
            throw new DaoException("Error getting latest product", e);
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
             PreparedStatement statement = connection.prepareStatement(GET_PHOTO_LIST))
        {
            statement.setInt(1, productId);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                photos.add(resultSet.getString(ColumnNames.PHOTO_PATH));
            }

        } catch(SQLException e){
            throw new DaoException("Error getting photos for product " + productId, e);
        }
        return photos;
    }
    @Override
    public List<Brand> getBrandList() throws DaoException {
        List<Brand> brands = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BRAND_LIST);
             ResultSet resultSet = statement.executeQuery())
        {
            while(resultSet.next()){
                Brand brand = new Brand();
                brand.setId(resultSet.getInt("id"));
                brand.setName(resultSet.getString(ColumnNames.PRODUCT_BRAND_NAME));
                brands.add(brand);
            }
        } catch(SQLException e){
            throw new DaoException("Error getting brands ", e);
        }
        return brands;
    }


}
