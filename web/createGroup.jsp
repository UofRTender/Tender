<%-- 
    Document   : createGroup
    Created on : 17-Jan-2017, 2:48:00 PM
    Author     : marlon
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Group</title>
    </head>
    <body>
        <form action="createGroup" method="get">
            <p>Group Name: <input type="text" name="group_name"></p>
            <c:if test="${not empty confirmed}">
                <c:forEach var="conf" items="${confirmed}">
                    <p><c:out value="${conf.firstName}"></c:out> <c:out value="${conf.lastName}"></c:out>
                        <input type="checkbox" name="friend" value="${conf.pk}"><br>
                </c:forEach>
            </c:if>
            <input type="submit" value="submit">   
        </form>
    </body>
</html>
