package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToAddNewProductCommand implements Command {
    ProductServiceImpl productService = ProductServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        try{
            session.setAttribute(SessionAttribute.BRAND_LIST, productService.getBrandList());
        } catch (ServiceException e){
            request.setAttribute(RequestAttribute.ORDER_ERROR, "cart.orderError");
        }

        return PagePath.ADD_NEW_PRODUCT;
    }

}
