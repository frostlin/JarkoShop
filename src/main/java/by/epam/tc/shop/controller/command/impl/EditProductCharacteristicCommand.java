package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditProductCharacteristicCommand implements Command {
    ProductServiceImpl productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String characteristicValue = request.getParameter(RequestParameter.CHARACTERISTIC_VALUE);
        int characteristicId = Integer.parseInt(request.getParameter(RequestParameter.CHARACTERISTIC_ID));
        int productId = Integer.parseInt(request.getParameter(RequestParameter.PRODUCT_ID));

        try{
            productService.updateProductCharacteristic(characteristicId, productId, characteristicValue);
            Product product = productService.getProductById(productId);
            session.setAttribute(SessionAttribute.CURRENT_PRODUCT, product);
        } catch (ServiceException e){
            request.setAttribute(RequestAttribute.ORDER_ERROR, "cart.orderError");
        }

        return PagePath.TO_PRODUCT;
    }
}
