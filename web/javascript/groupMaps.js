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
var palette;
var paletter;
var num = 0;
var currentRestaurant = "0";

function rankings(name, score, location, geometry) {
    this.name = name;
    this.score = score;
    this.location = location;
    this.geometry = geometry;
    this.untouchable = score;
}

function initMapRandom() {
    console.log("init");
    paletter="null";
    restaruants = [];
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15
    });

    infoWindow = new google.maps.InfoWindow();
    directionsDisplay.setMap(map);

    if (navigator.geolocation) {
        console.log("geo Enable");
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
            console.log("calling random");
            trueRandomReturn();

            //console.log("returned");
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
    //console.log(source);
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
            restaruants.push(new rankings(results[i].name, results[i].rating, results[i].place_id, results[i].geometry.location));
        }
        console.log(restaruants);
        findRestaurant();
        /*if (pagination.hasNextPage) {
         pagination.nextPage();
         } else {
         findRestaurant();
         }*/
    }
}

function findRestaurant() {
    console.log("find restaurant");
    num = Math.floor((Math.random() * restaruants.length));
    var node = document.getElementById("results");
    var container = document.getElementById("containerBox");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
    node.innerHTML = node.innerHTML + "<h3>Group Tender</h3>";
    node.innerHTML = node.innerHTML + "<p>name: " + restaruants[num].name + "</p>";
    node.innerHTML = node.innerHTML + "<p>rating: " + restaruants[num].untouchable + "</p>";
    //node.innerHTML = node.innerHTML + "<input type='hidden' id='id' value=" + restaruants[num].place_id + ">";
    node.innerHTML = node.innerHTML + "<button type='button' class='btn-danger btn-sm' onclick='addHistory()'>Add to History</button>";
    console.log("print stuff");
    console.log(num + "  " + restaruants[num].name + "  " + restaruants[num].location);
    node.style.backgroundColor = "white";
    node.style.borderRadius = "25px 25px 0px 0px";
    node.style.boxShadow = "10px 10px 10px";

    container.style.borderRadius = "0px 0px 25px 25px";

    currentRestaurant = restaruants[num].location;
    addTemp();
    destination = restaruants[num].geometry;
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
            //console.log("palette return");
            PaletteReturn(source);
        });
    } else {
        alert("default");
        setDefault();
    }
}

function PaletteReturn() {
    var service = new google.maps.places.PlacesService(map);
    console.log("palette reutrn");
    paletter=palette.palette;
    $.get('groupTender',
            {
                gpk: document.getElementById("gname").value
            }
    , function (data) {
        console.log(data);
        palette = data;
        PaletteRequest = {
            location: source,
            radius: '10000',
            query: paletter,
            openNow: true,
            type: ['restaurant', 'food']
        };

        service.textSearch(PaletteRequest, callbackPalette);
        /*if(i==data.liked.length-1){
         findPaletteRestaurant(data);
         }*/

    });
}

function callbackPalette(results, status, pagination) {
    //console.log(PaletteRequest["query"]);
    console.log("callback palette");
    for (var i = 0; i < results.length; i++) {
        restaruants.push(new rankings(results[i].name, results[i].rating, results[i].place_id, results[i].geometry.location));
    }
    if (restaruants.length == 0) {
        trueRandomReturn();
    } else {
        console.log(restaruants);
        findPaletteRestaurant();
    }

    /*for (var i = 0; i < results.length; i++) {
     restaruants.push(results[i]);
     }
     if (pagination.hasNextPage) {
     //pagination.nextPage();
     }else{
     restaruants.push();
     }*/
}

function findPaletteRestaurant(data) {
    console.log("find");
    console.log(restaruants);
    num = 0;
    var average = 0.0;

    for (var i = 0; i < restaruants.length; i++) {
        if (restaruants[i].score != undefined) {
            average = average + restaruants[i].score;
        } else {
            average = average + 3;
        }
    }
    average = average / restaruants.length;
    console.log("average+average" + average);
    for (var i = 0; i < restaruants.length; i++) {
        if (palette.groupHistory.length == 0 && palette.indivHistory.length == 0) {
            console.log("random number");
            num = Math.floor(Math.random() * restaruants.length);
        } else {
            if (palette.groupHistory.length != 0) {
                for (var k = 0; k < palette.groupHistory.length; k++) {
                    if (palette.groupHistory[k] == restaruants[i].location) {
                        restaruants[i].score -= average * (k + 1) / 10;
                    }
                }
            }

            if (palette.indivHistory.length != 0) {
                for (var k = 0; k < palette.indivHistory.length; k++) {
                    if (palette.indivHistory[k] == restaruants[i].location) {
                        restaruants[i].score -= average * (k + 1) / 100;
                    }
                }
            }
        }
        if (palette.favourites.length != 0) {
            for (var k = 0; k < palette.favourites.length; k++) {
                if (palette.indivHistory[k] == restaruants[i].location) {
                    restaruants[i].score *= 1.1;
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
    }
    console.log("number of thing to grab" + num);
    console.log(restaruants);
    var node = document.getElementById("results");
    var container = document.getElementById("containerBox");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
    node.innerHTML = node.innerHTML + "<h3>Group Tender</h3>";
    node.innerHTML = node.innerHTML + "<p>name: " + restaruants[num].name + "</p>";
    node.innerHTML = node.innerHTML + "<p>rating: " + restaruants[num].untouchable + "</p>";
    //node.innerHTML = node.innerHTML + "<input type='hidden' id='id' value=" + restaruants[num].place_id + ">";
    //node.innerHTML = node.innerHTML + "<input type='hidden' id='name' value=" + restaruants[num].name + ">";
    node.innerHTML = node.innerHTML + "<button type='button' class='btn-danger btn-sm' onclick='addHistory()'>Add to History</button>";
    node.style.backgroundColor = "white";
    node.style.borderRadius = "25px 25px 0px 0px";
    node.style.boxShadow = "10px 10px 10px";

    container.style.borderRadius = "0px 0px 25px 25px";
    destination = restaruants[num].geometry;
    addTemp();
    calculateAndDisplayRoute();
    console.log("post adjust score");
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
    restaruants.length = 0;
    restaruants.push(new rankings(place.name, place.rating, place.place_id, place.geometry.location));
    destination = place.geometry.location;

    var node = document.getElementById("results");
    var container = document.getElementById("containerBox");
    while (node.firstChild) {
        node.removeChild(node.firstChild);
    }
    node.innerHTML = node.innerHTML + "<h3>Group Tender</h3>";
    node.innerHTML = node.innerHTML + "<p>name: " + restaruants[num].name + "</p>";
    node.innerHTML = node.innerHTML + "<p>rating: " + restaruants[num].untouchable + "</p>";
    //node.innerHTML = node.innerHTML + "<input type='hidden' id='id' value=" + restaruants[num].place_id + ">";
    //node.innerHTML = node.innerHTML + "<input type='hidden' id='name' value=" + restaruants[num].name + ">";
    node.innerHTML = node.innerHTML + "<button type='button' class='btn-danger btn-sm' onclick='addHistory()'>Add to History</button>";
    node.style.backgroundColor = "white";
    node.style.borderRadius = "25px 25px 0px 0px";
    node.style.boxShadow = "10px 10px 10px";

    container.style.borderRadius = "0px 0px 25px 25px";

    //checkFavourites();
    updatePage();
    calculateAndDisplayRoute();

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
