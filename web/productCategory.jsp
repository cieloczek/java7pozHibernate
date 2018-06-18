<%@ page import="sda.pl.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="sda.pl.repository.ProductRepository" %>
<%@ page import="sda.pl.domain.ProductType" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 15.06.2018
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ProductType category = ProductType.valueOf(request.getParameter("category"));
    List<Product> all = ProductRepository.findAllByCategory(category);
    pageContext.setAttribute("all", all);

%>
<html>
<head>
<%@include file="header.jsp"%>
</head>
<body>
<div class="container">
    <div class="row">
        <%@include file="nav.jsp" %>
<div class="col-lg-9">
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Nazwa Produktu</th>
        <th scope="col">Cena Netto</th>
        <th scope="col">Cena Brutto</th>
        <th scope="col">Usuń</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${all}" var="pr" >
        <tr>
            <th scope="row">1</th>
            <td>${pr.name}</td>
            <td>${pr.price.priceNet}</td>
            <td>${pr.price.priceGross}</td>
            <td>Usuń</td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</div>
        <%@include file="footer.jsp"%>
</body>
</html>
