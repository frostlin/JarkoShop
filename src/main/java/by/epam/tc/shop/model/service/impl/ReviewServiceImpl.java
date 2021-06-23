package by.epam.tc.shop.model.service.impl;

import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.ReviewDao;
import by.epam.tc.shop.model.dao.impl.ReviewDaoImpl;
import by.epam.tc.shop.model.entity.Review;
import by.epam.tc.shop.model.service.ReviewService;
import by.epam.tc.shop.model.service.ServiceException;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    ReviewDao reviewDao = ReviewDaoImpl.getInstance();

    @Override
    public int commit(int userId, int productId, String content, int rating) throws ServiceException {
        try {
            return reviewDao.add(userId, productId, content, rating);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Review> getForProduct(int productId) throws ServiceException {
        try {
            return reviewDao.getByProductId(productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Review> getForUser(int userId) throws ServiceException {
        try {
            return reviewDao.getByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
