package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;


public class SearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String searchString = request.getParameter(RequestParameter.SEARCH_STRING);

        String page = PagePath.SEARCH_RESULT;
        request.setAttribute(RequestAttribute.SEARCH_RESULT, "Search string is " + searchString);

        return page;
    }
}
