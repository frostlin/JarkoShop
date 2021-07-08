package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddNewProductCommand implements Command {
    ProductServiceImpl productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        String newCategory = request.getParameter(RequestParameter.ADD_PRODUCT_NEW_CATEGORY);
        int selectedCategory = Integer.parseInt(request.getParameter(RequestParameter.ADD_PRODUCT_SELECTED_CATEGORY));
        String newBrand = request.getParameter(RequestParameter.ADD_PRODUCT_NEW_BRAND);
        int selectedBrand = Integer.parseInt(request.getParameter(RequestParameter.ADD_PRODUCT_SELECTED_BRAND));
        float price = Float.parseFloat(request.getParameter(RequestParameter.ADD_PRODUCT_PRICE));
        String model = request.getParameter(RequestParameter.ADD_PRODUCT_MODEL);
        String description = request.getParameter(RequestParameter.ADD_PRODUCT_DESCRIPTION);
        int warranty = Integer.parseInt(request.getParameter(RequestParameter.ADD_PRODUCT_WARRANTY));
        int amountStock = Integer.parseInt(request.getParameter(RequestParameter.ADD_PRODUCT_AMOUNT_STOCK));
        //String file = request.getParameter(RequestParameter.ADD_PRODUCT_PHOTO)


        HttpSession session = request.getSession();
        try{
            productService.addNewProduct(selectedBrand, selectedCategory, price, model, description, warranty, amountStock);
        } catch (ServiceException e){
            request.setAttribute(RequestAttribute.ORDER_ERROR, "cart.orderError");
        }


        return PagePath.ADD_NEW_PRODUCT;
    }
}
