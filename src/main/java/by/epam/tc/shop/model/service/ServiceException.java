package by.epam.tc.shop.model.service;

public class ServiceException extends Exception{
    public ServiceException(){}

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    public ServiceException(String message){
        super(message);
    }
}