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
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDuBvKTgXdc8RWLqkyT4TECNj7vNKoP9NE&libraries=places"></script>
        <script type="text/javascript" src="javascript/groupMaps.js"></script>
        <script type="text/javascript" src="javascript/groupBackEnd.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${groupName}"/></title>

        <style>
            /* Always set the map height explicitly to define the size of the div
             * element that contains the map. */
            #map {
                height: 100%;
            }
            /* Optional: Makes the sample page fill the window. */
            html, body{
                height: 70%;
                margin-left: 150px;
                padding: 0;
                margin-right: 150px;
                margin-top: 50px;
            }
        </style>
    </head>
    <body onload="persist()">
        <c:import url="HTMLPartials/navBar.jsp"/>
        <input type="hidden" id="gname" value="${pk}"/>
        <h1><c:out value="${groupName}"/></h1>
        <c:forEach var="user" items="${members}">
            <a href="profile?friendToAdd=${user.pk}"><c:out value="${user.firstName}"></c:out> <c:out value="${user.lastName}"></c:out></a><br>
        </c:forEach>
        <form method="post" action="viewGroup">
            <input type="hidden" name="gpk" value="${pk}"/>
            <input type="submit" value="leave group"/>
        </form>
        <form method="post" action="editGroup">
            <input type="hidden" name="gpk" value="${pk}"/>
            <input type="submit" value="edit group"/>
        </form>

        <button type="button" onclick="initMapRandom()">
            True Random
        </button>
        <!--<button type="button" onclick="initMapPalette()">
            Palette Specific
        </button>-->

        <div id="results"></div>
        <div id="map"></div>
    </body>
</html>
