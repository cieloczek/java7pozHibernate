package sda.pl.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.OverridesAttribute;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Min(0)
    @Max(6)
    @Range(min = 0, max = 6)
    private int rate;
    String description;
    @ManyToOne
    @JoinColumn
    Product product;
    @ManyToOne
    @JoinColumn
    User user;
    boolean isActive;

}
