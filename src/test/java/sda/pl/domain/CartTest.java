package sda.pl.domain;

import org.junit.Test;
import sda.pl.repository.CartRepository;
import sda.pl.repository.OrderRepository;
import sda.pl.repository.ProductRepository;
import sda.pl.repository.UserRepository;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void createNewOrder() {
        User user = UserRepository.findUserById(3L).get();
        Cart cart = Cart.builder().user(user).build();
        cart.addProductToCart(ProductRepository.findProduct(2L).get(),20L);
        cart.addProductToCart(ProductRepository.findProduct(3L).get(),5L);
        CartRepository.saveCart(cart);
        Order theNewOrder = null;
        try {
            theNewOrder = cart.createNewOrder();
        } catch (ShopException e) {
            e.printStackTrace();
        }
        OrderRepository.saveOrder(theNewOrder);
    }
}