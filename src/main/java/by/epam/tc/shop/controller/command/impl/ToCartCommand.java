package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.service.CartItemService;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.UserService;
import by.epam.tc.shop.model.service.impl.CartItemServiceImpl;
import by.epam.tc.shop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToCartCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final CartItemService cartItemService = CartItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.CURRENT_USER);
        session.setAttribute(SessionAttribute.SUCCESSFUL_ORDER, null);

        float cartPrice = 0.0f;
        for (CartItem item : user.getCart())
            cartPrice += item.getProduct().getPrice() * item.getCount();

        session.setAttribute(SessionAttribute.CART_PRICE,cartPrice);
        session.setAttribute(SessionAttribute.CART_ITEMS,user.getCart());

        return PagePath.CART;
    }
}
