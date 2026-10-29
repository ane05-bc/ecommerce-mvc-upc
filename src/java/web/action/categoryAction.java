package web.action;

import java.util.HashSet;
import javax.servlet.http.*;
import model.CategoryModel;
import model.ProductModel;
import model.ReviewModel;
import web.ViewManager;

public class categoryAction implements Action {

    CategoryModel categoryModel;
    ProductModel productModel;
    ReviewModel reviewModel;

    public categoryAction(CategoryModel categoryModel, ProductModel productModel, ReviewModel reviewModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
        this.reviewModel = reviewModel;
    }
    
    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String categoria = (String)req.getParameter("categoryid");
        int num_category = new Integer(categoria);
        
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("selectedCategory", categoryModel.retrieve(num_category));
        req.setAttribute("products", productModel.retrieveByCategory(num_category));
        req.setAttribute("averages", reviewModel.retrieveAverages(productModel.retrieveByCategory(num_category)));
        ViewManager.nextView(req, resp, "/view/category.jsp");
    }
}