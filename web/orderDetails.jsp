<%@ page import="sda.pl.repository.OrderRepository" %>
<%@ page import="sda.pl.domain.Order" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 15.06.2018
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%
    //TODO ZROBIC SZCZEGOLY ZAMOWIENIA
    Long USER_ID = 1L;
    List<Order> ordersByUser = OrderRepository.findAllByUserId(USER_ID);
        pageContext.setAttribute("ordersByUser", ordersByUser);
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
                    <th scope="col">Id</th>
                    <th scope="col">Data</th>
                    <th scope="col">Cena Netto</th>
                    <th scope="col">Cena Brutto</th>
                    <th scope="col">Usuń</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ordersByUser}" var="order" >
                    <tr>
                        <th scope="row">1</th>
                        <td>${order.id}</td>
                        <td>${order.date}</td>
                        <td>${order.totalPrice.priceNet}</td>
                        <td>${order.totalPrice.priceGross}</td>
                        <td>Usuń</td>
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

