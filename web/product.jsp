<%@ page import="java.util.Optional" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 14.06.2018
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div class="card h-100">
        <a href="#"><img class="card-img-top" src="/productImage?productId=${product.id}" onerror="http:'//placehold.it/700x400'" width="200" height="150"></a>
        <div class="card-body">
            <h4 class="card-title">
                <a href="#">${product.name}</a>
            </h4>
            <h5>${product.price.priceNet}</h5>
            <p class="card-text">${product.color}</p>
        </div>
        <div class="card-footer">
            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
            <a href="/productAdminPage.jsp?productId=${product.id}" class="btn"> Edit</a>
        </div>
    </div>

