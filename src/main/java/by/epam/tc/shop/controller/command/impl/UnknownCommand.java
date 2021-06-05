package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.controller.PagePath;

import javax.servlet.http.HttpServletRequest;

public class UnknownCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PagePath.HOME;
    }
}
