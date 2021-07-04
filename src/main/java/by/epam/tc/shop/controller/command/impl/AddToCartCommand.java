package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.service.CartItemService;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.CartItemServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddToCartCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    CartItemService cartItemService = CartItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        User user = (User)session.getAttribute(SessionAttribute.CURRENT_USER);
        int productId = Integer.parseInt(request.getParameter(RequestParameter.ADDED_PRODUCT_ID));
        try {
            if (cartItemService.getCartItem(productId, user.getId()) != null){
                request.setAttribute(RequestAttribute.ADD_PRODUCT_TO_CART_MESSAGE,"catalog.alreadyAdded");
            } else {
                cartItemService.addProductToCart(productId, user.getId());
                user.getCart().add(cartItemService.getCartItem(productId, user.getId()));

                request.setAttribute(RequestAttribute.ADD_PRODUCT_TO_CART_MESSAGE,"catalog.successfullyAdded");
            }
        } catch (ServiceException e) {
            logger.error("Error while adding product to cart", e);
            request.setAttribute(RequestAttribute.ADD_PRODUCT_TO_CART_MESSAGE,"catalog.notAdded");
        }
        return session.getAttribute(SessionAttribute.CURRENT_PAGE).toString();
    }
}
