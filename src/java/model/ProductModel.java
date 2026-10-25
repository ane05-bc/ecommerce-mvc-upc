/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/* adding retrieveByCategory method */
package model;

import entity.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

public class ProductModel {

    UserTransaction utx;
    EntityManager em;

    public ProductModel(EntityManager em, UserTransaction utx) {
        this.utx = utx;
        this.em = em;
    }

    public List<Product> retrieveByCategory(int categoryId) {
        //assumption if Product has an atribute 'category'
        Query q = em.createQuery("SELECT p FROM Product p WHERE p.category.id = :categoryId");
        //Query q = em.createQuery("SELECT p FROM Product p WHERE p.category = :categoryId");
        q.setParameter("categoryId", categoryId);
        return q.getResultList();
    }
    
    public Product retrieve(int productId) {
        return em.find(Product.class, productId);
    }
}
