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
        <script type="text/javascript" src="javascript/soloMap.js"></script>
        <script type="text/javascript" src="javascript/soloBackEnd.js"></script>
        <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
        <title>Tender</title>
        <script type="text/javascript">
            var address = "258 Read Avenue";
            var city = "Regina";
            google.maps.event.addDomListener(window, "resize", function () {
                var center = map.getCenter();
                google.maps.event.trigger(map, "resize");
                map.setCenter(center);
            });
        </script>

        <style>
            /* Always set the map height explicitly to define the size of the div
             * element that contains the map. */
            #map {
                height:50%;
                width:50%;
            }

            body, html {
                height: 100%;
                width: 100%;
            }


        </style>
    </head>
    <body onload="persist()">
        <c:import url="HTMLPartials/navBar.jsp"/>

        <!--<div id="containerBox" class="container col-sm-6 col-sm-offset-3" style="width:100vh; height:80vh; padding-top: 15px; padding-bottom: 35px;">-->
        <div id = "containerBox" class = "text-center container col-sm-4 col-sm-offset-4">
            <button type="button" onclick="initMapRandom()">
                True Random
            </button>
            <button type="button" onclick="initMapPalette()">
                Palette Specific
            </button>
        </div>
        <div id="results" class = "col-sm-4 col-sm-offset-4"></div>
        <div id="map" class = "col-sm-4 col-sm-offset-4"></div>
        <!--</div>-->

    </body>
</html>