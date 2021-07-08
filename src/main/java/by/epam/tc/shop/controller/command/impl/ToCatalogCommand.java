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
import java.util.List;


public class ToCatalogCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private final ProductServiceImpl productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.CURRENT_CATALOG_PRODUCTS_PAGE) == null
                || request.getParameter(RequestParameter.CURRENT_CATEGORY) != null
                || request.getParameter(RequestParameter.SEARCH_STRING) != null
                || request.getParameter(RequestParameter.FILTER_METHOD) != null)
        {
            session.setAttribute(SessionAttribute.CURRENT_CATALOG_PRODUCTS_PAGE, 1);
            session.setAttribute(SessionAttribute.PRODUCT_FILTER_METHOD, null);
        }
        if (session.getAttribute(SessionAttribute.CURRENT_CATALOG_PRODUCTS_PER_PAGE) == null)
            session.setAttribute(SessionAttribute.CURRENT_CATALOG_PRODUCTS_PER_PAGE, PaginationConstants.CURRENT_CATALOG_PRODUCTS_PER_PAGE);


        //String currentSearchString = (String)session.getAttribute(SessionAttribute.SEARCH_STRING);

        int pageNumber = (int)session.getAttribute(SessionAttribute.CURRENT_CATALOG_PRODUCTS_PAGE);
        String nextPageNumber = request.getParameter(RequestParameter.NEXT_ITEM_PAGE);
        if (nextPageNumber != null){
            pageNumber = Integer.parseInt(nextPageNumber);
            session.setAttribute(SessionAttribute.CURRENT_CATALOG_PRODUCTS_PAGE, pageNumber);
        }

        int categoryId = (int)session.getAttribute(SessionAttribute.CURRENT_CATEGORY);
        String nextCategoryId = request.getParameter(RequestParameter.CURRENT_CATEGORY);
        if (nextCategoryId != null){
            categoryId = Integer.parseInt(nextCategoryId);
            session.setAttribute(SessionAttribute.CURRENT_CATEGORY, categoryId);
        }
//        String searchString = request.getParameter(RequestParameter.SEARCH_STRING);
//        if (searchString != null){
//            currentSearchString = searchString;
//            session.setAttribute(SessionAttribute.SEARCH_STRING, currentSearchString);
//        }

        String currentFilterMethod = (String)session.getAttribute(SessionAttribute.PRODUCT_FILTER_METHOD);
        String filterMethod = request.getParameter(RequestParameter.FILTER_METHOD);
        if (filterMethod != null){
            currentFilterMethod = filterMethod;
            session.setAttribute(SessionAttribute.PRODUCT_FILTER_METHOD, currentFilterMethod);
        }

        try {
            List<Product> range = null;
            int productCount = 0;
            int itemsPerPage = (int)session.getAttribute(SessionAttribute.CURRENT_CATALOG_PRODUCTS_PER_PAGE);
            productCount = productService.getProductCount(categoryId);
            if (currentFilterMethod != null){
                switch (currentFilterMethod){
                case "avgRating":
                    range = productService.getProductPageByCategorySortedByAvgRating(pageNumber, itemsPerPage, categoryId);
                    break;
                case "priceAsc":
                    range = productService.getProductPageByCategorySortedByPrice(pageNumber, itemsPerPage, categoryId, "");
                    break;
                case "priceDesc":
                    range = productService.getProductPageByCategorySortedByPrice(pageNumber, itemsPerPage, categoryId, "DESC ");
                    break;
            }
            } else {
                //if (searchString == null) {
                    range = productService.getProductPageByCategory(pageNumber, itemsPerPage, categoryId);
                //} else {
                //    range = productService.getProductPageBySearch(pageNumber, itemsPerPage, categoryId, searchString);
                //}
            }
            int itemPageCount = (int) Math.ceil(productCount * 1.0 / itemsPerPage);

            session.setAttribute(SessionAttribute.TOTAL_PAGE_COUNT, itemPageCount);
            session.setAttribute(SessionAttribute.TOTAL_ITEM_COUNT, productCount);
            session.setAttribute(SessionAttribute.CURRENT_ITEMS_RANGE, range);
        } catch (ServiceException e) {
            logger.error("Error while displaying catalog", e);
        }

        return PagePath.CATALOG;
    }
}
