package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.UserService;
import by.epam.tc.shop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page;

        String login = request.getParameter(RequestParameter.LOGIN);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String password2 = request.getParameter(RequestParameter.CONFIRM);

        if (!password.equals(password2)) {
            page = PagePath.SIGN_UP;
            request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "Passwords do not match!");
        } else {
            try {
                boolean isUserCreated = userService.createUser(email, login, password);
                if (isUserCreated) {
                    page = PagePath.MAIN;
                } else {
                    page = PagePath.SIGN_UP;
                    request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "Error registering user");
                }
            } catch (ServiceException e) {
                logger.error("Error occurred while sign up user", e);
                page = PagePath.HOME;
            }
        }


        return page;
    }
}
