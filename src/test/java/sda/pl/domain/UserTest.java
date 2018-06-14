package sda.pl.domain;

import org.junit.Test;
import sda.pl.repository.ProductRatingRepository;
import sda.pl.repository.ProductRepository;
import sda.pl.repository.UserRepository;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void rateProduct() {
        Optional<User> user = UserRepository.findUserById(5L);
        user.ifPresent(us->{
            ProductRatingRepository.saveOrUpdate(ProductRating.builder()
                    .description("po prostu masło")
                    .user(us)
                    .isActive(true)
                    .rate(5)
                    .product(ProductRepository.findProduct(3L).get()).build());
        });
    }
}