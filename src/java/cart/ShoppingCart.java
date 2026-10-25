package cart;

import entity.Product;
import java.util.*;

/**
 *
 * @author juanluis
 */

/* Logic item managment, */
public class ShoppingCart {
    private List<ShoppingCartItem> items;
    
    public ShoppingCart() {
        items = new ArrayList<ShoppingCartItem>();
    }
    
    public synchronized void addItem(Product product) {
        boolean newItem = true;
        for (ShoppingCartItem scItem : items) {
            //
            if (scItem.getProduct().getId() == product.getId()) {
                newItem = false;
                scItem.incrementQuantity();
                break;
            }
        }
        if (newItem) {
            items.add(new ShoppingCartItem(product));
        }
    }
    
    public synchronized void update(Product product, String quantity) {
        int qty = Integer.parseInt(quantity);
        if (qty >= 0) {
            ShoppingCartItem itemToRemove = null;
            for (ShoppingCartItem scItem : items) {
                if (scItem.getProduct().getId() == product.getId()){
                    if (qty != 0) {
                        scItem.setQuantity(qty);
                    } else {
                        itemToRemove = scItem;
                    }
                    break;
                }
            }
            if (itemToRemove != null) {
                items.remove(itemToRemove);
            }
        }
    }
    
    public synchronized List<ShoppingCartItem> getItems() {
        return items;
    }
    
    public synchronized int getNumberOfItems() {
        int numberOfItems = 0;
        for (ShoppingCartItem scItem : items) {
            numberOfItems += scItem.getQuantity();
        }
        return numberOfItems;
    }
    
    public synchronized double getSubTotal() {
        double amount = 0;
        for (ShoppingCartItem scItem : items) {
            amount += (scItem.getQuantity() * scItem.getProduct().getPrice());
        }
        return amount;
    }
    
    public synchronized void clear() {
        items.clear();
    }
}