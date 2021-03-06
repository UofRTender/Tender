<%-- 
    Document   : remoteProfile
    Created on : 2-Apr-2017, 11:55:27 PM
    Author     : marlon
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="javascript/action.js"></script>
        <title><c:out value="${friend.firstName}"></c:out> <c:out value="${friend.lastName}"></c:out></title>
        <c:import url="HTMLPartials/navBar.jsp"/>
    </head>
    <body>
        <div id="containerBox" class = "container algin-self-center col-sm-4 col-sm-offset-4">
            <h1><c:out value="${friend.firstName}"></c:out> <c:out value="${friend.lastName}"></c:out> #<c:out value="${friend.pk}"></c:out></h1>
            <label><c:out value="${friend.email}"></c:out></label><br>
            <label class="text-capitalize"><c:out value="${friend.city}"/></label><br>
            <label class="text-capitalize"><c:out value="${friend.province}"/></label><br>
            <label class="text-capitalize"><c:out value="${friend.country}"/></label><br>
            <c:if test="${not empty addFriend}">
                <tr>
                    <td>
                        <input id="friendButton" class="btn btn-lg algin-self-center btn-danger" type="submit" value="Add Friend" onclick="addFriend(${friend.pk})"/>
                    </td>
                </tr>
            </c:if>
        </div>
    </body>
</html>
