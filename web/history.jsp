<%-- 
    Document   : history
    Created on : Mar 20, 2021, 2:10:54 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/car.css">
        <title>History</title>
    </head>
    <body>
        <div class="backgoundCreateAccount"></div>
        <c:set var="history" value="${sessionScope.HISTORY}"/>
        <c:if test="${not empty history}">
            <div class="displayAdmin">
                <table border="1" class="tableAdmin">
                    <thead>
                        <tr>
                            <th class="columnAdmin">HistoryID</th>
                            <th class="columnAdmin">RentalID</th>
                            <th class="columnAdmin">Status</th>
                            <th class="columnAdmin">rental Date</th>
                            <th class="columnAdmin">return Date</th>
                            <th class="columnAdmin">Action</th>
                            <th class="columnAdmin">UserID</th>
                            <th class="columnAdmin">TotalAll</th>
                            <th class="columnAdmin">Details</th>
                            <th class="columnAdmin">Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${history}" varStatus="counter">
                            <c:forEach var="rental" items="${sessionScope.RENTAL}">
                                <c:if test="${dto.rentalID eq rental.rentalID}">
                                <form action="DispatchServlet">
                                    <tr>
                                        <td class="rowAdmin">${dto.historyID}
                                        </td>
                                        <td class="rowAdmin">
                                            ${dto.rentalID}
                                        </td>
                                        <td class="rowAdmin">${dto.status}</td>
                                        <td class="rowAdmin">${rental.rentalDate}</td>
                                        <td class="rowAdmin">${rental.returnDate}</td>
                                        <td class="rowAdmin">${dto.action}</td>
                                        <td class="rowAdmin">${dto.userID}</td>
                                        <td class="rowAdmin">${rental.totalAll}</td>
                                        <td class="rowAdmin">
                                            <input type="submit" value="Detalis" name="btAction" />
                                        </td>
                                        <td class="rowAdmin">
                                            <input type="submit" value="Update" name="btAction" />
                                        </td>
                                    </tr>
                                </form>

                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <a href="DispatchServlet">Add more cars to your cart</a>
    </body>
</html>
