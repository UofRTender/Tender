/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var map;
var infoWindow;
var destination;
var source;
var directionsService = new google.maps.DirectionsService;
var directionsDisplay = new google.maps.DirectionsRenderer;

function initMapRandom() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15
    });
    setDefault();
    infoWindow = new google.maps.InfoWindow();
    directionsDisplay.setMap(map);

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            source = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            infoWindow.setPosition(source);
            map.setCenter(source);
            trueRandomReturn(source);
        });
    } else {
        alert("default");
        setDefault();
    }
}

function trueRandomReturn(pos, test1) {
    var service = new google.maps.places.PlacesService(map);
    service.nearbySearch({
        /*query: [data],*/
        location: pos,
        radius: 500,
        type: ['restaurant']
    }, callbackRandom);
}

function callbackRandom(results, status) {
    var node = document.getElementById("results");
    if (status === google.maps.places.PlacesServiceStatus.OK) {
        var num = Math.floor((Math.random() * results.length));
        node.innerHTML = node.innerHTML + "name: " + results[num].name + "<p>" + "place_id " + results[num].rating + " id " + results[num].id + "</p>";
        destination = results[num].geometry.location;
        calculateAndDisplayRoute();
    }
}

function initMapPalette() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15
    });
    setDefault();
    infoWindow = new google.maps.InfoWindow();
    directionsDisplay.setMap(map);

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            source = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            infoWindow.setPosition(source);
            map.setCenter(source);
            PaletteReturn(source);
        });
    } else {
        alert("default");
        setDefault();
    }
}

function calculateAndDisplayRoute() {
    directionsService.route({
        origin: source,
        destination: destination,
        travelMode: 'DRIVING'
    }, function (response, status) {
        if (status === 'OK') {
            directionsDisplay.setDirections(response);
        } else {
            window.alert('Directions request failed due to ' + status);
        }
    });
}

function setDefault() {
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({
        'address': address,
        componentRestrictions: {
            locality: city
        }
    }, function (results, status) {
        if (status == 'OK') {
            map.setCenter(results[0].geometry.location);
            /*var marker = new google.maps.Marker({
             map: map,
             position: results[0].geometry.location
             });*/
        }
    });
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
}