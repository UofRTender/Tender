<%-- 
    Document   : myGroups
    Created on : 17-Jan-2017, 2:04:35 PM
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
        <title>Groups</title>
    </head>
    <body>
        <c:import url="HTMLPartials/navBar.jsp"/>
        <c:forEach var="group" items="${groups}">
            <a href="viewGroup?id=${group.key}"><c:out value="${group.value}"></c:out></a><br>
        </c:forEach>
        <form action="createGroup" method="post">
            <input type="submit" value="Create a Group"/>
        </form>
    </body>
</html>
