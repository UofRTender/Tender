<%-- 
    Document   : profile
    Created on : 19-Nov-2016, 9:20:31 PM
    Author     : marlon
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
              <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>                     
                </button>
                <a class="navbar-brand" href="#">Tender</a>
              </div>
              <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="https://142.3.21.236/Tender/profile">Profile</a></li>
                    <li><a href="https://142.3.21.236/Tender/tender">Navigation</a></li>
                    <li><a href="https://142.3.21.236/Tender/palette?">Palette</a></li>
                </ul>
              </div>
            </div>
        </nav>
        
        <div id="containerBox" class = "container algin-self-center col-sm-4 col-sm-offset-4">
            <table>
                <tr>
                    <th><c:out value="${user.pk}"/></th>  
                </tr>
                <tr>
                    <td><c:out value="${sessionScope.personPK}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${user.email}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${user.address}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${user.city}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${user.province}"/></td>
                </tr>
                <tr>
                    <td><c:out value="${user.country}"/></td>
                </tr>
            
                
                <!--<h1><c:out value="${user.pk}"/></h1>
                <p><c:out value="${sessionScope.personPK}"/></p>
                <p><c:out value="${user.email}"/></p>
                <p><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></p>
                <p><c:out value="${user.address}"/></p>
                <p><c:out value="${user.city}"/></p>
                <p><c:out value="${user.province}"/></p>
                <p><c:out value="${user.country}"/></p>-->
                <tr>
                    <td>
                        <form method="post" action="upload" enctype="multipart/form-data">
                            File:
                            <input type="file" name="file"/> <br/>
                            <input type="submit" value="upload"/>
                        </form>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <form action="tender" method="post">
                            <input type="submit" value="Restaurant Selection"/>
                        </form>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <form action="palette" method="get">
                            <input type="submit" value="Palette"/>
                        </form>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <form action="profile" method="get">
                            <input type="text" name="friendToAdd"/> 
                            <input type="submit" value="Gotoprofile"/>
                        </form>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <form action="FriendsList" method="get">
                            <input type="submit" value="Friends List"/>
                        </form>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <form action="myGroups" method="get">
                            <input type="submit" value="My Groups"/>
                        </form>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <c:if test="${not empty addFriend}">
                            <form action="addFriend" method="post">
                                <input type="hidden" value="${addFriend}" name="friendId">
                                <input type="submit" value="add friend" name="${addFriend}"/>
                            </form>
                        </c:if>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <form action="logout" method="post">
                            <input type="submit" value="Logout"/>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
