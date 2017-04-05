function addHistory() {
    var node = document.getElementById("results");
    node.removeChild(node.childNodes[2]);
    $.get('addHistory',
            {
                job: "add",
                restaurant: restaruants[num].location,
                table: "history",
                name: restaruants[num].name,
                palette: palette.palette
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
                table: "history"
                        //restaurant: document.getElementById('id').value
            }, function (data) {
        if (data.new != "true") {
            console.log("persist");
            console.log(data);
            palette = data.palette;

            initMapOld(data);
        }
    });
}

function addFavourites() {
    $.get('addFavourites',
            {
                job: "add",
                name: restaruants[num].name,
                //palette: palette.palette,
                restaurant: restaruants[num].location
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
                restaurant: restaruants[num].location,
                name: restaruants[num].name
            },
            function (data) {
                console.log("check");
                console.log(data);
                var node = document.getElementById("results");
                if (data == "true") {
                    node.innerHTML = node.innerHTML + "<p>favourited!!</p>";
                } else {
                    node.innerHTML = node.innerHTML + "   <button type='button' class='btn-danger btn-sm' onclick='addFavourites()'>Add to Favourites</button>";
                }

            });
}