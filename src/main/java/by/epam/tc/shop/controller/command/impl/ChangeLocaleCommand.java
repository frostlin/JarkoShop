package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameter.CHANGE_LOCALE);

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOCALE, locale);
        return session.getAttribute(SessionAttribute.CURRENT_PAGE).toString();
    }
}
