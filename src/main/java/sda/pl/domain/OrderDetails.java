package sda.pl.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
    public OrderDetails(CartDetail cartDetail){
        this.product=cartDetail.getProduct();
        this.amount=cartDetail.getAmount();
        this.price=cartDetail.getPrice();
    }
}
