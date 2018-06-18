<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 14.06.2018
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<html>

<body>
<div class="col-lg-4 col-md-6 mb-4">
    <div class="card h-100">
        <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
        <div class="card-body">
            <h4 class="card-title">
                <a href="Prod.jsp?productId=${product.id}">${product.name}</a>
            </h4>
            <h5>${product.price}</h5>
            <p class="card-text">${product.color}</p>
        </div>
        <div class="card-footer">
            <small class="text-muted">
                &#9733; &#9733; &#9733; &#9733; &#9734;</small>
        </div>
    </div>
</div>
</body>
</html>
