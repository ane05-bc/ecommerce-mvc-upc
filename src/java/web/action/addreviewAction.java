package web.action;

/**
 *
 * @author entel
 */
import entity.Product;
import javax.servlet.http.*;
import model.ProductModel;
import model.ReviewModel;
import web.ViewManager;

public class addreviewAction implements Action {

    ProductModel productModel;
    ReviewModel reviewModel;

    public addreviewAction(ProductModel productModel, ReviewModel reviewModel){
        this.productModel = productModel;
        this.reviewModel = reviewModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int rating = Integer.parseInt(req.getParameter("rating"));
        String comment = req.getParameter("comment");

        Product p = productModel.retrieve(productId);

        reviewModel.create(p, rating, comment);
 
        req.setAttribute("product", p);
        req.setAttribute("reviews", reviewModel.retrieveByProduct(productId));
        req.setAttribute("average", reviewModel.retrieveAverage(productId));
        ViewManager.nextView(req, resp, "/view/review.jsp");
    }
}
