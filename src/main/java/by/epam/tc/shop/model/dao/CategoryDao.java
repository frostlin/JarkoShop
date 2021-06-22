package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> getCategories() throws DaoException;
}
