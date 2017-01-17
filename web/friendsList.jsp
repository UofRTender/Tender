<%-- 
    Document   : friendsList
    Created on : 13-Jan-2017, 12:51:15 PM
    Author     : marlon
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Friends List</title>
    </head>
    <body>
        <h1>Friends List</h1>

        <c:if test="${not empty pending}">
            <p>Pending friend requests</p>
            <c:forEach var="pend" items="${pending}">
                <form action="FriendsList" method="post">
                    <input type="hidden" value="${pend.pk}" name="pendingID">
                    <p><c:out value="${pend.firstName}"></c:out> <c:out value="${pend.lastName}"></c:out>
                            <input type="submit" name="accept" value="Confirm Request">
                            <input type="submit" name="decline "value="Decline Request"></p>
                    </form>
            </c:forEach>
        </c:if>

        <c:if test="${not empty confirmed}">
            <p>Friends</p>
            <c:forEach var="conf" items="${confirmed}">
                <form method="post" action="profile">
                    <input type="hidden" name="friendToAdd" value="${conf.pk}"/> 
                    <p><c:out value="${conf.firstName}"></c:out> <c:out value="${conf.lastName}"></c:out>
                            <input type="submit" value="view profile"></p>
                    </form>
            </c:forEach>
        </c:if>
    </body>
</html>
