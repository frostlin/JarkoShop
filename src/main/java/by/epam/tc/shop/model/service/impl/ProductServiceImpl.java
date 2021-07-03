package by.epam.tc.shop.model.service.impl;

import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.ProductDao;
import by.epam.tc.shop.model.dao.impl.ProductDaoImpl;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.service.ProductService;
import by.epam.tc.shop.model.service.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao = ProductDaoImpl.getInstance();

    @Override
    public List<Product> getProductPage(int pageNumber, int recordsPerPage) throws ServiceException {
        try {
            int start = (pageNumber - 1) * recordsPerPage;
            return productDao.getRange(start, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductPageSortedByPrice(int pageNumber, int recordsPerPage, String direction) throws ServiceException {
        try {
            int start = (pageNumber - 1) * recordsPerPage;
            return productDao.getRangeOrderByPrice(start, recordsPerPage, direction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public List<Product> getProductPageSortedByAvgRating(int pageNumber, int recordsPerPage) throws ServiceException {
        try {
            int start = (pageNumber - 1) * recordsPerPage;
            return productDao.getRangeOrderByAvgRating(start, recordsPerPage);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductPageByCategory(int pageNumber, int recordsPerPage, int categoryId) throws ServiceException {
        try {
            int start = (pageNumber - 1) * recordsPerPage;
            return productDao.getRangeByCategory(start, recordsPerPage, categoryId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductPageByCategorySortedByPrice(int pageNumber, int recordsPerPage, int categoryId, String direction) throws ServiceException {
        try {
            int start = (pageNumber - 1) * recordsPerPage;
            return productDao.getRangeByCategoryOrderByPrice(start, recordsPerPage, categoryId, direction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductPageByCategorySortedByAvgRating(int pageNumber, int recordsPerPage, int categoryId) throws ServiceException {
        try {
            int start = (pageNumber - 1) * recordsPerPage;
            return productDao.getRangeByCategoryOrderByAvgRating(start, recordsPerPage, categoryId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getLatest(int count) throws ServiceException {
        try {
            return productDao.getRange(0, count);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product getProductById(int id) throws ServiceException {
        try {
            return productDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getProductCount() throws ServiceException {
        try {
            return productDao.getProductCount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public int getProductCount(int categoryId) throws ServiceException {
        try {
            return productDao.getProductCount(categoryId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getProductPageBySearch(
            int pageNumber, int recordsPerPage, int categoryId, String searchString) throws ServiceException {
        try {
            int start = (pageNumber - 1) * recordsPerPage;

            return productDao.getRange(1, 1);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
