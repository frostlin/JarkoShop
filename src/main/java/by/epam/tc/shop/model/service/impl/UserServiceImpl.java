package by.epam.tc.shop.model.service.impl;

import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.UserDao;
import by.epam.tc.shop.model.dao.impl.UserDaoImpl;
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
    public List<User> getAll() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(String login, String password, String email) throws ServiceException {
        boolean isCreated = false;
        try {
            if (UserValidator.isLoginCorrect(login)
                    && UserValidator.isPasswordCorrect(password)
                    && UserValidator.isEmailCorrect(email))
            {
                if (userDao.isLoginRegistered(login) || userDao.isEmailRegistered(email)){
                    return isCreated;
                }
                Optional<String> encryptedPassword = PasswordEncryptor.encryptPassword(password);
                if (encryptedPassword.isPresent()){
                    isCreated = userDao.add(1, email, login, encryptedPassword.get());
                }
            }
        } catch (DaoException e){
            throw new ServiceException(e);
        }

        return isCreated;
    }

}
