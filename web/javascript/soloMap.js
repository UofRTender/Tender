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
var restaruants = [];
var PaletteRequest;

function initMapRandom() {
    console.log("init");
    restaruants = [];
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15
    });

    infoWindow = new google.maps.InfoWindow();
    directionsDisplay.setMap(map);

    if (navigator.geolocation) {
        console.log("geo Enable");
        navigator.geolocation.getCurrentPosition(function (position) {
            console.log("start");
            source = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            console.log("set position");
            infoWindow.setPosition(source);
            console.log("set center");
            map.setCenter();
            console.log("calling random");

            trueRandomReturn();

            console.log("returned");
        }, function (error) {
            alert(error.message);
            alert(error.code);
        }, {timeout: 30000});
    } else {
        alert("default");
        setDefault();
    }

}

function trueRandomReturn() {
    console.log(source);
    var service = new google.maps.places.PlacesService(map);
    service.nearbySearch({
        location: source,
        radius: 10000,
        type: ['restaurant']
    }, callbackRandom);
}

function callbackRandom(results, status, pagination) {
    console.log("callback");
    //var node = document.getElementById("results");
    if (status === google.maps.places.PlacesServiceStatus.OK) {
        //var num = Math.floor((Math.random() * results.length));
        for (var i = 0; i < results.length; i++) {
            restaruants.push(results[i]);
            //node.innerHTML = node.innerHTML + "<p>name: " + results[i].name + "</p>";
        }
        if (pagination.hasNextPage) {
            pagination.nextPage();
        } else {
            findRestaurant();
        }
    }
}

function findRestaurant() {
    var num = Math.floor((Math.random() * restaruants.length));
    var node = document.getElementById("results");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
    node.innerHTML = node.innerHTML + "<p>name: " + restaruants[num].name + "</p>";
    node.innerHTML = node.innerHTML + "<p>rating: " + restaruants[num].rating + "</p>";
    node.innerHTML = node.innerHTML + "<input type='hidden' id='id' value=" + restaruants[num].place_id + ">";
    node.innerHTML = node.innerHTML + "<button type='button' onclick='addHistory()'>Add to History</button>";

    checkFavourites();

    console.log("restaruants");
    console.log(num);
    destination = restaruants[num].geometry.location;
    console.log(restaruants[num].name);
    console.log(restaruants[num].geometry.location);

    calculateAndDisplayRoute();

}

function initMapPalette() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15
    });

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

function PaletteReturn() {
    var service = new google.maps.places.PlacesService(map);
    $.get('tender', function (data) {
        console.log(data);
        for (var i = 0; i < data.liked.length; i++) {
            type=data.liked;
            console.log(i);
            PaletteRequest = {
                location: source,
                radius: '100',
                query: data.liked[i],
                openNow: true,
                type: ['restaurant','food']
            };
           
            service.textSearch(PaletteRequest, callbackPalette);
            if(i==data.liked.length-1){
                findPaletteRestaurant(data);
            }
        }
    });
}

function callbackPalette(results, status, pagination) {
    console.log(PaletteRequest["query"]);
    for (var i = 0; i < results.length; i++) {
        restaruants.push(results[i]);
    }
    if (pagination.hasNextPage) {
        //pagination.nextPage();
    }else{
        restaruants.push();
    }
}

function findPaletteRestaurant(data){
    console.log("find");
    console.log(restaruants);
}

function initMapOld(placeid) {
    //console.log("init");
    restaruants = [];
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15
    });

    infoWindow = new google.maps.InfoWindow();
    directionsDisplay.setMap(map);

    if (navigator.geolocation) {
        //console.log("geo Enable");
        navigator.geolocation.getCurrentPosition(function (position) {
            //console.log("start");
            source = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            //console.log("set position");
            infoWindow.setPosition(source);
            //console.log("set center");
            map.setCenter();
            console.log("check placeid")
            console.log(placeid);
            getOldRestaurant(placeid);
        }, function (error) {
            alert(error.message);
            alert(error.code);
        }, {timeout: 30000});
    }
}

function getOldRestaurant(placeid) {
    console.log("old");
    console.log(placeid.id);

    var service = new google.maps.places.PlacesService(map);

    service.getDetails({
        placeId: placeid.id
    }, callbackOld);
}

function callbackOld(place, status) {
    destination = place.geometry.location;
    var node = document.getElementById("results");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
    node.innerHTML = node.innerHTML + "<p>name: " + place.name + "</p>";
    node.innerHTML = node.innerHTML + "<p>rating: " + place.rating + "</p>";
    node.innerHTML = node.innerHTML + "<input type='hidden' id='id' value=" + place.place_id + ">";
    node.innerHTML = node.innerHTML + "<button type='button' onclick='addHistory()'>Add to History</button>";

    checkFavourites();
    calculateAndDisplayRoute()
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
    console.log("restaruants");
    console.log(restaruants);
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
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
            source = results[0].geometry.location;
            map.setCenter(results[0].geometry.location);
        }
        console.log("default return");
    });
}
