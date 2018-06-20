<%@ page import="sda.pl.domain.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="sda.pl.repository.OrderRepository" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 15.06.2018
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Long USER_ID = 1L;
    List<Order> all = OrderRepository.findAllByUserId(USER_ID);
    pageContext.setAttribute("userOrders", all);
%>
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
                    <th scope="col">Imie</th>
                    <th scope="col">Nazw</th>
                    <th scope="col">Nr zamowienia</th>
                    <th scope="col">Data</th>
                    <th scope="col">Cena Brutto</th>
                    <th scope="col">Usuń</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userOrders}" var="order" >
                    <tr>
                        <th scope="row">1</th>
                        <td>${order.user.firstName}</td>
                        <td>${order.user.lastName}</td>
                        <td>${order.id}</td>
                        <td>${order.date}</td>
                        <td>${order.totalPrice.priceGross}</td>
                        <td><a class="btn" href="orderDetails.jsp?orderId=${order.id}">Szczegoly zamowienia</a>  </td>

                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
        <%@include file="footer.jsp"%>
</body>
</html>
