<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="sda.pl.domain.Product" %>
<%@ page import="sda.pl.repository.ProductRepository" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 18.06.2018
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = request.getParameter("phrase");
    List<Product> all = ProductRepository.findByNameCriteriaQuery(name);
    pageContext.setAttribute("allProducts", all);
%>
<html>
<head>
    <%@include file="header.jsp" %>
</head>

<body>
<div class="row">
    <%@include file="nav.jsp" %>
    <div class="col-lg-9">
        <div class="row">
                <c:forEach items="${allProducts}" var="product">
                    <div class="col-lg-4 col-md-6 mb-4">
                        <%@include file="product.jsp" %>
                    </div>
                </c:forEach>
        </div>
    </div>
</div>
<div>
    <%@include file="footer.jsp" %>
</div>
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
