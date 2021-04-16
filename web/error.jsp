<%-- 
    Document   : error
    Created on : Jan 20, 2021, 2:51:56 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.FULLNAME}" >
            <font color="red">
            Welcome, ${sessionScope.FULLNAME}
            </font> <br/>

            <form action="DispatchServlet">
                <input type="submit" value="Logout" name="btAction"/>
            </form>
        </c:if>

        <c:if test="${empty sessionScope.FULLNAME}" >
            <a href="login.html">Login</a> 
        </c:if>


        <c:set var="checkrole" value="${sessionScope.CHECKROLE}"/>
        <c:if test="${not empty checkrole}">
            <form action="DispatchServlet">
                <input type="submit" value="View history" name="btAction"/>
            </form>  
        </c:if> 

        <h1>Something went wrong. Click back</h1> <br/>
        <a href="DispatchServlet?SearchFoods">Back to search</a>
    </body>
</html>
