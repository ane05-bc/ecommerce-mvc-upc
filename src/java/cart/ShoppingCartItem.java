package cart;

import entity.Product;
/* Product encapsulation and quantity choosen */

public class ShoppingCartItem {
    private Product product;
    private int quantity;
    
    public ShoppingCartItem(Product product) {
        this.product = product;
        this.quantity =  1;
    }
    
    public Product getProduct() {
        return product;
    }   
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity (int quantity) {
        this.quantity = quantity;
    }
    
    public void incrementQuantity() {
        quantity++;
    }
    
    public void decrementQuantity() {
        quantity--;
    }
    
    public double getTotal() {
        // assumption getPrice() method of Product returns int
        return this.quantity * product.getPrice();
    }
}