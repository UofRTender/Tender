<%-- 
    Document   : login
    Created on : 19-Nov-2016, 7:05:47 PM
    Author     : marlon and chow
    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <title>Tender Login</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/login.css">
        
        
    </head>
    <body>
        
        <div class = "container text-center col-sm-4"></div>
        
        <div id="containerBox" class = "color container text-center col-sm-4">
            
            <form class="form-login" action="login" method="post">
                
                <h1 class="form-login-heading">Tender Login</h1>
                
                <input class="form-control" type="text" name="email" placeholder="Email address" required autofocus >
                    
                <input class="form-control" type="password" name="password" placeholder="Password" required>
                
                <input class="btn btn-lg btn-primary" type="submit" value="Login"/>
                
                <a class="btn btn-lg btn-primary" href="signup.jsp"/>Sign Up</a>
                
            </form>

        </div>
        
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src=”js/bootstrap.js”></script>
        
    </body>
</html>
