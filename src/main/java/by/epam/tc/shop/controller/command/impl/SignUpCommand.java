package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.UserService;
import by.epam.tc.shop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession();

        String login = request.getParameter(RequestParameter.LOGIN);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String password2 = request.getParameter(RequestParameter.CONFIRM_PASSWORD);

        if (!password.equals(password2)) {
            page = PagePath.SIGN_UP;
            request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "signup.passwordsNotMatching");
        } else {
            try {
                boolean isUserCreated = userService.createUser(login, password, email);
                if (isUserCreated) {
                    page = PagePath.MAIN;
                } else {
                    page = PagePath.SIGN_UP;
                    request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "signup.incorrectSignup");
                }
            } catch (ServiceException e) {
                logger.error("Error occurred while sign up user", e);
                request.setAttribute(RequestAttribute.SIGN_UP_ERROR, "signup.errorSignup");
                page = (String)session.getAttribute(SessionAttribute.CURRENT_PAGE);
            }
        }
        return page;
    }
}
