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
        <title>JSP Page</title>

        <style>
            /* Always set the map height explicitly to define the size of the div
             * element that contains the map. */
            #map {
                height: 100%;
            }
            /* Optional: Makes the sample page fill the window. */
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }
        </style>
        <script type="text/javascript" src="javascript/maps.js"></script>
    </head>
    <body>


        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBomL35j1WDsULxry-QT68xMbwpU68-diA&callback=initMap">
        </script>
        <div id="map"></div>
    </body>
</html>
