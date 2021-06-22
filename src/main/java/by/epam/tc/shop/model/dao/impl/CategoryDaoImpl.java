package by.epam.tc.shop.model.dao.impl;

import by.epam.tc.shop.model.dao.CategoryDao;
import by.epam.tc.shop.model.dao.ColumnNames;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.entity.Category;
import by.epam.tc.shop.model.entity.ProductCharacteristic;
import by.epam.tc.shop.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private static final CategoryDaoImpl instance = new CategoryDaoImpl();
    private static final ProductCharacteristicDaoImpl characteristicDao = ProductCharacteristicDaoImpl.getInstance();

    private static final String GET_CATEGORIES = "SELECT * FROM category";
    private static final String GET_SUBCATEGORIES = "SELECT * FROM category WHERE category_id LIKE ?";

    private CategoryDaoImpl() {}
    public static CategoryDaoImpl getInstance(){ return instance; }

    @Override
    public List<Category> getCategories() throws DaoException {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CATEGORIES);
             ResultSet resultSet = statement.executeQuery())
        {
            while(resultSet.next())
                categories.add(getCategoryFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return categories;
    }
    public List<Category> getSubCategories(int categoryId) throws DaoException {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_SUBCATEGORIES))
        {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
                categories.add(getCategoryFromResultSet(resultSet));
        } catch(SQLException e){
            throw new DaoException("Error getting all users data ", e);
        }
        return categories;
    }

    private Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException, DaoException{
        Category category = new Category();

        int id =             resultSet.getInt(ColumnNames.CATEGORY_ID);
        String name  =       resultSet.getString(ColumnNames.CATEGORY_NAME);
        String desc =        resultSet.getString(ColumnNames.CATEGORY_DESCRIPTION);
        int subId =          resultSet.getInt(ColumnNames.CATEGORY_SUBCATEGORY_ID);
//        List<Category> subCategories = null;
//        if (subId > 0)
//            subCategories = getSubCategories(id);
        List<ProductCharacteristic> characteristics = characteristicDao.getProductCharacteristicsForCategory(id);


        category.setId(id);
        category.setName(name);
        category.setCharacteristics(characteristics);
        category.setDescription(desc);
        //category.setCategories(subCategories);
        return category;
    }

}
