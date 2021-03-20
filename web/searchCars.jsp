<%-- 
    Document   : searchCars
    Created on : Mar 12, 2021, 8:42:41 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/car.css">
        <title>SearchCars</title>
    </head>
    <body>
        <div class="hearderDisplayAdmin">
            <ul class="nav-listAdmin">
                <li class="nav-itemAdmin">
                    <c:set var="username" value="${sessionScope.FULLNAMEUSER}" />
                    <c:if test="${not empty username}">
                        <font color="red">
                        Welcome, ${sessionScope.FULLNAMEUSER}
                        </font><br>

                    </c:if>
                </li>
                <li class="nav-itemAdmin">
                    <b> <a href="DispatchServlet" title="CarRental">Home</a></b>
                </li>

            </ul>
            <ul class="nav-listAdmin">
                <c:if test="${empty username}">
                    <li class="nav-itemAdmin">
                        <b><a href="login.jsp" title="Login">Sign up</a> </b>
                    </li>
                </c:if>
                <c:if test="${not empty username}">
                    <li class="nav-itemAdmin">
                        <form action="DispatchServlet">
                            <input type="submit" value="LogOut" name="btAction" />
                        </form>
                    </li>
                    <li class="nav-itemAdmin">
                        <form action="DispatchServlet">
                            <input type="submit" value="History" name="btAction" />
                        </form>
                    </li>
                </c:if>
            </ul>
        </div>


        <div  class="searchAdmin">
            <div class="searchAdminList">
                <form action="DispatchServlet">

                    <div class="textSearch">
                        Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />

                        <select name="cboCategory">
                            <option label="Ple choose category" value=""></option>
                            <c:forEach var="cbo" items="${sessionScope.CBOCATEGORY}" varStatus="counter">
                                <option label="${cbo.categoryname}" value="${cbo.categoryID}"></option>
                            </c:forEach>
                        </select>

                        <select name="cboAmountPrice">
                            <option label="Ple choose price" value="999999"></option>
                            <c:forEach var="cbo" items="${sessionScope.ALLPRICE}" varStatus="counter">
                                <option label="${cbo.price}" value="${cbo.price}"></option>

                            </c:forEach>
                        </select>
                    </div>


                    <div class="buttonSearchAdmin"> 
                        <input class="searchButton" type="submit" value="Search" name="btAction" />
                        <input type="hidden" name="txtIndex" value="1" />
                    </div>
                </form>
            </div>
        </div>


        <c:set var="result" value="${requestScope.SEARCHNAME}" />

        <c:if test="${not empty result}">
            <table border="1" class="displayUser">
                <c:forEach var="item" items="${result}" varStatus="counter">
                    <form action="DispatchServlet">
                        <tbody class="itemBodyUser">
                        <td class="itemUser"> 
                            <img src="images/${item.image}"><br>
                            <div class="detaliItem">
                                <input type="hidden" name="txtItemID" value="${item.carID}" />
                                <b>  ${item.description}<br>
                                    <div class="displayItem">                                 
                                        ${item.category} <br>                                                                
                                        ${item.carName}<br>
                                        ${item.quantity}<br>
                                        ${item.price}<br>
                                        ${item.year}<br></b>
                                    </div>       
                                    <input class="Order" type="submit" name="btAction" value="AddCart" />
                                    <input class="Order" type="submit" value="ViewCart" name="btAction" />
                            </div>
                        </td>
                        </tbody>
                    </form>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record
            </h2>
        </c:if>

        <div class="paging">
            <c:forEach begin="1" end="${ENDPAGE}" var="i">
                <a href="DispatchServlet?txtSearchValue=${SEARCHVALUE}&cboAmountPrice=${PRICE}&cboCategory=${categoryID}&txtIndex=${i}&btAction=Search">${i}</a> 

            </c:forEach>
        </div>

    </body>
</html>
