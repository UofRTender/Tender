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
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/signin.css">
        <link rel="stylesheet" href="css/tableCSS.css">
    </head>

    <body>
        
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

                            <th>Password: </th>
                            <th><input type="text" name="password"></th>

                        </tr>
                        <tr>

                            <th>Confirm Password: </th>
                            <th><input type="text" name="confirm_password"></th>

                        </tr>
                        <tr>

                            <th>First Name: </th>
                            <th><input type="text" name="first_name"></th>

                        </tr>
                        <tr>

                            <th>Last Name: </th>
                            <th><input type="text" name="last_name"></th>

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
                            <th><input type="text" name="province"></th>

                        </tr>
                    </table>
                    <input class="center-block btn btn-lg btn-danger" type="submit" value="submit">
                    <label></label>
                </div>
            </div>
        </form>
    </div>

</body>
</html>
