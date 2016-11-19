<%-- 
    Document   : signup
    Created on : 19-Nov-2016, 3:03:21 PM
    Author     : marlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup</title>
    </head>

    <body>
        <form action="submit" method="post">
            <table>
                <tr>
                    <td>First Name <input type="text" name="first_name"></td>
                </tr>
                <tr>
                    <td>Last Name <input type="text" name="last_name"></td>
                </tr>
                <tr>
                    <td>Password <input type="password" name="password"></td>
                </tr>
                <tr>
                    <td>Confirm password <input type="password" name="confirm_password"></td>
                </tr>
                <tr>
                    <td>Email Address <input type="text" name="email"></td>
                </tr>
                <tr>
                    <td>Address <input type="text" name="address"></td>
                </tr>
                <tr>
                    <td>City <input type="text" name="city"></td>
                </tr>
                <tr>
                    <td>Province <input type="text" name="province"></td>
                </tr>
                <tr>
                    <td>Country <input type="text" name="country"></td>
                </tr>
            </table>
            <input type="submit" value="submit">
        </form>
    </body>
</html>
