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
import java.util.Set;

@WebFilter(urlPatterns = "/controller")
public class AccessFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final int ACCESS_FORBIDDEN_ERROR = 403;

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
        Command command = CommandProvider.defineCommand(commandName);

        String roleName = (String)session.getAttribute(SessionAttribute.ROLE);
        UserRole userRole;

        if (roleName == null)
            userRole = UserRole.GUEST;
        else
            userRole = UserRole.valueOf(roleName.toUpperCase());


        Set<Command> commands = switch (userRole) {
            case GUEST -> Access.GUEST.getCommands();
            case USER -> Access.USER.getCommands();
            case ADMIN -> Access.ADMIN.getCommands();
        };
        if (!commands.contains(command)) {
            logger.info("Role {} tried to access {} command", roleName, commandName);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(PagePath.SIGN_IN);
            dispatcher.forward(request, response);
        }
        chain.doFilter(servletRequest, servletResponse);
    }
}
