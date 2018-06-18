package sda.pl.domain;

import lombok.*;
import sda.pl.repository.ProductRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"cartDetailSet", "user"})
public class Cart implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn
    User user;
    @Transient
    boolean valid;
    @OneToMany(mappedBy = "cart",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<CartDetail> cartDetailSet;

    public void addProductToCart(Product product, Long amount){
        if(cartDetailSet == null){
            cartDetailSet = new HashSet<>();
        }

        Optional<CartDetail> first = cartDetailSet.stream().filter(cd -> cd.getProduct().getId().equals(product.getId())).findFirst();
        long sum = product.getSumStockForSale();
        if(!first.isPresent()){
            if(amount>sum){
                amount=sum;
            }
            CartDetail newCartDetail = CartDetail.builder()
                    .amount(amount)
                    .price(product.getPrice())
                    .cart(this)
                    .product(product)
                    .build();
            cartDetailSet.add(newCartDetail);
        }else{
//            first.ifPresent(cd->cd.setAmount(cd.getAmount()+amount));
            CartDetail cd = first.get();
            if(cd.getAmount()+amount>sum){
                amount=sum-cd.getAmount();
            }
            cd.setAmount(cd.getAmount()+amount);
        }
    }
    public void substractProductInCart(Product product) {
        if (cartDetailSet == null) {
            return;
        }

        Optional<CartDetail> productInCart = cartDetailSet.stream().filter(cd -> cd.getId().equals(product.getId())).findFirst();
        productInCart.ifPresent(cd -> {
            if (cd.getAmount() > 0) {
                cd.setAmount(cd.getAmount() - 1);
            }
            if (cd.getAmount() == 0) {
                cd.setAmount(0L);
            }
        });
    }
       public void changeProductAmount(Product product, Long amount){
        if(cartDetailSet == null){
            return;
        }
        long sum = product.getSumStockForSale();
        Optional<CartDetail> productInCart = cartDetailSet.stream().filter(cd->cd.getId().equals(product.getId())).findFirst();
        productInCart.ifPresent(pr->{
            if(amount>0){
                if(amount>sum) {
                    pr.setAmount(sum);
                }else{
                    pr.setAmount(amount);
                }
            }else{
                pr.setAmount(0L);
            }
        });
       }
    public void checkIfValid(){
        setValid(true);
        getCartDetailSet().forEach(cd->{
            long sumStockForSale = cd.getProduct().getSumStockForSale();
            if(sumStockForSale<cd.getAmount()){
                setValid(false);
            }
        });
    }
    public void setValid(boolean valid){
        this.valid =valid;
    }
    public Order createNewOrder() throws ShopException{
        checkIfValid();
        if(!valid){
            throw new ShopException("Some products are missing in stock");
        }
        Order order = Order.builder()
                .cityname(this.getUser().cityName)
                .date(LocalDateTime.now())
                .user(this.user)
                .RODO(true)
                .build();
        this.cartDetailSet.forEach(e->
                order.addOrderDetail(new OrderDetails(e)));
                order.calculateTotalPrice();
        return order;
       }
    public void removeGivenAmmountOfProductFromCart(Long productId, Long ammount) {
        if (cartDetailSet != null) {
            Optional<CartDetail>prodInCart = this.cartDetailSet.stream().filter(e->e.getId().equals(productId)).findFirst();
            prodInCart.ifPresent(pr->{
                Long amountInCart = getAmountInCart(productId);
                if(amountInCart>0){
                    if(ammount>amountInCart){
                        pr.setAmount(ammount-amountInCart);
                    }else{
                        pr.setAmount(0L);
                    }
                }
                });
            //TODO a moze by tak rzucał shopexception?
        }
    }
    public Long getAmountInCart(Long productId){
        return this.cartDetailSet.stream().filter(pr->pr.getId().equals(productId)).findFirst().get().getAmount();
    }



}
