package sda.pl.domain;

import lombok.*;
import sda.pl.Price;
import sda.pl.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn
    Product product;

    @ManyToOne
    @JoinColumn
    Order order;
    Long amount;
    Price price;
}
