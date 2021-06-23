package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
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

        int pageNumber = (int)session.getAttribute(SessionAttribute.CURRENT_ITEMS_PAGE);
        int itemsPerPage = (int)session.getAttribute(SessionAttribute.CURRENT_ITEMS_PER_PAGE);
        int categoryId = (int)session.getAttribute(SessionAttribute.CURRENT_CATEGORY);

        String nextPageNumber = request.getParameter(RequestParameter.NEXT_ITEM_PAGE);
        if (nextPageNumber != null){
            pageNumber = Integer.parseInt(nextPageNumber);
            session.setAttribute(SessionAttribute.CURRENT_ITEMS_PAGE, pageNumber);
        }
        String nextCategoryId = request.getParameter(RequestParameter.CURRENT_CATEGORY);
        if (nextCategoryId != null){
            categoryId = Integer.parseInt(nextCategoryId);
            session.setAttribute(SessionAttribute.CURRENT_CATEGORY, categoryId);
        }

        try {
            if (categoryId == 0){
                int productCount = productService.getProductCount();
                int itemPageCount = (int) Math.ceil(productCount * 1.0 / itemsPerPage);

                session.setAttribute(SessionAttribute.PAGE_COUNT, itemPageCount);
                session.setAttribute(SessionAttribute.TOTAL_PRODUCT_COUNT, productCount);
                session.setAttribute(SessionAttribute.CATALOG_PAGE_PRODUCTS,
                        productService.getProductPage(pageNumber, itemsPerPage));
            } else {
                int productCount = productService.getProductCount(categoryId);
                int itemPageCount = (int) Math.ceil(productCount * 1.0 / itemsPerPage);

                session.setAttribute(SessionAttribute.PAGE_COUNT, itemPageCount);
                session.setAttribute(SessionAttribute.TOTAL_PRODUCT_COUNT, productCount);
                session.setAttribute(SessionAttribute.CATALOG_PAGE_PRODUCTS,
                        productService.getProductPageByCategory(pageNumber, itemsPerPage, categoryId));
            }


        } catch (ServiceException e) {
            logger.error("Error while setting up race list", e);
        }

        return PagePath.CATALOG;
    }
}
