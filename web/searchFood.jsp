<%-- 
    Document   : searchFood
    Created on : Jan 6, 2021, 9:09:20 AM
    Author     : HUYVU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
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

        <c:set var="getRole" value="${sessionScope.GETROLE}" />
        <c:if test="${not empty getRole}">           
            <a href="historyShopping.jsp">History Shoping</a>
        </c:if>

        <h1>Search</h1>
        <form action="DispatchServlet">
            Search: <input type="text" name="txtSearchFood" value="${param.txtSearchFood}" placeholder="enter name"/>  <br/>

            Category: <select name="cbCategory">
                <option value="Choose">Choose Category </option>
                <c:forEach var="category" items="${sessionScope.GETALLCATEGORY}">
                    <option value="${category.cateID}">${category.name}</option>
                </c:forEach>
            </select> <br/>

            <div class="showRangeMin"></div>
            MinPrice: <input type="range" min="0" max="1000" name="txtSearchFoodWithMinPrice" value="1" class="rangeMin" /> 
            <div class="showRangeMax"></div>
            MaxPrice: <input type="range" min="0" max="1000" name="txtSearchFoodWithMaxPrice" value="1000" class="rangeMax" /> <br/>

            <input type="submit" value="Search" name="btAction"  />  <br/>
            ===================================================================== <br/>
            <input type="submit" value="View your cart" name="btAction"/>

        </form>

        =====================================================================

        <c:set var="get20ListFoods" value="${requestScope.GET20LISTFOODFIRST}" />
        <c:if test="${not empty get20ListFoods}" >
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                <h2>Suggestion Foods </h2>
                <c:forEach var="list20foodsDTO" items="${get20ListFoods}" varStatus="counter" >
                    <form action="DispatchServlet">

                        <tr>
                            <th>
                                ${counter.count}
                            </th>
                            <th>
                                ${list20foodsDTO.name}
                            </th>
                            <th>
                                <image src="images/${list20foodsDTO.image}" alt="img" with="70" height="70"/> <br/>
                            </th>
                            <th>
                                ${list20foodsDTO.description}
                            </th>
                            <th>
                                ${list20foodsDTO.price}
                            </th>
                            <th>
                                ${list20foodsDTO.quantity}
                            </th>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if> 


    <c:set var="checkrole" value="${sessionScope.CHECKROLE}"/>
    <c:if test="${not empty checkrole}">
        <a href="createFood.jsp">Click here to create food</a> <br/>   
    </c:if> 


    <c:set var="resultSearchFoods" value="${requestScope.RESULTSEARCH}" />

    <c:if test="${empty resultSearchFoods}" >No record to match</c:if>

    <c:if test="${not empty resultSearchFoods}" >
        <c:set var="checkrole" value="${sessionScope.CHECKROLE}"/>
        <c:if test="${not empty checkrole}">               
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>FoodID</th>
                        <th>Name</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Create Date</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Status</th>
                        <th>cateID</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="getValueSearchFoods" items="${resultSearchFoods}" varStatus="counter" >
                    <form name="testform" action="DispatchServlet?btAction=Update" id="testform"
                          enctype="multipart/form-data" method="post">
                        <tr>
                            <th>
                                ${counter.count}
                            </th>
                            <th>
                                ${getValueSearchFoods.foodID}
                                <input type="hidden" name="txtFoodID" value="${getValueSearchFoods.foodID}" />
                            </th>
                            <th>                               
                                <input type="text" name="txtFoodName" value="${getValueSearchFoods.name}" required="required"/>
                            </th>
                            <th>
                                <image src="images/${getValueSearchFoods.image}" alt="img" with="70" height="70"/> <br/>
                                <input type="file" name="txtFoodImage" value="" />
                            </th>
                            <th>                                    
                                <input type="text" name="txtFoodDescription" value="${getValueSearchFoods.description}" required="required"/>
                            </th>
                            <th>
                                ${getValueSearchFoods.createDate}
                                <input type="hidden" name="txtCreateDate" value="${getValueSearchFoods.createDate}" />
                            </th>
                            <th>

                                <input type="number" name="txtFoodPrice" value="${getValueSearchFoods.price}" required="required" step="0.01"/>
                            </th>
                            <th>                                   
                                <input type="number" name="txtFoodQuantity" value="${getValueSearchFoods.quantity}" required="required"/>
                            </th>
                            <td>
                                ${getValueSearchFoods.status} 
                                <input type="checkbox" name="chkStatus" value="ON" 
                                       <c:if test="${getValueSearchFoods.status}" >
                                           checked = "checked"
                                       </c:if>
                                       />
                            </td>
                            <th>
                                <input type="text" name="txtCateID" value="${getValueSearchFoods.cateID}" />
                            </th>
                            <th>                           
                                <c:url var="urlRewriting" value="DeleteFoodsServlet" >
                                    <c:param name="pkDelete" value="${getValueSearchFoods.foodID}" />
                                    <c:param name="lastSearchValue" value="${param.txtSearchFood}" />
                                    <c:param name="lastSearchMinPrice" value="${param.txtSearchFoodWithMinPrice}" />
                                    <c:param name="lastSearchMaxPrice" value="${param.txtSearchFoodWithMaxPrice}" />
                                    <c:param name="lastSearchCate" value="${param.cbCategory}" />                 
                                </c:url>
                                <a href="${urlRewriting}">Delete</a>

                            </th>
                            <th>
                                <input type="submit" value="Update" name="btAction"/>
                                <input type="hidden" name="lastSearchValue" value="${param.txtSearchFood}" />
                                <input type="hidden" name="lastSearchMinPrice" value="${param.txtSearchFoodWithMinPrice}" />
                                <input type="hidden" name="lastSearchMaxPrice" value="${param.txtSearchFoodWithMaxPrice}" />
                                <input type="hidden" name="lastSearchCate" value="${param.cbCategory}" />
                            </th>
                        </tr>
                    </form>
                </c:forEach>
            </form>
        </tbody>
    </table>
</c:if>

<c:if test="${empty checkrole}">
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>FoodID</th>
                <th>Name</th>
                <th>Image</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Add to cart</th>
            </tr>
        </thead>
        <tbody>

            <c:forEach var="getValueSearchFoods" items="${resultSearchFoods}" varStatus="counter" >
                <c:set var="checkStatus" value="${getValueSearchFoods.status}" />
                <c:if test="${checkStatus == true}" >
                <form action="DispatchServlet">
                    <tr>
                        <th>
                            ${counter.count}
                        </th>
                        <th>
                            ${getValueSearchFoods.foodID}
                            <input type="hidden" name="txtFoodIDUser" value="${getValueSearchFoods.foodID}" />
                        </th>
                        <th>
                            ${getValueSearchFoods.name}
                        </th>
                        <th>                               
                            <image src="images/${getValueSearchFoods.image}" alt="img" with="70" height="70"/> <br/>
                        </th>
                        <th>
                            ${getValueSearchFoods.description}
                        </th>
                        <th>
                            ${getValueSearchFoods.price}
                        </th>
                        <th>
                            ${getValueSearchFoods.quantity}
                        </th>
                        <th>
                            <input type="submit" value="Add to cart" name="btAction"/>
                            <input type="hidden" name="lastSearchValue" value="${param.txtSearchFood}">
                            <input type="hidden" name="lastSearchMinPrice" value="${param.txtSearchFoodWithMinPrice}" />
                            <input type="hidden" name="lastSearchMaxPrice" value="${param.txtSearchFoodWithMaxPrice}" />
                            <input type="hidden" name="lastSearchCate" value="${param.cbCategory}" />
                        </th>
                    </tr>
                </form>
            </c:if>
        </c:forEach>

    </c:if>
</c:if>

<script src="./js/main.js"></script>

</body>
</html>
