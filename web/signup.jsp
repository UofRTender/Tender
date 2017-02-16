<%-- 
    Document   : signup
    Created on : 19-Nov-2016, 3:03:21 PM
    Author     : marlon and chow
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Signup</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/signin.css">
        <link rel="stylesheet" href="css/tableCSS.css">
    </head>

    <body>
      <form action="signup" method="post">
        <div id="containerBox" class = "container align-self-center">
            <div class="col-4 text-center">
                <h1 class="form-login-heading">Tender Sign Up</h1>
            </div>
            <div class="col-sm-4"></div>
            <div class="col-sm-4 table-responsive">
                <table class="table table-hover table-condensed">
                    <tr>
                        <th></th>
                        <th scope="row">Email: </th>
                        <th scope="row"><input type="text" name="email"></th>
                        <th></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th>Confirm Email: </th>
                        <th><input type="text" name="conEmail"></th>
                        <th></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th>Password: </th>
                        <th><input type="text" name="password"></th>
                        <th></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th>Confirm Password: </th>
                        <th><input type="text" name="conPassword"></th>
                        <th></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th>First Name: </th>
                        <th><input type="text" name="firstName"></th>
                        <th></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th>Last Name: </th>
                        <th><input type="text" name="lastName"></th>
                        <th></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th>Address: </th>
                        <th><input type="text" name="address"></th>
                        <th></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th>City: </th>
                        <th><input type="text" name="city"></th>
                        <th></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th>Country: </th>
                        <th><input type="text" name="country"></th>
                        <th></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th>Province: </th>
                        <th><input type="text" name="firstName"></th>
                        <th></th>
                    </tr>
                </table>
                <input class="center-block btn btn-lg btn-primary" type="submit" value="submit">
                <label></label>
            </div>
            </div>
        </div>
                

        
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
         <script src=”bootstrap.js”></script>
    </body>
</html>
