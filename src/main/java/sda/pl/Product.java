package sda.pl;

import lombok.*;
import org.hibernate.Session;
import sda.pl.domain.*;
import sun.tools.java.AmbiguousMember;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
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
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Stock>stockSet;


    public void addStock(WarehouseName name, BigDecimal amount){
        Stock stock = new Stock();
        stock.setProduct(this);
        stock.setWarehouseName(name);
        stock.setAmount(amount);
        if(stockSet==null){
            stockSet = new HashSet<>();
            stockSet.add(stock);
        }
        Optional<Stock> stockExist = stockSet.stream().filter(e->e.getWarehouseName().equals(name)).findFirst();
        stockExist.ifPresent(s->s.setAmount(s.getAmount().add(amount)));
    }
}

