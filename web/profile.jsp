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
        <title>JSP Page</title>
    </head>
    <body>
        <h1><c:out value="${pk}"/></h1>
        <p><c:out value="${sessionScope.personPK}"/></p>
        <p><c:out value="${firstname}"/> <c:out value="${lastname}"/></p>
        <p><c:out value="${test}"/></p>
        <form action="nextpage" method="post">
            <input type="submit" value="test page"/>
        </form>
        <form action="logout" method="post">
            <input type="submit" value="Logout"/>
        </form>
    </body>
</html>
