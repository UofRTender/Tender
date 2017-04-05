<%-- 
    Document   : editgroup
    Created on : 22-Jan-2017, 4:34:27 PM
    Author     : marlon
--%>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Group</title>
    </head>
    <body>
        <c:import url="HTMLPartials/navBar.jsp"/>
        <div id="containerBox" class="container col-sm-4 col-sm-offset-4">
            <h1>Edit group: <c:out value="${name}"></c:out></h1>
            <c:if test="${not empty error}">
                <c:out value="${error}"></c:out>
            </c:if>
            <form action="editGroup" method="get">
                <div class="table-responsive">
                    <table class="table-condensed">
                        <tr>
                            <td>New Group Name: <input type="text" name="group_name"></td>
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
                                <input type="hidden" name="gpk" value="${gpk}">
                                <input type="submit" value="submit">   
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </body>
</html>
