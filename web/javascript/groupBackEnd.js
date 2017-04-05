function addHistory() {
    $.get('addHistory',
            {
                job: "add",
                restaurant: restaruants[num].location,
                id: document.getElementById('gname').value,
                table: "grouphistory",
                palette: palette.palette,
                name:restaruants[num].name
            }, function (data) {
        console.log("History");
        console.log(data);
    });
}

function persist() {
    console.log("persist");
    $.get('addHistory',
            {
                job: "check",
                id: document.getElementById('gname').value,
                table: "grouphistory"
            },
            function (data) {
                console.log(data);
                if (data.new != "true") {
                    initMapOld(data);
                } else {
                    updatePage();
                }
            }
    );
}
function updatePage() {
    $.get('addHistory', {
        job: "check",
        table: "temphistory",
        oldid: currentRestaurant,
        id: document.getElementById("gname").value
    }, function (data) {
        //console.log("return");
        console.log(data);
        if (data.load == "true") {
            console.log("currentRes:    "+currentRestaurant);
            currentRestaurant=data.old;
            initMapOld(data);
        } else {
            console.log();
            var intId = setInterval(function () {
                updatePage();
            }, 20000);
        }
    });
}

function addTemp() {
    console.log("addtemp");
    
    $.get('addHistory', {
        job: "temp",
        restaurant:restaruants[num].location,
        table: "temphistory",
        id: document.getElementById("gname").value,
    }, function (data) {
        //updatePage();
        console.log("TEMPHistory");
        console.log(data);
    })
}

function addFavourites() {
    $.get('addFavourites',
            {
                job: "add",
                restaurant: document.getElementById("id").value
            },
            function (data) {
                console.log("favourites");
                var node = document.getElementById("results");

                node.innerHTML = node.innerHTML + "<p>" + data + "</p>";
            });
}

function checkFavourites() {
    $.get('addFavourites',
            {
                job: "check",
                restaurant: document.getElementById("id").value
            },
            function (data) {
                console.log("check");
                console.log(data);
                var node = document.getElementById("results");
                if (data == "true") {
                    node.innerHTML = node.innerHTML + "<p>favourited!!</p>";
                } else {
                    node.innerHTML = node.innerHTML + "<button type='button' onclick='addFavourites()'>Add to Favourites</button>";
                }
            });
}


