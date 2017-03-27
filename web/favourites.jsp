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
        <script type="text/javascript" src="javascript/action.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Favourites</title>
    </head>
    <body>
        <c:import url="HTMLPartials/navBar.jsp"/>
        <h1>Your Favourites</h1>
        <c:forEach var="favoured" items="${favourites}">
            <p><c:out value="${favoured.value}"></c:out> <button type="button" id="${favoured.key}" onclick="removeFavourites(this)">x</button></p>
        </c:forEach>
    </body>
</html>
