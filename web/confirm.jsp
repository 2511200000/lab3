<%-- 
    Document   : confirm
    Created on : Mar 17, 2021, 2:09:17 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/car.css">
        <title>Confirm</title>
    </head>
    <body  <c:if test="${not empty requestScope.ERRDISCOUNT}">
            onload="alertMessage()"
        </c:if> 
        >

        <div class="backgoundCreateItem">
        </div>

        <div class="createPage">
            <div class="createForm"> 
                <form action="DispatchServlet">
                    Discount(if any): <input id="txtTextfile" type="text" name="txtDiscount" value="${param.txtDiscount}" /><br>

                    Customname: <input id="txtTextfile" type="text" name="txtCustomer" value="" /><br>
                    Address: <input id="txtTextfile" type="text" name="txtAddress" value="" /><br>
                    Phonenumber: <input id="txtTextfile" type="text" name="txtPhonenumber" value="" /><br>
                  

                    <c:set var="total" value="${sessionScope.totalAll}" />
                    <c:if test="${not empty total}">
                        Total Bill: ${total}
                    </c:if>
                    <c:set var="total" value="${sessionScope.totalDiscount}" />
                    <c:if test="${not empty totalDiscount}">
                        - ${total}
                    </c:if><br>
                    <c:if test="${not empty discount}">
                        Discount: ${discount}%
                    </c:if><br>
                    <c:set var="totalDiscount" value="${sessionScope.totalAllDícount}" />
                    <c:if test="${not empty totalDiscount}">
                        Total Bill: ${totalDiscount}
                    </c:if>

                    <br>
                    <div class="buttonCreate"> 
                        <input id="buttonLogin" type="submit" value="CheckOut" name="btAction" />
                        <input  id="buttonLogin" type="submit" name="btAction" value="Check Vourcher" />

                    </div>
                </form>
            </div>
        </div>
        <script>
            function alertMessage() {
                alert("${requestScope.ERRDISCOUNT}");
            }


        </script>
    </body>
</html>
