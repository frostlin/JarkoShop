package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.CurrencyMultipliers;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameter.NEW_LOCALE);
        HttpSession session = request.getSession();

        switch (locale){
            case "ru_RU":
                session.setAttribute(SessionAttribute.CONVERTING_MULTIPLIER, CurrencyMultipliers.BYN);
                break;
            case "en_US":
                session.setAttribute(SessionAttribute.CONVERTING_MULTIPLIER, CurrencyMultipliers.USD);
                break;
            case "ja_JP":
                session.setAttribute(SessionAttribute.CONVERTING_MULTIPLIER, CurrencyMultipliers.JPY);
                break;
        }
        session.setAttribute(SessionAttribute.LOCALE, locale);
        return session.getAttribute(SessionAttribute.CURRENT_PAGE).toString();
    }
}
