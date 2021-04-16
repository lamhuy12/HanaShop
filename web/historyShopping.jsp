<%-- 
    Document   : historyShopping
    Created on : Jan 20, 2021, 6:38:30 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History shopping Page</title>
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

        <form action="DispatchServlet">
            <input type="number" name="txtSearchDate" value="" min="1" max="31" placeholder="date"/>
            <input type="submit" value="Search by date" name="btAction"/>
        </form> <br/>
        
            <a href="DispatchServlet?SearchFoods">Back to search</a> <br/>
            
            <c:set var="searchDate" value="${param.txtSearchDate}" />
            <c:if test="${not empty searchDate}" >
                <c:set var="getHistoryShopping" value="${requestScope.HISTORYSHOPPING}" />
                <c:if test="${not empty getHistoryShopping}" >
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>OrderDetailID</th>
                                <th>OrderID</th>
                                <th>foodID</th>
                                <th>Quantity</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="historyShoppingValue" items="${getHistoryShopping}" varStatus="counter" >
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${historyShoppingValue.orderDetailID}
                                    </td>
                                    <td>
                                        ${historyShoppingValue.orderID}
                                    </td>
                                    <td>
                                        ${historyShoppingValue.foodID}
                                    </td>
                                    <td>
                                        ${historyShoppingValue.quantity}
                                    </td>
                                    <td>
                                        ${historyShoppingValue.price}
                                    </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
                <c:if test="${empty getHistoryShopping}" >No history Shopping</c:if>
            </c:if>
    </body>
</html>
