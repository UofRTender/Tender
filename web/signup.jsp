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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="javascript/action.js"></script>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/signin.css">
        <link rel="stylesheet" href="css/tableCSS.css">
    </head>

    <body>



        <div id="containerBox" class = "container align-self-center col-sm-4 col-sm-offset-4">
            <div class="col-4 text-center">
                <h1 class="form-login-heading">Tender Sign Up</h1>
            </div>

            <div class="table-responsive">
                <table class="table table-hover table-condensed">
                    <tr>
                        <th scope="row">Email: </th>
                        <th scope="row"><input type="text" id="email"></th>
                    </tr>
                    <tr>
                        <th>Password: </th>
                        <th><input type="password" id="password"></th>
                    </tr>
                    <tr>
                        <th>Confirm Password: </th>
                        <th><input type="password" id="confirm_password"></th>
                    </tr>
                    <tr>
                        <th>First Name: </th>
                        <th><input type="text" id="first_name"></th>
                    </tr>
                    <tr>
                        <th>Last Name: </th>
                        <th><input type="text" id="last_name"></th>
                    </tr>
                    <tr>
                        <th>Address: </th>
                        <th><input type="text" id="address"></th>
                    </tr>
                    <tr>
                        <th>City: </th>
                        <th><input type="text" id="city"></th>
                    </tr>
                    <tr>
                        <th>Country: </th>
                        <th><input type="text" id="country"></th>
                    </tr>
                    <tr>
                        <th>Province: </th>
                        <th><input type="text" id="province"></th>
                    </tr>
                </table>
                <button class="center-block btn btn-lg btn-danger" type="button" onclick="signup()">
                    Sign Up
                </button>
                
                <div id="error"></div>
            </div>
        </div>

    </div>

</body>
</html>
