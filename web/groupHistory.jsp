<%-- 
    Document   : groupHistory
    Created on : 1-Apr-2017, 9:19:41 PM
    Author     : marlon
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Group History</title>
    </head>
    <body>
        <c:import url="HTMLPartials/navBar.jsp"/>
        <div id="containerBox" class = "container algin-self-center col-sm-4 col-sm-offset-4">
            <h1>History - <c:out value="${name}"></c:out></h1>
            <table class="table table-hover table-condensed">
                <c:forEach var="history" items="${entireHistory}">
                    <tr>
                        <td id="${history.historyPk}"><c:out value="${history.name}"></c:out></td>
                        <td><c:out value="${history.date}"></c:out></td>
                        </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
