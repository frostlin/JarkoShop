package by.epam.tc.shop.model.dao;

public class DaoException extends Exception{
    public DaoException(){}

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause){
        super(cause);
    }

    public DaoException(String message){
        super(message);
    }
}
