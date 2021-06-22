package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.Category;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ToCatalogCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ProductServiceImpl productService = new ProductServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        int pageNumber = (int)session.getAttribute(SessionAttribute.CURRENT_PRODUCT_PAGE);
        int productPerPage = (int)session.getAttribute(SessionAttribute.CURRENT_PRODUCT_PER_PAGE);
        int categoryId = (int)session.getAttribute(SessionAttribute.CURRENT_CATEGORY);

        String nextPageNumber = request.getParameter(RequestParameter.NEXT_PRODUCT_PAGE);
        if (nextPageNumber != null){
            pageNumber = Integer.parseInt(nextPageNumber);
            session.setAttribute(SessionAttribute.CURRENT_PRODUCT_PAGE, pageNumber);
        }
        String nextCategoryId = request.getParameter(RequestParameter.CURRENT_CATEGORY);
        if (nextCategoryId != null){
            categoryId = Integer.parseInt(nextCategoryId);
            session.setAttribute(SessionAttribute.CURRENT_CATEGORY, categoryId);
        }

        try {
            if (categoryId == 0){
                int productCount = productService.getProductCount();
                int productPageCount = (int) Math.ceil(productCount * 1.0 / productPerPage);

                session.setAttribute(SessionAttribute.PRODUCT_PAGE_COUNT, productPageCount);
                session.setAttribute(SessionAttribute.TOTAL_PRODUCT_COUNT, productCount);
                session.setAttribute(SessionAttribute.CATALOG_PAGE_PRODUCTS,
                        productService.getProductPage(pageNumber, productPerPage));
            } else {
                int productCount = productService.getProductCount(categoryId);
                int productPageCount = (int) Math.ceil(productCount * 1.0 / productPerPage);

                session.setAttribute(SessionAttribute.PRODUCT_PAGE_COUNT, productPageCount);
                session.setAttribute(SessionAttribute.TOTAL_PRODUCT_COUNT, productCount);
                session.setAttribute(SessionAttribute.CATALOG_PAGE_PRODUCTS,
                        productService.getProductPageByCategory(pageNumber, productPerPage, categoryId));
            }


        } catch (ServiceException e) {
            logger.error("Error while setting up race list", e);
        }

        return PagePath.CATALOG;
    }
}
