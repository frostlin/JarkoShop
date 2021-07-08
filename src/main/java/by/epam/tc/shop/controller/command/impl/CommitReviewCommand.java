package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.SessionAttribute;
import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.model.entity.Product;
import by.epam.tc.shop.model.entity.Review;
import by.epam.tc.shop.model.entity.User;
import by.epam.tc.shop.model.service.ReviewService;
import by.epam.tc.shop.model.service.ServiceException;
import by.epam.tc.shop.model.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CommitReviewCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final ReviewService reviewService = ReviewServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int currentUserId = ((User)session.getAttribute(SessionAttribute.CURRENT_USER)).getId();
        int currentProductId = ((Product)session.getAttribute(SessionAttribute.CURRENT_PRODUCT)).getId();
        String content = request.getParameter(RequestParameter.REVIEW_CONTENT);
        int rating = Integer.parseInt(request.getParameter(RequestParameter.REVIEW_RATING));

        int reviewId = 0;

        try{
            reviewId = reviewService.commit(currentUserId,currentProductId,content,rating);
            List<Review> reviews = reviewService.getForProduct(currentProductId);
            request.setAttribute(RequestAttribute.REVIEWS, reviews);
        } catch(ServiceException e){
            logger.error("Error occurred while committing review for product " + currentProductId, e);
            request.setAttribute(RequestAttribute.REVIEW_COMMIT_ERROR, "review.commitError");
        }

        return PagePath.TO_PRODUCT;
    }
}
