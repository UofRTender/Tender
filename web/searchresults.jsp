<%-- 
    Document   : searchresults
    Created on : 2-Apr-2017, 6:38:04 PM
    Author     : marlon
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <c:import url="HTMLPartials/navBar.jsp"/>
        <div id="containerBox" class = "container algin-self-center col-sm-4 col-sm-offset-4">
            <h1>Search Results</h1>
            <c:choose>
                <c:when test="${not empty users}">
                    <table class="table table-hover table-condensed">
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td id="${user.pk}"><a href="remoteProfile?friendToAdd=${user.pk}">
                                        <c:out value="${user.firstName}"></c:out> <c:out value="${user.lastName}"></c:out> #<c:out value="${user.pk}"></c:out>
                                    </a></td>
                                <td class="text-capitalize"><c:out  value="${user.email}"></c:out></td>
                                <td class="text-capitalize"><c:out value="${user.city}"></c:out> <c:out value="${user.country}"></c:out></td>
                                </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>Sorry, That search returned nothing, Please try again</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
