package sda.pl.repository;

import org.hibernate.Session;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.query.Query;
import sda.pl.HibernateUtil;
import sda.pl.domain.Order;

import java.util.Collections;
import java.util.List;

public class OrderRepository {

    public static boolean saveOrder(Order order) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.save(order);
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
}
