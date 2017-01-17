<%-- 
    Document   : palette
    Created on : 9-Jan-2017, 11:43:30 AM
    Author     : marlon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Palette</title>
    </head>
    <body>
        <h1>Palette</h1>
        <form action="palette" method="post">
            <c:forEach var="food" items="${foodType}" varStatus="index">
                <p><c:out value="${food}"></c:out>
                    <c:choose>
                        <c:when test="${preference[index.index]=='t'}">
                            <input type="radio" name="${food}" value="1" checked> Like
                            <input type="radio" name="${food}" value="0"> Dislike <br>
                        </c:when>
                        <c:otherwise>
                            <input type="radio" name="${food}" value="1"> Like
                            <input type="radio" name="${food}" value="0" checked> Dislike <br>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                
                <input type="submit" value="Update Palette"/>
        </form>
        <form action="">
    </body>
</html>
