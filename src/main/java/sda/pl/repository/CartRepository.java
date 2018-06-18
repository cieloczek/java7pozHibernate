package sda.pl.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sda.pl.HibernateUtil;
import sda.pl.domain.Cart;
import java.util.Optional;
public class CartRepository {
    public static boolean saveCart(Cart cart) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.save(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static boolean saveOrUpdateCart(Cart cart) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(cart);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null && session.isOpen() && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public static Optional<Cart> findCart(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Cart cart = session.find(Cart.class, id);
            return Optional.ofNullable(cart);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public static Optional<Cart> findCartByUserId(Long id){
        Session session = null;
        try{
            session= HibernateUtil.openSession();
            String hql="Select c from Cart c Join fetch c.cartDetailSet where c.user.id = :userId";
            Query query = session.createQuery(hql);
            query.setParameter("userId", id);
            return Optional.ofNullable((Cart) query.getSingleResult());
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }
    public static void deleteCart(Optional<Cart> cartByUserId){
Session session = null ;
    try{
    session = HibernateUtil.openSession() ;
    session.getTransaction().begin();
    String removeCartDetail = "Delete from CartDetail cd where cd.cart.id = :cartId";
    String removeCart = "Delete from Cart c where c.id = :cartId";

    Query query = session.createQuery(removeCartDetail);
    query.setParameter("cartId", cartByUserId.get().getId());
    query.executeUpdate();

    Query removeCartQuery = session.createQuery(removeCart);
    removeCartQuery.setParameter("cartId", cartByUserId.get().getId());
    removeCartQuery.executeUpdate();
        session.getTransaction().commit();
    }catch (Exception e){
        if(session!=null && session.isOpen() && session.getTransaction().isActive()){
            session.getTransaction().rollback();
        }
        e.printStackTrace();
    }finally{
        if(session!=null){
            session.close();
        }
    }
    }
}