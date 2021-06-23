package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.ReviewDao;
import by.epam.tc.shop.model.entity.Order;
import by.epam.tc.shop.model.entity.Review;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    private static final ReviewDaoImpl instance = new ReviewDaoImpl();
    private static final UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static final ProductDaoImpl productDao = ProductDaoImpl.getInstance();

    private static final String ADD = "INSERT INTO review (user_id,product_id,content,rating) VALUES (?,?,?,?)";
    private static final String GET_BY_PRODUCT = "SELECT id,user_id,product_id,content,date,rating" +
            " FROM review WHERE product_id LIKE ?";
    private static final String GET_BY_USER = "SELECT id,user_id,product_id,content,date,rating" +
            " FROM review WHERE user_id LIKE ?";

    private ReviewDaoImpl(){}
    public static ReviewDaoImpl getInstance(){ return instance; }

    @Override
    public int add(int userId, int productId, String content, int rating) throws DaoException {
        int id = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);)
        {
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setString(3, content);
            statement.setInt(4, rating);

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                id = resultSet.getInt(1);
        } catch(SQLException e){
            throw new DaoException("Error inserting review ", e);
        }
        return id;
    }

    @Override
    public List<Review> getByProductId(int productId) throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_PRODUCT);)
        {
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
                reviews.add(getReviewFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting orders for user by id=" + productId, e);
        }
        return reviews;
    }

    @Override
    public List<Review> getByUserId(int userId) throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_USER);)
        {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
                reviews.add(getReviewFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting orders for user by id=" + userId, e);
        }
        return reviews;
    }


    private Review getReviewFromResultSet(ResultSet resultSet) throws SQLException, DaoException{
        Review review = new Review();

        int id =             resultSet.getInt(ColumnNames.REVIEW_ID);
        int userId =         resultSet.getInt(ColumnNames.REVIEW_USER_ID);
        int productId =      resultSet.getInt(ColumnNames.REVIEW_PRODUCT_ID);
        String content =     resultSet.getString(ColumnNames.REVIEW_CONTENT);
        Date date =          resultSet.getDate(ColumnNames.REVIEW_DATE);
        int rating =         resultSet.getInt(ColumnNames.REVIEW_RATING);

        review.setId(id);
        review.setUser(userDao.getById(userId).get());
        review.setProduct(productDao.getById(productId));
        review.setContent(content);
        review.setDate(date);
        review.setRating(rating);
        return review;
    }
}
