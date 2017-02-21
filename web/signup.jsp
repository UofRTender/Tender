<%-- 
    Document   : signup
    Created on : 19-Nov-2016, 3:03:21 PM
    Author     : marlon and chow
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
        <title>Signup</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/signin.css">
        <link rel="stylesheet" href="css/tableCSS.css">
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
      <form action="signup" method="post">
        
        <div id="containerBox" class = "container align-self-center col-sm-4 col-sm-offset-4">
            <div class="col-4 text-center">
                <h1 class="form-login-heading">Tender Sign Up</h1>
            </div>
            
            <div class="table-responsive">
                <table class="table table-hover table-condensed">
                    <tr>
                        
                        <th scope="row">Email: </th>
                        <th scope="row"><input type="text" name="email"></th>
                        
                    </tr>
                    <tr>
                        
                        <th>Confirm Email: </th>
                        <th><input type="text" name="conEmail"></th>
                        
                    </tr>
                    <tr>
                        
                        <th>Password: </th>
                        <th><input type="text" name="password"></th>
                        
                    </tr>
                    <tr>
                        
                        <th>Confirm Password: </th>
                        <th><input type="text" name="conPassword"></th>
                        
                    </tr>
                    <tr>
                        
                        <th>First Name: </th>
                        <th><input type="text" name="firstName"></th>
                        
                    </tr>
                    <tr>
                        
                        <th>Last Name: </th>
                        <th><input type="text" name="lastName"></th>
                        
                    </tr>
                    <tr>
                        
                        <th>Address: </th>
                        <th><input type="text" name="address"></th>
                        
                    </tr>
                    <tr>
                       
                        <th>City: </th>
                        <th><input type="text" name="city"></th>
                        
                    </tr>
                    <tr>
                        
                        <th>Country: </th>
                        <th><input type="text" name="country"></th>
                        
                    </tr>
                    <tr>
                        
                        <th>Province: </th>
                        <th><input type="text" name="firstName"></th>
                        
                    </tr>
                </table>
                <input class="center-block btn btn-lg btn-primary" type="submit" value="submit">
                <label></label>
            </div>
            </div>
        </div>
                

        
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
         <!--<script src=”bootstrap.js”></script>-->
         <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
