package sda.pl.web;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sda.pl.HibernateUtil;
import sda.pl.domain.Cart;
import sda.pl.domain.Price;
import sda.pl.domain.Product;
import sda.pl.repository.CartRepository;
import sda.pl.repository.ProductRepository;
import sda.pl.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

@WebServlet(name = "AddProductToCartServlet", urlPatterns = "/cart")
public class AddProductToCartServlet extends HttpServlet {

    //TODO USERID from session or Cookie
    public static final Long USER_ID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productAmmount = Long.parseLong(request.getParameter("productAmmount"));
        Long productId = Long.parseLong(request.getParameter("productId"));

        Optional<Cart>cartByUserId=CartRepository.findCartByUserId(USER_ID);
        Cart cart = new Cart();
        if(cartByUserId.isPresent()){
            cart = cartByUserId.get();
        } else{
            cart.setUser(UserRepository.findUserById(1L).get());
        }
//        Product product = new Product();
//        Price price = new Price();
//        price.setPriceGross(BigDecimal.ONE);
//        price.setPriceNet(BigDecimal.ONE);
//        price.setPriceSymbol("PLN");
//        product.setPrice(price);
//
//        product.setStockSet(new HashSet<>());
//        cart.addProductToCart(product, productAmmount);

        Optional<Product> pr = ProductRepository.findProduct(productId);
        if(pr.isPresent()){
            cart.addProductToCart(pr.get(), productAmmount);
            CartRepository.saveOrUpdateCart(cart);
        }
        PrintWriter writer = response.getWriter();
        response.sendRedirect("http://localhost:8080");
        writer.write("ammount" + productAmmount);
    }


}
