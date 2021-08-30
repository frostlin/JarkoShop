package by.epam.tc.shop.controller.filter;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.controller.command.CommandProvider;
import by.epam.tc.shop.model.entity.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * The {@code AccessFilter} class represents user asses filter by roles
 *
 * @author Pavel Voronin
 * @version 1.0
 */
@WebFilter(urlPatterns = "/controller")
public class AccessFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if (session.getAttribute(SessionAttribute.ROLE) == null) {
            session.setAttribute(SessionAttribute.ROLE, "guest");
        }

        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = CommandProvider.getCommand(commandName);

        String roleName = (String)session.getAttribute(SessionAttribute.ROLE);
        UserRole userRole;

        if (roleName == null)
            userRole = UserRole.GUEST;
        else
            userRole = UserRole.valueOf(roleName.toUpperCase());


        List<Command> commands = switch (userRole) {
            case GUEST -> Access.GUEST.getCommands();
            case USER -> Access.USER.getCommands();
            case ADMIN -> Access.ADMIN.getCommands();
        };
        if (!commands.contains(command)) {
            logger.info("Role {} tried to access {} command", roleName, commandName);
            response.sendRedirect(PagePath.REDIRECT_SIGN_IN);
            return;
        }
        chain.doFilter(servletRequest, servletResponse);
    }
}
