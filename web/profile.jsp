<%-- 
    Document   : profile
    Created on : 19-Nov-2016, 9:20:31 PM
    Author     : marlon
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></title>
    </head>
    <body>
        <h1><c:out value="${user.pk}"/></h1>
        <p><c:out value="${sessionScope.personPK}"/></p>
        <p><c:out value="${user.email}"/></p>
        <p><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></p>
        <p><c:out value="${user.address}"/></p>
        <p><c:out value="${user.city}"/></p>
        <p><c:out value="${user.province}"/></p>
        <p><c:out value="${user.country}"/></p>

        <form method="post" action="upload" enctype="multipart/form-data" >
            File:
            <input type="file" name="file"/> <br/>
            <input type="submit" value="upload"/>
        </form>

        <form action="restaurantSelection" method="post">
            <input type="submit" value="Restaurant Selection"/>
        </form>

        <form action="palette" method="get">
            <input type="submit" value="Palette"/>
        </form>

        <form action="profile" method="get">
            <input type="text" name="friendToAdd"/> 
            <input type="submit" value="Gotoprofile"/>
        </form>
        <form action="FriendsList" method="post">
            <input type="submit" value="Friends List"/>
        </form>
        <c:if test="${not empty addFriend}">
            <form action="addFriend" method="post">
                <input type="hidden" value="${addFriend}" name="friendId">
                <input type="submit" value="add friend" name="${addFriend}"/>
            </form>
        </c:if>
        <form action="logout" method="post">
            <input type="submit" value="Logout"/>
        </form>
    </body>
</html>
