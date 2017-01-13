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
        <title><c:out value="${firstname}"/> <c:out value="${lastname}"/></title>
    </head>
    <body>
        <h1><c:out value="${pk}"/></h1>
        <p>test <c:out value="${sessionScope.personPK}"/></p>
        <p>test <c:out value="${sessionScope.personAddress}"/></p>
        <p>test <c:out value="${sessionScope.personCity}"/></p>
        <p><c:out value="${test}"/></p>
        <p><c:out value="${firstname}"/> <c:out value="${lastname}"/></p>
        

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
        <form action="friendsList" method="get">
            <input type="submit" value="friendsList"/>
        </form>
        <form action="logout" method="post">
            <input type="submit" value="Logout"/>
        </form>
    </body>
</html>
