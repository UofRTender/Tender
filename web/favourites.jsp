<%-- 
    Document   : favourites
    Created on : 12-Mar-2017, 9:41:04 PM
    Author     : marlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Favourites</title>
    </head>
    <body>
        <h1>Your Favourites</h1>
        <c:forEach var="favoured" items="${favourites}">
            <p id="${favoured.key}"><c:out value="${favoured.value}"></c:out></p>
        </c:forEach>
    </body>
</html>
