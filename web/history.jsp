<%-- 
    Document   : history
    Created on : 12-Mar-2017, 9:41:16 PM
    Author     : marlon
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
    </head>
    <body>
        <h1>History</h1>
        <c:forEach var="history" items="${entireHistory}">
            <p id="${history.key}"><c:out value="${history.value}"></c:out></p>
        </c:forEach>
    </body>
</html>
