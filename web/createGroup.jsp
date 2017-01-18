<%-- 
    Document   : createGroup
    Created on : 17-Jan-2017, 2:48:00 PM
    Author     : marlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Group</title>
    </head>
    <body>
        <form action="createGroup" method="get">
            <table>
                <tr>
                    <td>Group Name: <input type="text" name="group_name"></td>
                </tr>
                <c:forEach var="conf" items="${confirmed}" varStatus="index">
                
                </c:forEach>
            </table>
            <input type="submit" value="submit">
        </form>
    </body>
</html>
