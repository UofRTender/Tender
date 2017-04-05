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

        <div id="containerBox" class = "container align-self-center col-sm-4 col-sm-offset-4">

            <div class="table-responsive">
                <table class="table table-condensed">

                    <c:if test="${not empty pending}">
                        <thead>
                        <th>Pending friend requests</th>
                        </thead>
                        <c:forEach var="pend" items="${pending}">
                            <form action="FriendsList" method="post">
                                <input type="hidden" value="${pend.pk}" name="pendingID">
                                <tr>
                                    <td class="text-capitalize"><c:out value="${pend.firstName}"></c:out> <c:out value="${pend.lastName}"></c:out></td>
                                        <td><input class="btn-sm btn-danger" type="submit" name="accept" value="Confirm Request"></td>
                                        <td><input class="btn-sm btn-danger" type="submit" name="decline "value="Decline Request"></td>
                                    </tr>
                                </form>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty confirmed}">
                        <thead>
                        <th>Friends</th> 
                        </thead>
                        <c:forEach var="conf" items="${confirmed}">
                            <form method="get" action="remoteProfile">
                                <input type="hidden" name="friendToAdd" value="${conf.pk}"/> 
                                <tr>
                                    <td><c:out value="${conf.firstName}"></c:out> <c:out value="${conf.lastName}"></c:out></td>
                                        <td><input class="btn-sm btn-danger" type="submit" value="View Profile"></td>
                                    </tr>
                                </form>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
        </div>
    </body>
</html>