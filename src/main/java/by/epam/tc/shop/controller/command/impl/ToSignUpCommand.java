package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ToSignUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.SIGN_UP;
    }
}
