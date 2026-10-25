package web.action;

import java.util.HashSet;
import javax.servlet.http.*;
import model.CategoryModel;
import model.ProductModel;
import web.ViewManager;

public class categoryAction implements Action {

    CategoryModel categoryModel;
    ProductModel productModel;

    public categoryAction(CategoryModel categoryModel, ProductModel productModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }
    
    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String categoria = (String)req.getParameter("categoryid");
        int num_category = new Integer(categoria);
        
        req.setAttribute("categories", categoryModel.retrieveAll());
        req.setAttribute("products", productModel.retrieveByCategory(num_category));
        ViewManager.nextView(req, resp, "/view/init.jsp");
    }
}