package sda.pl.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sda.pl.HibernateUtil;
import sda.pl.domain.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    public static Long saveOrUpdate(User user) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            return user.getId();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return 0L;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static Optional<User> findByEmailAndPassword(String email, String password) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "Select u From User u Where u.email = :email and u.password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("password", password);
            query.setParameter("email", email);
            User user = (User) query.getSingleResult();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static Optional<User> findUserById(Long Id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            User user = session.find(User.class, Id);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static List<User> findAllWithTotalOrderPrice() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql="Select new sda.pl.domain.User(u.id,u.email, SUM(o.totalPrice.priceGross)) From User u Left Join u.orderSet o group by u.id";
            Query query = session.createQuery(hql);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (session != null && session.isOpen()) {
                session.close() ;
            }
        }
    }
}
