package model;

/**
 *
 * @author entel
 */
import entity.Product;
import entity.Review;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

public class ReviewModel {

    UserTransaction utx;
    EntityManager em;

    public ReviewModel(EntityManager em, UserTransaction utx) {
        this.utx = utx;
        this.em = em;
    }

    public List<Review> retrieveByProduct(int productId) {
        Query q = em.createQuery("SELECT r FROM Review r WHERE r.product.id = :productId ORDER BY r.dateCreated DESC");
        q.setParameter("productId", productId);
        return q.getResultList();
    }

    public double retrieveAverage(int productId) {
        Query q = em.createQuery("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId");
        q.setParameter("productId", productId);
        Double average = (Double) q.getSingleResult();
        if (average == null) {
            return 0;
        }
        return average;
    }

    public HashMap<Integer, Double> retrieveAverages(List<Product> products) {
        HashMap<Integer, Double> averages = new HashMap<Integer, Double>();
        for (Product product : products) {
            averages.put(product.getId(), retrieveAverage(product.getId()));
        }
        return averages;
    }

    public void create(Product product, int rating, String comment) throws Exception {
        Review review = new Review();
        review.setProduct(product);
        review.setRating(rating);
        review.setComment(comment);
        review.setDateCreated(new Date());

        try {
            utx.begin();
            em.persist(review);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            throw e;
        }
    }
}
