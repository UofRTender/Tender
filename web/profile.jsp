<%-- 
    Document   : profile
    Created on : 19-Nov-2016, 9:20:31 PM
    Author     : marlon and chow
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title> <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/> </title>
    <c:import url="HTMLPartials/navBar.jsp"/>

    <div id="containerBox" class = "container algin-self-center col-sm-4 col-sm-offset-4">

        <h1 class="text-center">
            <th class="text-center"><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></th>
        </h1>

        <table class="table table-hover table-condensed">
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
                    <form action="tender" method="post">
                        <input class="btn btn-block btn-danger" type="submit" value="Restaurant Selection"/>
                    </form>
                </td>
            </tr>

            <tr>
                <td>
                    <form action="palette" method="get">
                        <input class="btn btn-block btn-danger" type="submit" value="Palette"/>
                    </form>
                </td>
            </tr>

            <tr>
                <td>
                    <form action="profile" method="get">
                        <div class="form-group">
                            <label class="text-center" for="usr">Friend Search:</label>
                            <input name="friendToAdd" type="text" class="form-control" id="usr">
                        </div>
                        <input class="btn btn-block btn-danger" type="submit" value="Search"/>
                    </form>
                </td>
            </tr>

            <tr>
                <td>
                    <form action="FriendsList" method="get">
                        <input class="btn btn-block btn-danger" type="submit" value="Friends List"/>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="favourites" method="post">
                        <input class="btn btn-block btn-danger" type="submit" value="Favourites"/>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="history" method="post">
                        <input class="btn btn-block btn-danger" type="submit" value="History"/>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form action="myGroups" method="get">
                        <input class="btn btn-block btn-danger" type="submit" value="My Groups"/>
                    </form>
                </td>
            </tr>

            <c:if test="${not empty addFriend}">
                <tr>
                    <td>
                        <form action="addFriend" method="post">
                            <input type="hidden" value="${addFriend}" name="friendId">
                            <input class="btn btn-block btn-danger" type="submit" value="add friend" name="${addFriend}"/>
                        </form>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form action="logout" method="post">
                        <input class="btn btn-danger btn-block" type="submit" value="Logout"/>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
