<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="sda.pl.domain.Product" %>
<%@ page import="sda.pl.repository.ProductRepository" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 14.06.2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%
    Long productId = Long.valueOf(request.getParameter("productId"));
    Optional<Product> product = ProductRepository.findProduct(productId);
    if(product.isPresent()){
            pageContext.setAttribute("product", product.get());
    }else{
        //TODO Redirecting
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="header.jsp" %>
</head>
<body>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-3">

            <h1 class="my-4">Shop Name</h1>
            <div class="list-group">
                <a href="#" class="list-group-item">Category 1</a>
                <a href="#" class="list-group-item">Category 2</a>
                <a href="#" class="list-group-item">Category 3</a>
            </div>

        </div>
        <!-- /.col-lg-3 -->
        <div class="row">

            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card h-100">
                    <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                    <div class="card-body">
                        <h4 class="card-title">
                            <a href="#">${product.name}</a>
                        </h4>
                        <h5>${product.price.priceNet}</h5>
                        <p class="card-text">${product.color}</p>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                    </div>
                    <div>
                        <form action="/cart?productId=${product.id}" method = "post">
                           <label for="productAmmount" >Liczba productów</label>
                            <input name="productAmmount" id="productAmmount" value="1" type="number">
                            <button type="submit">Dodaj do koszyka</button>
                        </form>
                    </div>
                </div>
            </div>


        </div>



            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<%@include file="footer.jsp"%>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
