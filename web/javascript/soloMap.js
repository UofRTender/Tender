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
var palette;
var num = 0;

function rankings(name, score, location, geometry) {
    this.name = name;
    this.score = score;
    this.location = location;
    this.geometry = geometry;
    this.untouchable = score;
}

function initMapRandom() {
    console.log("init");
    palette = "null";
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
    var distance = document.getElementById("radius").value*100;
    //alert(distance);
    service.nearbySearch({
        location: source,
        radius: distance,
        type: ['restaurant']
    }, callbackRandom);
}

function callbackRandom(results, status, pagination) {
    console.log("callback");
    //var node = document.getElementById("results");
    if (status === google.maps.places.PlacesServiceStatus.OK) {
        //var num = Math.floor((Math.random() * results.length));
        for (var i = 0; i < results.length; i++) {
            restaruants.push(new rankings(results[i].name, results[i].rating, results[i].place_id, results[i].geometry.location));
            //node.innerHTML = node.innerHTML + "<p>name: " + results[i].name + "</p>";
        }
        findRestaurant();
        /*if (pagination.hasNextPage) {
         pagination.nextPage();
         } else {
         findRestaurant();
         }*/
    }
}

function findRestaurant() {
    num = Math.floor((Math.random() * restaruants.length));
    var node = document.getElementById("results");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
    node.innerHTML = node.innerHTML + "<p>name: " + restaruants[num].name + "</p>";
    node.innerHTML = node.innerHTML + "<p>rating: " + restaruants[num].untouchable + "</p>";
    //node.innerHTML = node.innerHTML + "<input type='hidden' id='id' value=" + restaruants[num].place_id + ">";
    //node.innerHTML = node.innerHTML + "<input type='hidden' id='name' value=" + restaruants[num].name + ">";
    node.innerHTML = node.innerHTML + "<button type='button' onclick='addHistory()'>Add to History</button>";

    checkFavourites();

    console.log("restaruants");
    console.log(num);
    destination = restaruants[num].geometry;
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
        }, function (error) {
            alert(error.message);
            alert(error.code);
        }, {timeout: 30000});
    } else {
        alert("default");
        setDefault();
    }
}

function PaletteReturn() {
    var service = new google.maps.places.PlacesService(map);
    var distance = document.getElementById("radius").value*100;
    $.get('paletteFetch', function (data) {
        palette = data;
        //console.log(data);
       console.log("palette " + palette.palette);
        var PaletteRequest = {
            location: source,
            radius: distance,
            query: palette.palette,
            openNow: true,
            type: ['restaurant']
        };

        service.textSearch(PaletteRequest, callbackPalette);
    });
}

function callbackPalette(results, status, pagination) {
    //console.log(results);
    for (var i = 0; i < results.length; i++) {
        restaruants.push(new rankings(results[i].name, results[i].rating, results[i].place_id, results[i].geometry.location));
    }
    //console.log("callback ");
    //console.log(restaruants);
    findPaletteRestaurant();
    /*if (pagination.hasNextPage) {
     pagination.nextPage();
     } else {
     findPaletteRestaurant();
     }*/
}

function findPaletteRestaurant(data) {
    /*console.log("find");
    console.log("pre adjust score");*/
    console.log(restaruants);
    num = 0;
    var average = 0.0;

    for (var i = 0; i < restaruants.length; i++) {
        if (restaruants[i].score != undefined) {
            average = average + restaruants[i].score;
        } else {
            restaruants[i].score=average/i;
            restaruants[i].untouchable=average/i;
            average = average + 3;
        }
        //console.log(i+" "+restaruants[i].score);
        //console.log(average);
    }
    //console.log(average);
    average = average / restaruants.length;
    //console.log("average " + average);
    for (var i = 0; i < restaruants.length; i++) {
        if (palette.history.length != 0) {
            //console.log("history!=0");
            //console.log(palette.history);
            for (var k = 0; k < palette.history.length; k++) {
                if (palette.history[k] == restaruants[i].location) {
                    for (var j = 0; j < palette.favourites.length; j++) {
                        if (palette.favourites[j] == restaruants[i].location) {
                            restaruants[i].score *= 1.1;
                        }
                    }
                    switch (k) {
                        case 9:
                            restaruants[i].score -= average;
                            break;
                        case 8:
                            restaruants[i].score -= average * 0.9;
                            break;
                        case 7:
                            restaruants[i].score -= average * 0.8;
                            break;
                        case 6:
                            restaruants[i].score -= average * 0.7;
                            break;
                        case 5:
                            restaruants[i].score -= average * 0.6;
                            break;
                        case 4:
                            restaruants[i].score -= average * 0.5;
                            break;
                        case 3:
                            restaruants[i].score -= average * 0.4;
                            break;
                        case 2:
                            restaruants[i].score -= average * 0.3;
                            break;
                        case 1:
                            restaruants[i].score -= average * 0.2
                            break;
                        case 0:
                            restaruants[i].score -= average * 0.1;
                            break;
                    }
                } else {
                    for (var j = 0; j < palette.favourites.length; j++) {
                        //console.log("else");
                        //console.log(palette.favourites[j]);
                        //console.log(restaruants[i].location);
                        if (palette.favourites[j] == restaruants[i].location) {
                            //console.log("favourited");
                            restaruants[i].score *= 1.1;
                        }
                    }
                }
            }
        } else {
            if (palette.favourites.length != 0) {
                //console.log("no favourites");
                for (var j = 0; j < palette.favourites.length; j++) {
                    if (palette.favourites[j] == restaruants[i].location) {
                        restaruants[i].score *= 1.1;
                    }
                }
            } else {
                //console.log("random number");
                num = Math.floor(Math.random() * restaruants.length);
            }
        }
    }
    var maximum = 0;
    if (num == 0) {
        for (var i = 0; i < restaruants.length; i++) {
            if (restaruants[i].score > maximum) {
                maximum = restaruants[i].score;
                num = i;
            }
        }
    }
    var node = document.getElementById("results");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
    console.log(num);
    node.innerHTML = node.innerHTML + "<p>name: " + restaruants[num].name + "</p>";
    node.innerHTML = node.innerHTML + "<p>rating: " + restaruants[num].untouchable + "</p>";
    node.innerHTML = node.innerHTML + "<button type='button' onclick='addHistory()'>Add to History</button>";
    
    checkFavourites();

    /*console.log("restaruants");
     console.log(num);*/
    destination = restaruants[num].geometry;
    //console.log(restaruants[num].name);
    //console.log(restaruants[num].geometry);

    calculateAndDisplayRoute();
    console.log("post adjust score");
    console.log(restaruants);
}

function initMapOld(placeid) {
    //console.log("init");

    if (placeid.id != '' || placeid.id != undefined) {
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
                console.log("place" + placeid);
                getOldRestaurant(placeid);
            }, function (error) {
                alert(error.message);
                alert(error.code);
            }, {timeout: 30000});
        }
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
    console.log("correct");
    console.log(place);
    restaruants.push(new rankings(place.name, place.rating, place.place_id, place.geometry.location));
    console.log("test");
    console.log(restaruants);
    num = 0;
    destination = place.geometry.location;
    var node = document.getElementById("results");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
    node.innerHTML = node.innerHTML + "<p>name: " + restaruants[0].name + "</p>";
    node.innerHTML = node.innerHTML + "<p>rating: " + restaruants[0].untouchable + "</p>";
    //node.innerHTML = node.innerHTML + "<input type='hidden' id='id' value=" + place.place_id + ">";
    //node.innerHTML = node.innerHTML + "<button type='button' onclick='addHistory()'>Add to History</button>";

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
    //console.log("restaruants");
    //console.log(restaruants);
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
