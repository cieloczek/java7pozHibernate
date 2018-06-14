package sda.pl.repository;

import org.junit.Assert;
import org.junit.Test;
import sda.pl.domain.Product;

import java.util.Optional;

public class ProductRepositoryTest {

    @org.junit.Test
    public void findProduct() {
        Optional<Product> product= ProductRepository.findProduct(2L);
        Assert.assertTrue(product.get().getProductImage().getImage()!=null);
    }

    @Test
    public void findProductWithMagic() {
        ProductRepository.findProductWithMagic(2L);

    }
}