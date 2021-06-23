package by.epam.tc.shop.model.service;

import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.entity.Review;

import java.util.List;

public interface ReviewService {
    int commit(int userId, int productId, String content, int rating) throws ServiceException;
    List<Review> getForProduct(int productId)throws ServiceException;
    List<Review> getForUser(int userId)throws ServiceException;

}
