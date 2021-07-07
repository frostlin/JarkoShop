package by.epam.tc.shop.controller.filter;

import by.epam.tc.shop.controller.CurrencyMultipliers;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.impl.CategoryDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"})
public class CatalogFilter implements Filter {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.LOCALE) == null) {
            session.setAttribute(SessionAttribute.LOCALE, "ru_RU");
        }
        if (session.getAttribute(SessionAttribute.CONVERTING_MULTIPLIER) == null) {
            session.setAttribute(SessionAttribute.CONVERTING_MULTIPLIER, CurrencyMultipliers.BYN);
        }
        if (session.getAttribute(SessionAttribute.CURRENT_CATEGORY) == null)
            session.setAttribute(SessionAttribute.CURRENT_CATEGORY, 0);

        if (session.getAttribute(SessionAttribute.CATEGORY_LIST) == null){
            CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
            try{
                session.setAttribute(SessionAttribute.CATEGORY_LIST, categoryDao.getCategories());
            } catch (DaoException e) {
                logger.error("Error while setting up race list", e);
            }
        }
        chain.doFilter(request,response);
    }
}
