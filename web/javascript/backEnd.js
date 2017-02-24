function addHistory() {
    $.get('addHistory', 
    {
        job: "add",
        restaurant: document.getElementById('id').value
    }, function (data) {
        console.log("History");
        console.log(data);
    });
}
function persist(){
    $.get('addHistory', 
    {
        job: "check",
        //restaurant: document.getElementById('id').value
    }, function (data) {
        if(data!="true"){
            console.log("persist");
            console.log(data.id);
            initMapOld(data);
        }
    });
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
        
        node.innerHTML = node.innerHTML + "<p>"+data+"</p>";
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
        if(data=="true"){ 
            node.innerHTML = node.innerHTML + "<p>favourited!!</p>";
        }else{
            node.innerHTML = node.innerHTML + "<button type='button' onclick='addFavourites()'>Add to Favourites</button>";
           
        }
        
    });
}