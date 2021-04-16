<%-- 
    Document   : createFoods
    Created on : Jan 13, 2021, 12:37:24 PM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create foods/drink</title>
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

        <h1>Create Food</h1>
        <form name="testform" action="DispatchServlet?btAction=Create" id="testform"
              enctype="multipart/form-data" method="post">
            FoodID: <input type="text" name="txtFoodIDCreate" value="" maxlength="50" required="required"/> <br/>
            Name: <input type="text" name="txtFoodNameCreate" value="" maxlength="50" required="required"/> <br/>
            Description: <input type="text" name="txtFoodDescriptionCreate" value="" required="required"/> <br/>
            Price: <input type="number" name="txtFoodPriceCreate" value="" min="0" max="10000" required="required" step="0.01"/> <br/>
            Quantity: <input type="number" name="txtFoodQuantityCreate" value="" min="0" max="10000" required="required" /> <br/>
            Category: <select name="cbCategory">
                <c:forEach var="category" items="${sessionScope.GETALLCATEGORY}">
                    <option value="${category.cateID}">${category.name}</option>
                </c:forEach>
            </select> <br/>
            File <input type="file" name="fileUp" value=""> <br/>
            <input type="submit" value="Create" name="btAction"/> 
        </form>

        <a href="DispatchServlet?SearchFoods">Back to search</a>

    </body>
</html>
