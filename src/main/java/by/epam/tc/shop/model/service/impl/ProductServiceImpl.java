package by.epam.tc.shop.model.service.impl;

import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.ProductCharacteristicDao;
import by.epam.tc.shop.model.dao.ProductDao;
import by.epam.tc.shop.model.dao.impl.ProductCharacteristicDaoImpl;
import by.epam.tc.shop.model.dao.impl.ProductDaoImpl;
import by.epam.tc.shop.model.entity.Brand;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.service.ProductService;
import by.epam.tc.shop.model.service.ServiceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final ProductServiceImpl instance = new ProductServiceImpl();
    private static final ProductDao productDao = ProductDaoImpl.getInstance();
    private static final ProductCharacteristicDao productCharacteristicDao = ProductCharacteristicDaoImpl.getInstance();

    private ProductServiceImpl(){};
    public static ProductServiceImpl getInstance(){return instance; }

    @Override
    public int addNewProduct(int brandId, int categoryId, float price, String model, String description, int warranty, int amount_stock) throws ServiceException {
        try {
            int productId = productDao.add(brandId,categoryId,price,model,description,warranty,amount_stock);
            List<Integer> ids = productCharacteristicDao.getCharacteristicsIdByCategory(categoryId);
            for (int id : ids)
                productCharacteristicDao.initializeProductCharacteristic(id,productId);
            return productId;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public int addNewBrand(String name) throws ServiceException {
        return 0;
    }

    @Override
    public int addPhotos(int productId, String name) throws ServiceException {
        return 0;
    }

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
    public Product getProductById(int id) throws ServiceException {
        try {
            return productDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Product getLatest() throws ServiceException {
        try {
            return productDao.getLatest();
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

    @Override
    public List<Brand> getBrandList() throws ServiceException {
        try {
            return productDao.getBrandList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateProductCharacteristic(int characteristicId, int productId, String value) throws ServiceException {
        try {
            return productCharacteristicDao.updateProductCharacteristic(characteristicId, productId, value);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateCharacteristic(int characteristicId, String productName, String productDesc) throws ServiceException {
        try {
            return productCharacteristicDao.updateCharacteristic(characteristicId, productName, productDesc);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int addNewCharacteristic(int categoryId, String name, String desc) throws ServiceException {
        try {
            return productCharacteristicDao.add(categoryId, name, desc);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
