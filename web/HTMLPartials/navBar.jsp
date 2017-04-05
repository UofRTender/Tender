<%-- 
    Document   : navBar
    Created on : 20-Feb-2017, 8:47:48 PM
    Author     : marlon
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/signin.css">
    <link rel="stylesheet" href="css/tableCSS.css">
    <script>
        $(document).ready(function () {
            $('ul.nav li.dropdown').hover(function () {
                $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
            }, function () {
                $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
            });
        });
    </script>
</head>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                     
            </button>
            <a class="navbar-brand" style="padding-top: 10px; padding-bottom: 10px;" href="/Tender/profile">
                <img src="/Tender/images/Tender Word_2.png" alt="Tender">
            </a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/Tender/profile">Profile</a></li>
                <li><a href="/Tender/tender">Navigation</a></li>
                <li><a href="/Tender/palette">Palette</a></li>
                <li><a href="/Tender/favourites">Favourites</a></li>
                <li><a href="/Tender/history">History</a></li>                                
                        
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Groups <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="myGroups">My Groups</a></li>
                        <li class="divider"></li>
                        <c:forEach var="group" items="${groups}">
                            <li><a href="viewGroup?id=${group.pk}">${group.name}</a></li>
                        </c:forEach>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-left" action="search" method="get">
                <div class="form-group">
                    <input name="friendToAdd" type="text" class="form-control" id="usr">
                </div>
                <input class="btn-danger btn-sm" type="submit" value="Search"/>
                
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
            </ul>
        </div>
    </div>
</nav>

