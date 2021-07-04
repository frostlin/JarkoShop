package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.entity.Review;
import by.epam.tc.shop.model.service.ProductService;
import by.epam.tc.shop.model.service.ReviewService;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;
import by.epam.tc.shop.model.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToProductPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final ProductService productService = ProductServiceImpl.getInstance();
    private static final ReviewService reviewService = ReviewServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int productId = Integer.parseInt(request.getParameter(RequestParameter.ADDED_PRODUCT_ID));

        try{
            Product product = productService.getProductById(productId);
            session.setAttribute(SessionAttribute.CURRENT_PRODUCT, product);
            List<Review> reviews = reviewService.getForProduct(productId);
            request.setAttribute(RequestAttribute.REVIEWS, reviews);
        } catch (ServiceException e){
            logger.error("Error occurred while getting product for product page", e);
        }

        return PagePath.TO_PRODUCT;

    }


}
