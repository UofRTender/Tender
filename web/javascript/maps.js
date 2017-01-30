/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var map;
var infoWindow;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -33.867, lng: 151.195},
        zoom: 15
    });
    infoWindow = new google.maps.InfoWindow();

    var service = new google.maps.places.PlacesService(map);
    service.nearbySearch({
        location: {lat: -33.867, lng: 151.195},
        radius: 500,
        type: ['restaurant']
    }, callback);
}


function callback(results, status) {
    var node = document.getElementById("results");
    if (status === google.maps.places.PlacesServiceStatus.OK) {
        for (var i = 0; i < results.length; i++) {
            createMarker(results[i]);
            node.innerHTML = node.innerHTML + " " + results[i].types+" ";
        }
    }
}

function createMarker(place) {
    var placeLoc = place.geometry.location;
    var marker = new google.maps.Marker({
        map: map,
        position: place.geometry.location
    });
    
    
    google.maps.event.addListener(marker, 'click', function () {
        infoWindow.setContent(place.id);
        infoWindow.open(map, this);
    });
}


function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
}