<%-- 
    Document   : history
    Created on : Jan 20, 2021, 2:23:02 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
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
        
        <a href="DispatchServlet?SearchFoods">Back to search</a> <br/>

        <c:set var="getHistory" value="${requestScope.HISTORY}" />
        <c:if test="${not empty getHistory}" >
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Action</th>
                        <th>Time</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${getHistory}" varStatus="counter" >
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.action}
                            </td>
                            <td>
                                ${dto.time}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty getHistory}">No history </c:if>
    </body>
</html>
