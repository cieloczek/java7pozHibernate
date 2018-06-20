package sda.pl.web;

import sda.pl.MyParser;
import sda.pl.domain.Color;
import sda.pl.domain.Price;
import sda.pl.domain.Product;
import sda.pl.domain.ProductType;
import sda.pl.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet(name = "ProductAdminServlet", urlPatterns = "/ProductAdmin")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,     // 10 MB
        maxFileSize = 1024 * 1024 * 50,          // 50 MB
        maxRequestSize = 1024 * 1024 * 100)       // 100 MB
public class ProductAdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        BigDecimal netPrice = MyParser.stringToBigDecimal(request.getParameter("netPrice"));
        BigDecimal grossPrice = MyParser.stringToBigDecimal(request.getParameter("grossPrice"));
        Color color = Color.valueOf(request.getParameter("color"));
        ProductType type = ProductType.valueOf(request.getParameter("productType"));
        Part photo = request.getPart("photo");
        Long productId = MyParser.stringToLong(request.getParameter("productId"));
        Optional<Product> productOptional = ProductRepository.findProduct(productId);
        Product product;
        if (productOptional.isPresent()) {
            product = productOptional.get();
            product.setName(name);
            product.setPrice(Price.builder().priceNet(netPrice).priceGross(grossPrice).build());
            product.setColor(color);
            product.setProductType(type);
            if (photo != null && photo.getSize() > 0) {
                product.addImage(photo);
            }
        } else {
            product =
                    Product.builder()
                            .name(name)
                            .price(Price.builder()
                                    .priceGross(grossPrice)
                                    .priceNet(netPrice)
                                    .priceSymbol("pln").build())
                            .color(color)
                            .productType(type)
                            .build();
            product.addImage(photo);
        }
        ProductRepository.saveOrUpdateProduct(product);

        response.sendRedirect("http://localhost:8080");
    }
}
