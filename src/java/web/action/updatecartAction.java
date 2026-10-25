package web.action;

import cart.ShoppingCart;
import entity.Product;
import javax.servlet.http.*;
import model.ProductModel;
import web.ViewManager;

public class updatecartAction implements Action {
    ProductModel productModel;

    public updatecartAction(ProductModel productModel){
        this.productModel = productModel;
    }

    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        
        if (cart != null) {
            String productId = req.getParameter("productId");
            String quantity = req.getParameter("quantity");
            if (productId != null && quantity != null) {
                Product p = productModel.retrieve(Integer.parseInt(productId));
                cart.update(p, quantity);
            }
        }
        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
}