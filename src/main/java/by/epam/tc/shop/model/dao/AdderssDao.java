package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Address;
import by.epam.tc.shop.model.entity.User;

import java.util.List;

public interface AdderssDao {

    public List<Address> getByUser(int userId) throws DaoException;

}
