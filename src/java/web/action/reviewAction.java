package web.action;

/**
 *
 * @author entel
 */
import javax.servlet.http.*;
import model.ProductModel;
import model.ReviewModel;
import web.ViewManager;

public class reviewAction implements Action {

    ProductModel productModel;
    ReviewModel reviewModel;

    public reviewAction(ProductModel productModel, ReviewModel reviewModel){
        this.productModel = productModel;
        this.reviewModel = reviewModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int productId = Integer.parseInt(req.getParameter("productId"));

        req.setAttribute("product", productModel.retrieve(productId));
        req.setAttribute("reviews", reviewModel.retrieveByProduct(productId));
        req.setAttribute("average", reviewModel.retrieveAverage(productId));
        ViewManager.nextView(req, resp, "/view/review.jsp");
    }
}
