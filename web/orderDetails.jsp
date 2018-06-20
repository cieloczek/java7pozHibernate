<%@ page import="sda.pl.repository.OrderRepository" %>
<%@ page import="sda.pl.domain.Order" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.List" %>
<%@ page import="sda.pl.domain.OrderDetails" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 15.06.2018
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%
    Long orderId =Long.valueOf(request.getParameter("orderId"));
    //TODO ZROBIC SZCZEGOLY ZAMOWIENIA
    Long USER_ID = 1L;
    Optional<Order> ordersByUser = OrderRepository.findById(orderId);
    if (ordersByUser.isPresent()) {
        pageContext.setAttribute("order", ordersByUser.get());
    } else {

        //should Be Error
    }
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
                    <th scope="col">Produkt</th>
                    <th scope="col">Ilość</th>
                    <th scope="col">Cena Brutto szt</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${order.orderDetailsSet}" var="ods">
                    <tr>
                        <th scope="row">1</th>
                        <td>${ods.id}</td>
                        <td>${ods.product.name}</td>
                        <td>${ods.amount}</td>
                        <td>${ods.product.price.priceGross}</td>
                        <%--<td>${ods.amount*ods.price}</td>--%>

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

