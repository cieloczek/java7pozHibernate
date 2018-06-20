package sda.pl.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "orderDetailsSet")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDateTime date;
    Price totalPrice;
    @ManyToOne
    @JoinColumn
    User user;

    @Column(name = "shipping_adress")
    String cityname;

    boolean RODO;

    @Transient
    BigDecimal vat;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<OrderDetails> orderDetailsSet;

    public void addOrderDetail(OrderDetails orderDetails){
        if(orderDetailsSet == null){
            orderDetailsSet=new HashSet<>();
        }
        orderDetails.setOrder(this);
        orderDetailsSet.add(orderDetails);

    }
    public void calculateTotalPrice(){
        totalPrice = new Price();
           totalPrice.setPriceGross(BigDecimal.ZERO);
           totalPrice.setPriceNet(BigDecimal.ZERO);
           totalPrice.setPriceSymbol("PLN");

        getOrderDetailsSet().forEach(
                e ->{getTotalPrice().setPriceGross(getTotalPrice().getPriceGross()
                        .add(e.getPrice().getPriceGross().multiply(new BigDecimal(e.getAmount()))
                        ));
                    getTotalPrice().setPriceNet(getTotalPrice().getPriceNet()
                            .add(e.getPrice().getPriceNet().multiply(new BigDecimal(e.getAmount()))
                            ));
                });
    }
}
