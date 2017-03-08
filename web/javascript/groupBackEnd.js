var currentRestaurant="0";

function addHistory() {
    $.get('addHistory',
            {
                job: "add",
                restaurant: document.getElementById('id').value,
                id: document.getElementById('gname').value,
                table: "grouphistory"

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
                //alert(data.new);
                //console.log("persist return");
                //console.log(data.new);
                if (data.new != "true") {
                    //console.log("persist");
                    //console.log(data.id);
                    initMapOld(data);
                } else {
                    updatePage();
                }
            }
    );
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

function updatePage() {
    $.get('addHistory', {
        job: "check",
        table: "temphistory",
        oldid: currentRestaurant,
        id: document.getElementById("gname").value
    }, function (data) {
        //console.log("return");
        //console.log(data.load);
        //console.log("return");
        if (data.load == "true") {
            currentRestaurant=data.old;
            console.log("currentRes:    "+currentRestaurant);
            initMapOld(data);
        } else {
            var intId = setInterval(function () {
                persist();
                updatePage();
            }, 20000);
        }
    });
}

function addTemp() {
    console.log("addtemp");
    $.get('addHistory', {
        job: "temp",
        table: "temphistory",
        id: document.getElementById("gname").value,
        restaurant: document.getElementById('id').value
    }, function (data) {
        updatePage();
        console.log("TEMPHistory");
        console.log(data);
    })
}