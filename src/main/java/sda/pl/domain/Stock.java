package sda.pl.warehouse;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import sda.pl.HibernateUtil;
import sda.pl.Product;
import sda.pl.WarehouseName;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Embeddable
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    WarehouseName warehouseName;
    Long ammount;

    @ManyToOne
    Product product;

    public static void addProduct(Product product, WarehouseName name){
    Session session = null;
    try {
        session = HibernateUtil.openSession();

        } catch (Exception e) {
        e.printStackTrace();
    }finally {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
    }
}
