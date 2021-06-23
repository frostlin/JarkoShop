package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Review;

import java.sql.Date;
import java.util.List;

public interface ReviewDao {

    int add(int userId, int productId, String content, int rating) throws DaoException;
    List<Review> getByProductId(int productId)throws DaoException;
    List<Review> getByUserId(int userId)throws DaoException;

}
