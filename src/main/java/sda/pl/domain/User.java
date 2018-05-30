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
@EqualsAndHashCode(exclude = {"orderSet","cartSet","productRatingSet"})
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
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Cart> cartSet;
    @OneToMany(mappedBy = "user")
    Set<ProductRating> productRatingSet;

    @Transient
    BigDecimal totalOrderPrice;

    public User(Long id, String email, BigDecimal totalOrderPrice){
        this.id = id;
        this.email = email;
        this.totalOrderPrice = totalOrderPrice;

    }
}
