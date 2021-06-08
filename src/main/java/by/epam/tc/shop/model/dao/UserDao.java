package by.epam.tc.shop.model.dao;

import java.sql.Date;

public interface UserDao {

    public boolean add(int role_id, String email , String login, String password, Date date_registered) throws DaoException;

}
