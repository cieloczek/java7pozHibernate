package sda.pl.web;

import sda.pl.domain.Cart;
import sda.pl.domain.Price;
import sda.pl.domain.Product;
import sda.pl.repository.ProductRepository;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productAmmount = Long.parseLong(request.getParameter("productAmmount"));
        Long productId = Long.parseLong(request.getParameter("productId"));

        Cart cart = new Cart();
        Product product = new Product();
        Price price = new Price();
        price.setPriceGross(BigDecimal.ONE);
        price.setPriceNet(BigDecimal.ONE);
        price.setPriceSymbol("PLN");
        product.setPrice(price);
        product.setStockSet(new HashSet<>());
        cart.addProductToCart(product, productAmmount);

        Optional<Product> pr = ProductRepository.findProduct(productId);
        if(pr.isPresent()){
            cart.addProductToCart(pr.get(), productAmmount);
        }
        PrintWriter writer = response.getWriter();
        writer.write("ammount" + productAmmount);
    }


}
