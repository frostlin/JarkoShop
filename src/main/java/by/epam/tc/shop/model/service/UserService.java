package by.epam.tc.shop.model.service;

import by.epam.tc.shop.model.entity.User;

import java.util.List;

public interface UserService {
    public boolean create(String login, String password, String email) throws ServiceException;
    public List<User> getAll() throws ServiceException;

}
