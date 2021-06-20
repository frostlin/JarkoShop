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
        if (session.getAttribute(SessionAttribute.CURRENT_PRODUCT_PAGE) == null)
            session.setAttribute(SessionAttribute.CURRENT_PRODUCT_PAGE, 1);
        if (session.getAttribute(SessionAttribute.CURRENT_PRODUCT_PER_PAGE) == null)
            session.setAttribute(SessionAttribute.CURRENT_PRODUCT_PER_PAGE, 5);

        int pageNumber = (int)session.getAttribute(SessionAttribute.CURRENT_PRODUCT_PAGE);
        int productPerPage = (int)session.getAttribute(SessionAttribute.CURRENT_PRODUCT_PER_PAGE);

        if (request.getParameter(RequestParameter.NEXT_PRODUCT_PAGE) != null){
            int nextPageNumber = Integer.parseInt(request.getParameter(RequestParameter.NEXT_PRODUCT_PAGE));
            pageNumber = nextPageNumber;
            session.setAttribute(SessionAttribute.CURRENT_PRODUCT_PAGE, nextPageNumber);
        }

        try {
            int productCount = productService.getProductCount();
            int productPageCount = (int) Math.ceil(productCount * 1.0 / productPerPage);

            session.setAttribute(SessionAttribute.PRODUCT_PAGE_COUNT, productPageCount);
            session.setAttribute(SessionAttribute.TOTAL_PRODUCT_COUNT, productCount);
            session.setAttribute(SessionAttribute.CATALOG_PAGE_PRODUCT_LIST,
                    productService.getProductPage(pageNumber, productPerPage));

        } catch (ServiceException e) {
            logger.error("Error while setting up race list", e);
        }

        return PagePath.CATALOG;
    }
}
