package web.action;

import cart.ShoppingCart;
import entity.Product;
import java.util.List;
import javax.servlet.http.*;
import model.CategoryModel;
import model.ProductModel;
import model.ReviewModel;
import web.ViewManager;

public class neworderAction implements Action {

    CategoryModel categoryModel;
    ProductModel productModel;
    ReviewModel reviewModel;

    public neworderAction(CategoryModel categoryModel, ProductModel productModel, ReviewModel reviewModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
        this.reviewModel = reviewModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        String productId = req.getParameter("productId");
        if (productId != null) {
            Product p = productModel.retrieve(Integer.parseInt(productId));
            if (p != null) {
                cart.addItem(p);
            }
        }
        
        req.setAttribute("categories", categoryModel.retrieveAll());
        String categoryId = req.getParameter("categoryid");
        if (categoryId != null) {
            int num_category = Integer.parseInt(categoryId);
            req.setAttribute("selectedCategory", categoryModel.retrieve(num_category));
            req.setAttribute("products", productModel.retrieveByCategory(num_category));
            req.setAttribute("averages", reviewModel.retrieveAverages(productModel.retrieveByCategory(num_category)));
        }

        ViewManager.nextView(req, resp, "/view/category.jsp");
    }
}