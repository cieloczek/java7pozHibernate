package sda.pl.domain;

import org.junit.Test;
import sda.pl.repository.ProductRatingRepository;
import sda.pl.repository.ProductRepository;
import sda.pl.repository.UserRepository;

import static org.junit.Assert.*;

public class ProductRatingTest {

    @Test
    public void setRate() {
        ProductRatingRepository.saveOrUpdate(ProductRating.builder().user(UserRepository.findUserById(2L).get()).product(ProductRepository.findProduct(5L).get()).rate(8).build());

    }
}