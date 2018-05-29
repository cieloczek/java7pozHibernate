package sda.pl.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import sda.pl.Product;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class ProductImage implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Lob
    byte[] image;

    @OneToOne
    Product product;

}
