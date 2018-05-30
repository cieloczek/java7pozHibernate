package sda.pl;

import org.junit.Assert;
import org.junit.Test;
import sda.pl.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void addStock() {
        Optional<Product> product = ProductRepository.findProduct(2L);
        product.ifPresent(
                p->{
                    p.addStock(WarehouseName.MAIN, BigDecimal.ONE);
                    ProductRepository.saveOrUpdateProduct(p);
                }
        );
    }
}