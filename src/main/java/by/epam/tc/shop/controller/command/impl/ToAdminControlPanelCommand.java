package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToAdminControlPanelCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        //HttpSession session = request.getSession();

        return PagePath.ADMIN_CONTROL_PANEL;
    }
}
