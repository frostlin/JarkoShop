package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.CartItem;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.service.OrderService;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CheckoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.CURRENT_USER);
        try{
            int orderId = orderService.add(user.getId(),1,
                    Integer.parseInt(request.getParameter(RequestParameter.SHIPPING_ADDRESS_ID)),
                    (float) session.getAttribute(SessionAttribute.CART_PRICE),
                    request.getParameter(RequestParameter.COMMENT),
                    (List<CartItem>)session.getAttribute(SessionAttribute.CART_ITEMS));

            user.getCart().clear();
            session.setAttribute(SessionAttribute.CART_ITEMS, new ArrayList<>());
            session.setAttribute(SessionAttribute.SUCCESSFUL_ORDER, "t");

        } catch (ServiceException e){
            logger.error("Error occurred while creating order", e);
            request.setAttribute(RequestAttribute.ORDER_ERROR, "cart.orderError");
        }
        return PagePath.CART;
    }
}
