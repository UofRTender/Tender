<%-- 
    Document   : palette
    Created on : 9-Jan-2017, 11:43:30 AM
    Author     : marlon
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Palette</title>     
    </head>
    <body>
        <c:import url="HTMLPartials/navBar.jsp"/>
        <div id="containerBox" class="container algin-self-center col-sm-4 col-sm-offset-4">
            <h1 class="text-center">Palette</h1>
            <form action="palette" method="post">
                <table>
                        <c:forEach var="food" items="${foodPreferences}" varStatus="index">
                            <tr>
                                <th><c:out value="${food.key}"></c:out></th>
                            
                            <c:choose>
                                <c:when test="${food.value=='t'}">
                                    <label class="radio-inline"><input type="radio" name="${food.key}" value="1" checked> Like</label>
                                    <label class="radio-inline"><input type="radio" name="${food.key}" value="0"> Dislike</label>
                                </c:when>
                                <c:otherwise>
                                    <label class="radio-inline"><input type="radio" name="${food.key}" value="1"> Like</label>
                                    <label class="radio-inline"><input type="radio" name="${food.key}" value="0" checked> Dislike</label>
                                </c:otherwise>
                            </c:choose>
                            </tr>
                        </c:forEach>
                        <input type="submit" value="Update Palette"/>
                    </table>
            </form>
            
        </div>
    </body>
</html>
