package sda.pl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    BigDecimal priceGross;
    BigDecimal priceNet;
    String priceSymbol;

}
