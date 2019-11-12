<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 12.11.2019
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
    <h1>All Clients</h1>

    <table border="1">
        <c:forEach items="${list}" var="o">
            <tr>
                <td><c:out value="${o.name}"/></td>
                <td><c:out value="${o.email}"/></td>
                <td><c:out value="${o.phoneNumber}"/></td>
            </tr>
        </c:forEach>
    </table>

    <br/><a href="index.html">Go back</a>

</body>
</html>
