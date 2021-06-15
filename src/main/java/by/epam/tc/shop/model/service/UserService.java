package by.epam.tc.shop.model.service;

import by.epam.tc.shop.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public boolean createUser(String login, String password, String email) throws ServiceException;
    public Optional<User> authorizeUser(String login, String password) throws ServiceException;
    public List<User> getAllUsers() throws ServiceException;

}
