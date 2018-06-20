package sda.pl.domain;

import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.servlet.http.Part;
import javax.validation.constraints.Min;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    @Enumerated(EnumType.STRING)
    ProductType productType;
    @Enumerated
    WarehouseName warehouseName;;
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
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

    public void addImage(Part photo) throws IOException {
        InputStream input = photo.getInputStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        for(int length = 0; (length=input.read(buffer))>0; output.write(buffer, 0, length));
        ProductImage productImage = new ProductImage();
        productImage.setImage(output.toByteArray());
        productImage.setProduct(this);

        this.setProductImage(productImage);
    }

    public long getSumStockForSale() {
        return getStockSet()
                .stream()
                .filter(s->!s
                .getWarehouseName()
                .equals(WarehouseName.MAIN))
                .mapToLong(s->s.getAmount().longValue()).sum();
    }
    public Product(Price price){
        this.price=price;
    }


}

