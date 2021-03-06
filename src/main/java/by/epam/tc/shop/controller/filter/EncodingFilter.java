package by.epam.tc.shop.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * The {@code RoleAccessFilter} class represents encoding filter
 *
 * @author Pavel Voronin
 * @version 1.0
 */
@WebFilter(urlPatterns = "/controller")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        chain.doFilter(servletRequest, servletResponse);
    }
}