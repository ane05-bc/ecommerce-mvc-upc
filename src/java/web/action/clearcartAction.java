
package web.action;
import cart.ShoppingCart;
import javax.servlet.http.*;
import web.ViewManager;
/**
 *
 * @author aneli and ... 
 */
public class clearcartAction implements Action {
    @Override
    public void perform(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        
        if (cart != null) {
            cart.clear();
        }
        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
    
}
