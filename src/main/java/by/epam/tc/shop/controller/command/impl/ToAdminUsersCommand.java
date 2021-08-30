package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.PaginationConstants;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.UserService;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;
import by.epam.tc.shop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToAdminUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.CURRENT_ADMIN_USERS_PAGE) == null)
            session.setAttribute(SessionAttribute.CURRENT_ADMIN_USERS_PAGE, 1);
        if (session.getAttribute(SessionAttribute.CURRENT_ADMIN_USERS_PER_PAGE) == null)
            session.setAttribute(SessionAttribute.CURRENT_ADMIN_USERS_PER_PAGE, PaginationConstants.CURRENT_ADMIN_USERS_PER_PAGE);

        int pageNumber = (int)session.getAttribute(SessionAttribute.CURRENT_ADMIN_USERS_PAGE);
        int itemsPerPage = (int)session.getAttribute(SessionAttribute.CURRENT_ADMIN_USERS_PER_PAGE);

        String nextPageNumber = request.getParameter(RequestParameter.NEXT_ITEM_PAGE);
        if (nextPageNumber != null){
            pageNumber = Integer.parseInt(nextPageNumber);
            session.setAttribute(SessionAttribute.CURRENT_ADMIN_USERS_PAGE, pageNumber);
        }
        try {
            int userCount = userService.getUserCount();
            int itemPageCount = (int) Math.ceil(userCount * 1.0 / itemsPerPage);

            session.setAttribute(SessionAttribute.TOTAL_PAGE_COUNT, itemPageCount);
            session.setAttribute(SessionAttribute.TOTAL_ITEM_COUNT, userCount);
            session.setAttribute(SessionAttribute.CURRENT_ITEMS_RANGE,
                    userService.getUserPage(    pageNumber, itemsPerPage));
        } catch (ServiceException e) {
            logger.error("Error while setting up products list", e);
        }
        return PagePath.ADMIN_USERS;
    }
}
