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
import by.epam.tc.shop.model.service.impl.CartItemServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteCartItemCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final CartItemService cartItemService = CartItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.CURRENT_USER);
        if (request.getParameter(RequestParameter.PRODUCT_ID_TO_DELETE ) != null){
            int productId = Integer.parseInt(request.getParameter(RequestParameter.PRODUCT_ID_TO_DELETE));
            int userId = ((User)session.getAttribute(SessionAttribute.CURRENT_USER)).getId();

            try{
                cartItemService.deleteCartItem(productId, userId);
                user.getCart().removeIf(c -> c.getProduct().getId() == productId);
                session.setAttribute(SessionAttribute.CURRENT_USER, user);
            } catch (ServiceException e){
                logger.error("Error occurred while deleting cart item productId = " + productId + " userId = " + userId, e);
                request.setAttribute(RequestAttribute.DELETE_CART_ITEM, "cart.deleteError");
            }
        }

        float cartPrice = 0.0f;
        for (CartItem item : user.getCart())
            cartPrice += item.getProduct().getPrice() * item.getCount();

        session.setAttribute(SessionAttribute.CART_PRICE,cartPrice);
        session.setAttribute(SessionAttribute.CART_ITEMS,user.getCart());

        return PagePath.CART;
    }
}
