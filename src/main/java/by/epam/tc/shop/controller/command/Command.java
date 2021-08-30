package by.epam.tc.shop.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code Command} interface represents command pattern
 *
 * @author Pavel Voronin
 * @version 1.0
 */
public interface Command {
    /**
     * Execute command
     *
     * @param request the http request
     * @return the string containing the path to jsp
     */
    String execute(HttpServletRequest request);
}
