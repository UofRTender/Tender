<%-- 
    Document   : viewGroup
    Created on : 22-Jan-2017, 3:40:16 PM
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
        <title><c:out value="${groupName}"/></title>

    </head>
    <body>
        <c:import url="HTMLPartials/navBar.jsp"/>
        <c:forEach var="user" items="${members}">
            <a href="profile?friendToAdd=${user.pk}"><c:out value="${user.firstName}"></c:out> <c:out value="${user.lastName}"></c:out></a><br>
        </c:forEach>
        <form method="post" action="viewGroup">
            <input type="hidden" name="gpk" value="${pk}"/>
            <input type="submit" value="leave group"/>
        </form>
        <form method="post" action="editGroup">
            <input type="hidden" name="gpk" value="${pk}"/>
            <input type="submit" value="edit group"/>
        </form>
    </body>
</html>
