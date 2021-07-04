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
import java.util.Optional;

public class SignInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        HttpSession session = request.getSession();

        try {
            Optional<User> user = userService.authorizeUser(login, password);
            if (user.isPresent()) {

                session.setAttribute(SessionAttribute.CURRENT_USER, user.get());
                session.setAttribute(SessionAttribute.ROLE, user.get().getRole().getRoleName());

                page = PagePath.MAIN;
            } else {
                page = PagePath.SIGN_IN;
                request.setAttribute(RequestAttribute.SIGN_IN_ERROR, "signup.incorrectSignin");
            }
        } catch (ServiceException e) {
            logger.error("Error occurred while sign in user", e);
            request.setAttribute(RequestAttribute.SIGN_IN_ERROR, "signup.errorSignin");
            page = (String)session.getAttribute(SessionAttribute.CURRENT_PAGE);
        }
        return page;
    }
}
