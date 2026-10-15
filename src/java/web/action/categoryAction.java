package web.action;

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

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        String categoria = (String)req.getParameter("categoryid");
        int num_category = new Integer(categoria);S
        req.setAttribute("categories", categoryModel.retrieveAll());
        ViewManager.nextView(req, resp, "/view/init.jsp");
    }
}
