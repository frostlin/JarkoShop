package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.PaginationConstants;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.service.OrderService;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.OrderServiceImpl;
import by.epam.tc.shop.model.service.impl.ProductServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToAdminProducts implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final ProductServiceImpl productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttribute.CURRENT_ADMIN_PANEL_PAGE) == null)
            session.setAttribute(SessionAttribute.CURRENT_ADMIN_PANEL_PAGE, 1);
        if (session.getAttribute(SessionAttribute.CURRENT_ADMIN_PANEL_ITEMS_PER_PAGE) == null)
            session.setAttribute(SessionAttribute.CURRENT_ADMIN_PANEL_ITEMS_PER_PAGE, PaginationConstants.CURRENT_ORDERS_PER_PAGE);

        int pageNumber = (int)session.getAttribute(SessionAttribute.CURRENT_ADMIN_PANEL_PAGE);
        int itemsPerPage = (int)session.getAttribute(SessionAttribute.CURRENT_ADMIN_PANEL_ITEMS_PER_PAGE);

        String nextPageNumber = request.getParameter(RequestParameter.NEXT_ITEM_PAGE);
        if (nextPageNumber != null){
            pageNumber = Integer.parseInt(nextPageNumber);
            session.setAttribute(SessionAttribute.CURRENT_ADMIN_PANEL_PAGE, pageNumber);
        }
        try {
            int productCount = productService.getProductCount();
            int itemPageCount = (int) Math.ceil(productCount * 1.0 / itemsPerPage);

            session.setAttribute(SessionAttribute.TOTAL_PAGE_COUNT, itemPageCount);
            session.setAttribute(SessionAttribute.TOTAL_ITEM_COUNT, productCount);
            session.setAttribute(SessionAttribute.CURRENT_ITEMS_RANGE,
                    productService.getProductPage(pageNumber, itemsPerPage));
        } catch (ServiceException e) {
            logger.error("Error while setting up products list", e);
        }
        return PagePath.ADMIN_PRODUCTS;
    }
}
