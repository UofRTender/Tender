<%-- 
    Document   : navBar
    Created on : 20-Feb-2017, 8:47:48 PM
    Author     : marlon
--%>


<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/signin.css">
    <link rel="stylesheet" href="css/tableCSS.css">
</head>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                     
            </button>
            <a class="navbar-brand" href="/Tender/profile">Tender</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/Tender/profile">Profile</a></li>
                <li><a href="/Tender/tender">Navigation</a></li>
                <li><a href="/Tender/palette">Palette</a></li>
                <li><a href="/Tender/favourites">Favourites</a></li>
                <li><a href="/Tender/history">History</a></li>
                
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </ul>
        </div>
    </div>
</nav>

