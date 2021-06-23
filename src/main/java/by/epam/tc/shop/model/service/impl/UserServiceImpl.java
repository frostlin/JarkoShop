package by.epam.tc.shop.model.service.impl;

import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.UserDao;
import by.epam.tc.shop.model.dao.impl.UserDaoImpl;
import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.UserService;
import by.epam.tc.shop.model.service.impl.validator.UserValidator;
import by.epam.tc.shop.util.PasswordEncryptor;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService  {
    private final UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createUser(String login, String password, String email) throws ServiceException {
        boolean isCreated = false;
        try {
            if (UserValidator.isLoginCorrect(login)
                    && UserValidator.isPasswordCorrect(password)
                    && UserValidator.isEmailCorrect(email))
            {
                if (userDao.getByLogin(login).isPresent() || userDao.getByEmail(email).isPresent()){
                    return isCreated;
                }
                Optional<String> encryptedPassword = PasswordEncryptor.encryptPassword(password);
                if (encryptedPassword.isPresent()){
                    isCreated = userDao.add(2, email, login, encryptedPassword.get());
                }
            }
        } catch (DaoException e){
            throw new ServiceException(e);
        }

        return isCreated;
    }

    @Override
    public Optional<User> authorizeUser(String login, String password) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            if (UserValidator.isLoginCorrect(login) && UserValidator.isPasswordCorrect(password)) {
                Optional<User> optionalUser = userDao.getByLogin(login);
                if (optionalUser.isPresent()) {
                    String rightPassword = optionalUser.get().getPassword();
                    Optional<String> encPassword = PasswordEncryptor.encryptPassword(password);

                    if (encPassword.isPresent() && rightPassword.equals(encPassword.get()))
                        user = optionalUser;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }


}
