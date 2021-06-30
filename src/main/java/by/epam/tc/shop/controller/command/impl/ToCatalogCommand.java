package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.PaginationConstants;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class ToCatalogCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ProductServiceImpl productService = new ProductServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.CURRENT_PRODUCTS_PAGE) == null
                || request.getParameter(RequestParameter.CURRENT_CATEGORY) != null
                || request.getParameter(RequestParameter.SEARCH_STRING) != null)
            session.setAttribute(SessionAttribute.CURRENT_PRODUCTS_PAGE, 1);
        if (session.getAttribute(SessionAttribute.CURRENT_PRODUCTS_PER_PAGE) == null)
            session.setAttribute(SessionAttribute.CURRENT_PRODUCTS_PER_PAGE, PaginationConstants.CURRENT_PRODUCTS_PER_PAGE);


        int pageNumber = (int)session.getAttribute(SessionAttribute.CURRENT_PRODUCTS_PAGE);
        int itemsPerPage = (int)session.getAttribute(SessionAttribute.CURRENT_PRODUCTS_PER_PAGE);
        int categoryId = (int)session.getAttribute(SessionAttribute.CURRENT_CATEGORY);
        String currentSearchString = (String)session.getAttribute(SessionAttribute.SEARCH_STRING);

        String nextPageNumber = request.getParameter(RequestParameter.NEXT_ITEM_PAGE);
        if (nextPageNumber != null){
            pageNumber = Integer.parseInt(nextPageNumber);
            session.setAttribute(SessionAttribute.CURRENT_PRODUCTS_PAGE, pageNumber);
        }
        String nextCategoryId = request.getParameter(RequestParameter.CURRENT_CATEGORY);
        if (nextCategoryId != null){
            categoryId = Integer.parseInt(nextCategoryId);
            session.setAttribute(SessionAttribute.CURRENT_CATEGORY, categoryId);
        }
        String searchString = request.getParameter(RequestParameter.SEARCH_STRING);
        if (searchString != null){
            currentSearchString = searchString;
            session.setAttribute(SessionAttribute.SEARCH_STRING, currentSearchString);
        }

        try {
            List<Product> range;
            int productCount = 0;
            int itemPageCount = 0;

            if (categoryId == 0){
                productCount = productService.getProductCount();
                itemPageCount = (int) Math.ceil(productCount * 1.0 / itemsPerPage);

                if (searchString == null){
                    range = productService.getProductPage(pageNumber, itemsPerPage);
                } else {
                    range = productService.getProductPageBySearch(pageNumber, itemsPerPage, categoryId, searchString);
                }

            } else {
                productCount = productService.getProductCount(categoryId);
                itemPageCount = (int) Math.ceil(productCount * 1.0 / itemsPerPage);

                if (searchString == null){
                    range = productService.getProductPageByCategory(pageNumber, itemsPerPage, categoryId);
                } else {
                    range = productService.getProductPageBySearch(pageNumber, itemsPerPage, categoryId, searchString);
                }
            }

            session.setAttribute(SessionAttribute.TOTAL_PAGE_COUNT, itemPageCount);
            session.setAttribute(SessionAttribute.TOTAL_ITEM_COUNT, productCount);
            session.setAttribute(SessionAttribute.CURRENT_ITEMS_RANGE, range);
        } catch (ServiceException e) {
            logger.error("Error while setting up race list", e);
        }

        return PagePath.CATALOG;
    }
}
