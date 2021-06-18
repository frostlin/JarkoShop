package by.epam.tc.shop.model.service.impl;

import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.ProductDao;
import by.epam.tc.shop.model.dao.impl.ProductDaoImpl;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.service.ProductService;
import by.epam.tc.shop.model.service.ServiceException;

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
    public int getProductCount() throws ServiceException {
        try {
            return productDao.getProductCount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
