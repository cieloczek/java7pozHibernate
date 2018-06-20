<%@ page import="sda.pl.domain.Color" %>
<%@ page import="sda.pl.domain.Price" %>
<%@ page import="sda.pl.domain.Product" %>
<%@ page import="sda.pl.repository.ProductRepository" %>
<%@ page import="java.util.Optional" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: lbacic_adm
  Date: 14.06.2018
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%
    // TODO dodac user z sesji albo ciastka
    Long USER_ID = 1L;
%>
<!DOCTYPE html>
<html lang="en">
<body>
<%
    Color[] colors = Color.values();

    if (request.getParameter("productId") != null && request.getParameter("productId").length() > 0) {
        Long productId = Long.valueOf(request.getParameter("productId"));
        Optional<Product> product = ProductRepository.findProduct(productId);
        if (product.isPresent()) {
            pageContext.setAttribute("product", product.get());

        } else {
            pageContext.setAttribute("product", new Product(new Price()));
        }
    }
    pageContext.setAttribute("colors", colors);

%>
<%@include file="header.jsp" %>
<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <%@include file="nav.jsp" %>
        </div>
        <!-- /.col-lg-3 -->
        <div class="col-lg-9">
            <div class="row">
                <form action="/ProductAdmin?productId=${product.id}" method="POST" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="name">Nazwa produktu</label>
                        <input type="text" class="form-control" value="${product.name}" name="name" id="name"
                               placeholder="Nazwa produktu">
                    </div>
                    <div class="form-group">
                        <label for="netPrice">cena netto</label>
                        <input type="text" class="form-control" value="${product.price.priceNet}" name="netPrice"
                               id="netPrice" placeholder="cena netto">
                        <%--</div>--%>
                        <%--<div class = "form-group">--%>
                        <label for="grossPrice">cena brutto</label>
                        <input type="text" class="form-control" value="${product.price.priceGross}" name="grossPrice"
                               id="grossPrice" placeholder="cena brutto">
                    </div>
                    <div class="form-group">
                        <label for="Color">kolor</label>
                        <select name="color" id="color">
                            <option>
                                <c:forEach var="colors" items="${colors}">
                                <c:if test="${empty product.id}">
                            <option>${colors}</option>
                            </c:if>
                            <c:if test="${not empty product.id}">
                                <option selected="selected">${colors}</option>
                            </c:if>
                            </c:forEach>
                            </option>
                        </select>
                        <%--</div>--%>
                        <%--<div class = "form-group">--%>
                        <label for="ProductType">Typ</label>
                        <select name="productType" id="ProductType">
                            <option>
                                <c:forEach var="categories" items="${categories}">
                                <c:if test="${empty product.id}">
                            <option>${categories}</option>
                            </c:if>
                            <c:if test="${not empty product.id}">
                                <option selected="selected">${categories}</option>
                            </c:if>
                            </c:forEach>
                            </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="photo">zdjecie</label>
                        <input type="file" class="form-control" id="photo" name="photo" placeholder="zdjecie" >
                    </div>
                    <button type="submit">OK!</button>
                </form>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.col-lg-9 -->
    </div>
    <!-- /.row -->
</div>
<!-- /.container -->
<%@include file="footer.jsp" %>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>

