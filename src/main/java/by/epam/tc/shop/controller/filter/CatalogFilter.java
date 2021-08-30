package by.epam.tc.shop.controller.filter;

import by.epam.tc.shop.controller.CurrencyMultipliers;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.model.dao.DaoException;
import by.epam.tc.shop.model.dao.impl.CategoryDaoImpl;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The {@code CatalogFilter} class represents constants filter when accessing catalog
 *
 * @author Pavel Voronin
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/controller"})
public class CatalogFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
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
            session.setAttribute(SessionAttribute.CURRENT_CATEGORY, 1);

        if (session.getAttribute(SessionAttribute.CATEGORY_LIST) == null){
            CategoryDaoImpl categoryDao = CategoryDaoImpl.getInstance();
            try{
                session.setAttribute(SessionAttribute.CATEGORY_LIST, categoryDao.getCategories());
            } catch (DaoException e) {
                logger.error("Error while setting up race list", e);
            }
        }
        if (request.getParameter(RequestAttribute.LATEST_PRODUCT) == null){
            try{
                ProductServiceImpl productService = ProductServiceImpl.getInstance();
                request.setAttribute(RequestAttribute.LATEST_PRODUCT, productService.getLatest());
            } catch (ServiceException e) {
                logger.error("Error while setting up latest product on main page", e);
            }
        }
        chain.doFilter(request,response);
    }
}
