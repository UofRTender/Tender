<%-- 
    Document   : restaurantSelection
    Created on : 9-Jan-2017, 11:46:44 AM
    Author     : marlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tender</title>

        <style>
            /* Always set the map height explicitly to define the size of the div
             * element that contains the map. */
            #map {
                height: 100%;
            }
            /* Optional: Makes the sample page fill the window. */
            html, body {
                height: 70%;
                margin-left: 150px;
                padding: 0;
                margin-right: 150px;
                margin-top: 50px;
            }
        </style>
        <script  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDuBvKTgXdc8RWLqkyT4TECNj7vNKoP9NE&libraries=places"></script>
        <script type="text/javascript" src="javascript/maps.js"></script>
    </head>
    <body>


        <button type="button" onclick="initMap()">
            True Random</button>
        <div id="map"></div>
        <div id="results"></div>
    </body>
</html>
