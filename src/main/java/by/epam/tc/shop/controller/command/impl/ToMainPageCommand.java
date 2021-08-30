package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.impl.CategoryDaoImpl;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToMainPageCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    private static final String LOCALE = "en_US";
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.ROLE) == null) {
            session.setAttribute(SessionAttribute.ROLE, "guest");
        }
        if (session.getAttribute(SessionAttribute.LOCALE) == null) {
            session.setAttribute(SessionAttribute.LOCALE, "ru_RU");
        }
        if (session.getAttribute(SessionAttribute.CATEGORY_LIST) == null){
            CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
            try{
                session.setAttribute(SessionAttribute.CATEGORY_LIST, categoryDao.getCategories());
            } catch (DaoException e) {
                logger.error("Error while setting up category list on main page", e);
            }
        }
        return PagePath.MAIN;
    }
}
