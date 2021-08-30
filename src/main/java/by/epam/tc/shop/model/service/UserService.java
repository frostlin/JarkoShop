package by.epam.tc.shop.model.service;

import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean createUser(String login, String password, String email) throws ServiceException;
    Optional<User> authorizeUser(String login, String password) throws ServiceException;
    List<User> getAllUsers() throws ServiceException;
    List<User> getUserPage(int pageNumber, int recordsPerPage) throws ServiceException;

    int getUserCount() throws ServiceException;
}
