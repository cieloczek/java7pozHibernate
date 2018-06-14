package sda.pl.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"orderSet","productRatingSet","bannerSet"})
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    String email;
    String zipcode;
    String cityName;
    String street;
    String password;

    @OneToMany(mappedBy = "user")
    Set<Order> orderSet;
    @OneToOne(mappedBy = "user")
    Cart cart;
    @OneToMany(mappedBy = "user")
    Set<ProductRating> productRatingSet;
    @ManyToMany(cascade = CascadeType.ALL)//, fetch = FetchType.EAGER)
    //join table do laczenia przez tabele dodatkowa
    @JoinTable(
            name = "advertisement_for_the_user",
            joinColumns = @JoinColumn(name = "banner_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<Banner> bannerSet;

    @Transient
    BigDecimal totalOrderPrice;

    public User(Long id, String email, BigDecimal totalOrderPrice){
        this.id = id;
        this.email = email;
        this.totalOrderPrice = totalOrderPrice;

    }
    public Cart createCart(){
        Cart cart = new Cart();
        cart.setUser(this);
        return cart;
    }
    public ProductRating rateProduct(int rate, String descritpion, Product product){
        ProductRating pr = ProductRating.builder().user(this)
                .rate(rate)
                .isActive(false)
                .description(descritpion)
                .build();
        return pr;
    }


}
