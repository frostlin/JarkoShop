package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.service.ProductService;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToProductPageCommand implements Command {
    public static final Logger logger = LogManager.getLogger();
    private static final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter(RequestParameter.ADDED_PRODUCT_ID));

        try{
            Product product = productService.getProductById(productId);
            session.setAttribute(SessionAttribute.CURRENT_PRODUCT, product);
        } catch (ServiceException e){
            logger.error("Error occurred while getting product for product page", e);

        }

        return PagePath.TO_PRODUCT;

    }


}
