<%-- 
    Document   : viewCart
    Created on : Jan 14, 2021, 11:20:03 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Shopping Cart</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope}" >
            <font color="red">
            Welcome, ${sessionScope.FULLNAME}
            </font> <br/>

            <form action="DispatchServlet">
                <input type="submit" value="Logout" name="btAction"/>
            </form>
            <h1>Your shopping cart</h1>
        </c:if>

        <c:if test="${empty sessionScope}" >            
            <a href="login.html">You need to login as member first</a> 
        </c:if> <br/>

        <a href="DispatchServlet?SearchFoods">Back to search</a> <br/>

        <c:set var="checkrole" value="${sessionScope.CHECKROLE}"/>

        <c:if test="${not empty checkrole}">
            You don't have permission to shopping! Please be a user first.
        </c:if> <br/>


        <c:set var="cart" value="${sessionScope.CUSTCART}" />
        <c:if test="${not empty cart}" >
            <form action="DispatchServlet">
                <input type="submit" value="checkOut" name="btAction"/>
            </form>
            <c:set var="getFoods" value="${cart.foods}" />
            <c:if test="${not empty getFoods}" >
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Foods</th>
                            <th>Image</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="foods" items="${getFoods}" varStatus="counter" >
                        <form action="DispatchServlet">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>                                   
                                    ${foods.value.name}
                                </td>
                                <td>
                                    <image src="images/${foods.value.image}" alt="img" with="70" height="70"/> <br/>
                                </td>
                                <td>
                                    <input type="number" name="txtQuantityInCart" value="${foods.value.quantity}" min="1" />
                                </td>
                                <td>
                                    ${foods.value.price * foods.value.quantity} 
                                </td>
                                <td>
                                    <input type="checkbox" name="chkRemoveFoodsFromCart" value="${foods.key}" />
                                    <input type="hidden" name="txtFoodIDInCart" value="${foods.key}" />
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tr>
                            <td colspan="2">
                                <!--                                    <a href="SearchFoodsByNameServlet">Continues Shopping</a>-->
                                <a href="DispatchServlet?btAction=SearchFoodsServlet">Continues Shopping</a>
                            </td>
                            <td>
                                <input type="submit" value="Update quantity" name="btAction"/>
                            </td>
                            <td>
                                ${sessionScope.CUSTCART.total}
                            </td>
                            <td> 
                                <input type="submit" value="Remove selected food" name="btAction"/>
                            </td>
                        </tr> 

                </table>
            </form>

        </c:if>
        <c:if test="${empty getFoods}" >
            Your cart is currently empty.
            <a href="DispatchServlet?btAction=SearchFoodsServlet">Click here to shopping</a>
        </c:if>
    </c:if>
</body>
</html>
