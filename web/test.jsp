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
        
        <div id="containerBox" class="container algin-self-center col-sm-4 col-sm-offset-4">
            <div class="table-responsive">
                <table class="table table-condensed">
                    <thead>
                        <th>Groups</th>
                    </thead>
                    <tbody>
                        <tr>
                            <c:forEach var="group" items="${groups}">
                                <td><a href="viewGroup?id=${group.key}"><c:out value="${group.value}"></c:out></a></td>
                            </c:forEach>
                        </tr>
                        <tr>
                            <form action="createGroup" method="post">
                                <input class="algin-self-center btn-danger btn-sm" type="submit" value="Create a Group"/>
                            </form>
                        </tr>
                    </tbody>
                </table>
        </div>
    </body>
</html>