<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="sda.pl.repository.CartRepository" %>
<%@ page import="sda.pl.domain.Cart" %>
<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 15.06.2018
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Long USER_ID = 1L;
    Optional<Cart> cartByUserId = CartRepository.findCartByUserId(USER_ID);
    if(cartByUserId.isPresent()){
        pageContext.setAttribute("cart", cartByUserId.get());
    }else{
        //TODO dodac przekierowanie
    }
%>
<html>
<head>
    <%@include file="header.jsp" %>

</head>
<body>
<div class="container">
<div class="row">
<%@include file="nav.jsp" %>
    <div class="col-lg-9">
        <div>
            <br>
            <br>
            <br>
            <br>
        </div>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Nazwa Produktu</th>
        <th scope="col">Ilość</th>
        <th scope="col">Cena Netto</th>
        <th scope="col">Cena Brutto</th>
        <th scope="col">Usuń</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cart.cartDetailSet}" var="cd" >
    <tr>
        <th scope="row">1</th>
        <td>${cd.product.name}</td>
        <td>${cd.amount}</td>
        <td>${cd.price.priceNet}</td>
        <td>${cd.price.priceGross}</td>
        <td> <button type="submit" href="/removeOne?productId=${cd.id}"/>Usuń</td>
    </tr>
    </c:forEach>
    </tbody>


</table>
    <div>
    </div>
    <a href="/createOrder" class="btn-success" value="Zakończ i zapać"/>
    </div>
</div>
</div>
<%@include file="footer.jsp"%>

</body>
</html>
