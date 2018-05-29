package sda.pl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sda.pl.Price;
import sda.pl.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetail implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn
    Product product;
    Long ammount;
    @Embedded
    Price price;
    @ManyToOne
    @JoinColumn
    Cart cart;

}
