package sda.pl.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sda.pl.HibernateUtil;
import sda.pl.domain.ProductRating;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductRatingRepository {
    public static Long saveOrUpdate(ProductRating rating){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            session.saveOrUpdate(rating);
            session.getTransaction().commit();
            return rating.getId();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return 0L;
        } finally {
            if (session.isOpen() && session != null) {
                session.close();
            }
        }
    }

    public static Optional<ProductRating> findProductRating(Long id){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            String hql = "Select p from ProductRating p where p.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            ProductRating pr = (ProductRating) query.getSingleResult();
            //Or ProductRating pr = session.find(ProductRating.class, id);
            return Optional.ofNullable(pr);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
            }finally {
            if(session.isOpen() && session!=null){
                session.close();
            }
        }
    }
    public static List<ProductRating> findAllActiveByProductId(Long productId) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "Select pr from ProductRating pr " +
                    "where pr.product.id = :id " +
                    "and pr.isActive = true " +
                    "order by pr.rate";
            Query query = session.createQuery(hql);
            query.setParameter("id", productId);
           return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (session.isOpen() && session != null) {
                session.close();
            }
        }
    }
    public static List<ProductRating> findAllActiveorWithRatingabove(Long productId, int rating) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "Select pr from ProductRating pr where pr.product.id = :id " +
                    "and( pr.isActive = true or pr.rate>= :rating ) ORDER BY pr.rate desc";
            Query query = session.createQuery(hql);
            query.setParameter("id", productId);
            query.setParameter("rating", rating);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (session.isOpen() && session != null) {
                session.close();
            }
        }
    }

}
