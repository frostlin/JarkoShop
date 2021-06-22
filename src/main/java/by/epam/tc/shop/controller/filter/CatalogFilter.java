package by.epam.tc.shop.controller.filter;

import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"})
public class CatalogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.LOCALE) == null) {
            session.setAttribute(SessionAttribute.LOCALE, "ru_RU");
        }
        if (session.getAttribute(SessionAttribute.CURRENT_PRODUCT_PAGE) == null
                || request.getParameter(RequestParameter.CURRENT_CATEGORY) != null)
            session.setAttribute(SessionAttribute.CURRENT_PRODUCT_PAGE, 1);
        if (session.getAttribute(SessionAttribute.CURRENT_PRODUCT_PER_PAGE) == null)
            session.setAttribute(SessionAttribute.CURRENT_PRODUCT_PER_PAGE, 2);
        if (session.getAttribute(SessionAttribute.CURRENT_CATEGORY) == null)
            session.setAttribute(SessionAttribute.CURRENT_CATEGORY, 0);

        chain.doFilter(request,response);
    }
}
