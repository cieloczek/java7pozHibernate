package sda.pl;

import lombok.*;
import sda.pl.domain.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"orderDetailsSet", "cartDetailSet", "productImage","productRatingSet","stockSet"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    @Embedded
    Price price;
    @Enumerated(EnumType.STRING)
    Color color;
    @Enumerated
    WarehouseName warehouseName;;
    @OneToOne(mappedBy = "product")
    ProductImage productImage;
    @OneToMany(mappedBy = "product")
    Set<OrderDetails>orderDetailsSet;
    @OneToMany(mappedBy = "product")
    Set<CartDetail>cartDetailSet;
    @OneToMany(mappedBy = "product")
    Set<ProductRating>productRatingSet;
    @OneToMany(mappedBy = "product")
    Set<Stock>productStockSet;
}

