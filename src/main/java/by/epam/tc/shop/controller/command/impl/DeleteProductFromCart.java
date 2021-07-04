package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.service.ProductService;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.UserService;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;
import by.epam.tc.shop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteProductFromCart implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = PagePath.CART;

        return page;
    }
}
