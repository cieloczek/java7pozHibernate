<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="sda.pl.domain.Product" %>
<%@ page import="sda.pl.repository.ProductRepository" %>
<%@ page import =" java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 14.06.2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%
    List<Product>all = ProductRepository.findAll();
    pageContext.setAttribute("all", all);
%>
<!DOCTYPE html>
<html lang="en">
<body>
<%@include file="header.jsp" %>
<!-- Navigation -->

<!-- Page Content -->
<div class="container">

    <div class="row">
        <%@include file="nav.jsp"%>

        <!-- /.col-lg-3 -->

        <div class="col-lg-9">

            <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active">
                        <img class="d-block img-fluid" src="http://bi.gazeta.pl/im/61/17/15/z22114401Q,Maslo-przez-kilka-miesiecy-szybko-drozalo--ale-w-o.jpg" style="width:900px;height:350px;" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid"  src="http://placehold.it/900x350" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid"  src="http://placehold.it/900x350" alt="Third slide">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block img-fluid"  src="http://placehold.it/900x350" alt="Fourth slide">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

            <div class="row" aria-dropeffect="popup" >
                <c:forEach items="${all}" var="product">
                    <div class="col-lg-4 col-md-6 mb-4">
                    <%@include file="product.jsp"%>
                    </div>
                </c:forEach>
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
