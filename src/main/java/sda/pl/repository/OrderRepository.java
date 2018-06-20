package sda.pl.repository;

import com.sun.tools.corba.se.idl.constExpr.Or;
import org.hibernate.Session;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.query.Query;
import sda.pl.HibernateUtil;
import sda.pl.domain.Order;
import sda.pl.domain.Product;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    public static boolean saveOrder(Order order) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.save(order);
            System.out.println("Order saved");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session.isOpen() && session != null) {
                session.close();
            }
        }
    }

    public static List<Order> collectAllOrders() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "Select o from Order o JOIN FETCH o.orderDetailsSet";
            Query query = session.createQuery(hql);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    public static List<Order> collectAllWithId(String productName) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "Select o from Order o JOIN FETCH o.orderDetailsSet od WHERE od.product.name like :productName ";
            Query query = session.createQuery(hql);
            query.setParameter("productName",  productName);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    public static List<Order> findAllByUserId(Long user_id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
//            String hql = "Select o from Order o JOIN FETCH o.orderDetailsSet od WHERE o.user.id = :userID GROUP BY o.id";
            String hql = "Select distinct o from Order o JOIN FETCH o.orderDetailsSet od WHERE o.user.id = :userID ";
            Query query = session.createQuery(hql);
            query.setParameter("userID",  user_id);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }
    public static Optional<Order> findById(Long id){
            Session session = null;
            try {
                session = HibernateUtil.openSession();
                Order order = session.find(Order.class, id);
                return Optional.ofNullable(order);
            } catch (Exception e) {
                e.printStackTrace();
                return Optional.empty();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
}
