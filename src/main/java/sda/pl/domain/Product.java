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
        if(stockSet==null){
            stockSet = new HashSet<>();
        }
        Optional<Stock> stockExits= stockSet.stream().filter(e->e.getWarehouseName().equals(name)).findFirst();
        if(!stockExits.isPresent()) {
            Stock stock = new Stock();
            stock.setProduct(this);
            stock.setWarehouseName(name);
            stock.setAmount(amount);
            stockSet.add(stock);
        }else{
        stockExits.ifPresent(e->e.setAmount(e.getAmount().add(amount)));        }

        Optional<Stock> stockExist = stockSet.stream().filter(e->e.getWarehouseName().equals(name)).findFirst();
        stockExist.ifPresent(s->s.setAmount(s.getAmount().add(amount)));
    }

    public long getSumStockForSale() {
        return getStockSet()
                .stream()
                .filter(s->!s
                .getWarehouseName()
                .equals(this))
                .mapToLong(s->s.getAmount().longValue()).sum();
    }
    public void addProductRating(ProductRating productRating){
        if(productRatingSet==null){
            productRatingSet = new HashSet<>();
        }
        productRating.setProduct(this);
        productRatingSet.add(productRating);
    }
}

