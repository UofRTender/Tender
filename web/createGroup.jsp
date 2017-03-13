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
        <c:import url="HTMLPartials/navBar.jsp"/>
        <c:if test="${not empty error}">
            <c:out value="${error}"></c:out>
        </c:if>
        <div id="containerBox" class="container col-sm-4 col-sm-offset-4">
            <form action="createGroup" method="get">
                <table class="table-condensed">
                    <tr>
                        <td>Group Name: <input type="text" name="group_name"></td>
                    </tr>
                    <c:if test="${not empty confirmed}">
                        <c:forEach var="conf" items="${confirmed}">
                            <tr>
                                <td><c:out value="${conf.firstName}"></c:out> <c:out value="${conf.lastName}"></c:out>
                                    <input type="checkbox" name="friend" value="${conf.pk}"></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <tr>
                        <td>
                            <input class = "btn-sm btn-danger" type="submit" value="submit">   
                        </td>
                    </tr>
                </table>
            </form>
        </div>

    </body>
</html>
