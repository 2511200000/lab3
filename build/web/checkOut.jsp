<%-- 
    Document   : checkOut
    Created on : Mar 18, 2021, 12:45:07 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/car.css">
        <title>CheckOut</title>
    </head>
    <body>
        <div class="backgoundCreateItem">
        </div>

        <c:set var="cart" value="${sessionScope.DISPLAYORDER}" />
        <c:if test="${not empty cart}">
            <div class="displayAdmin">
                <table border="1" class="tableAdmin">
                    <thead>
                        <tr>
                            <th  class="columnAdmin">No.</th>
                            <th  class="columnAdmin">customerID</th>
                            <th  class="columnAdmin">totalAll</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${cart}" varStatus="counter">
                        <form action="DispatchServlet">
                            <tr>
                                <td class="rowAdmin">${counter.count}
                                    .</td>
                                <td class="rowAdmin">${dto.customerID}</td>
                                <td class="rowAdmin">${dto.totalAll}</td>
                            </tr>
                        </form>
                    </c:forEach>

                    </tbody>
                </table>

            </div>
            <a href="DispatchServlet">Add more cars to your cart</a>
        </c:if>
    </body>
</html>
