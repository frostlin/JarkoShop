package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.Address;
import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.service.OrderService;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.UserService;
import by.epam.tc.shop.model.service.impl.OrderServiceImpl;
import by.epam.tc.shop.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CheckoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final UserService userService = new UserServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.CURRENT_USER);
        Address shippingAddress = null;
        int orderId = 0;

        try{
            orderId = orderService.add(user.getId(),1,
                    Integer.parseInt(request.getParameter(RequestParameter.SHIPPING_ADDRESS)),
                    (float) session.getAttribute(SessionAttribute.CART_PRICE),
                    request.getParameter(RequestParameter.COMMENT),
                    (List<CartItem>) session.getAttribute(SessionAttribute.CART_ITEMS));

            user.getCart().clear();
            session.setAttribute(SessionAttribute.CART_ITEMS, new ArrayList<>());

        } catch (ServiceException e){
            logger.error("Error occurred while creating order", e);
            request.setAttribute(RequestAttribute.ORDER_ERROR, "cart.deleteError");
        }


        return PagePath.CART;
    }
}
