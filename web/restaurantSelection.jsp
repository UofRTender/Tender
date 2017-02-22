<%-- 
    Document   : restaurantSelection
    Created on : 9-Jan-2017, 11:46:44 AM
    Author     : marlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDuBvKTgXdc8RWLqkyT4TECNj7vNKoP9NE&libraries=places"></script>
        <script type="text/javascript" src="javascript/maps.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tender</title>
        <script type="text/javascript">
            var address = "258 Read Avenue";
            var city = "Regina";
        </script>

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
    <body>
        <c:import url="HTMLPartials/navBar.jsp"/>
        <button type="button" onclick="initMapRandom()">
            True Random
        </button>
        <button type="button" onclick="initMapPalette()">
            Palette Specific
        </button>
        <div id="results"></div>
        <div id="map"></div>
    </body>
</html>
