package by.epam.tc.shop.model.dao;

import by.epam.tc.shop.model.entity.Address;
import by.epam.tc.shop.model.entity.User;

import java.util.List;

public interface AdderssDao {

    List<Address> getByUser(int userId) throws DaoException;
    Address getById(int addressId) throws DaoException;
}
