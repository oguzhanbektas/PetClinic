<%--
  Created by IntelliJ IDEA.
  User: bforb
  Date: 21.02.2021
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="margin-top:20px; margin-bottom: 20px;"> Owners Table</h1>
<table>
    <thead>
    <tr style="font-weight: bold; background-color: aqua">
        <td>Id</td>
        <td>First Name</td>
        <td>Last Name</td>
    </tr>
    </thead>
    <c:forEach items="${owners}" var="owner" varStatus="status">
        <tr bgcolor="${status.index % 2 ==0 ? 'white' : 'lightgray'}">
            <td>${owner.id}</td>
            <td>${owner.firstName}</td>
            <td>${owner.lastName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
