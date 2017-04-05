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
        <script type="text/javascript" src="javascript/action.js"></script>
        
        <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
        <title>Tender</title>
        <style>
            /* Always set the map height explicitly to define the size of the div
             * element that contains the map. */
            #map {
                height:100%;

            }

            body, html {
                height: 100%;
                width: 100%;
            }
        </style>
    </head>
    <body onload="persist()">
        <c:import url="HTMLPartials/navBar.jsp"/>
        <div id="results" class="container col-sm-6 col-sm-offset-3" style="width:100vh;"></div>
        <div id="containerBox" class="container col-sm-6 col-sm-offset-3" style="width:100vh; height:70vh; padding-top: 15px; padding-bottom: 87px;">
            
            
            <button type="button" class="btn-danger btn-sm" onclick="initMapRandom()">
                True Random
            </button>
            <button type="button"  class="btn-danger btn-sm" onclick="initMapPalette()">
                Palette Specific
            </button>
            <table class="table table-condensed">
                <tr>
                    <td><input id="radius" type="range" min="0" max="50" value="25" step="1" onchange="showValue(this.value)" /></td>
                    <td><span id="range">25 km</span><span> from current location</span></td>
                </tr>
            </table>

            <div id="map"></div>
        </div> 
   </body>
</html>
