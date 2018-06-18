package sda.pl.web;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HelloWorldServlet", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        PrintWriter writer = response.getWriter();
        List<Product> all = ProductRepository.findAll();

        writer.write("<html><head></head><body><h1>Helo world!"+firstName+" </h1>" +
                "<table style = \"width:60%\" border=2px>" +
                "<tr><th>Nazwa</th>" +
                "<th>Id</th>" +
                "<th>Cena Netto</th>"+
                "<th>Cena Brutto</th></tr>");

        for(Product p : all) {
            writer.write("<tr><td>"+p.getName()+"</td><td>"+p.getId()+"</td><td>"+p.getPrice().getPriceNet()+"</td><td>"+p.getPrice().getPriceGross()+"</td></tr>");
        }
        writer.write( "</table></body></html>");
    }
}
