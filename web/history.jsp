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
        <c:set var="history" value="${sessionScope.HISTORY}"/>
        <c:if test="${not empty history}">
            <table border="1">
                <thead>
                    <tr>
                        <th>HistoryID</th>
                        <th>RentalID</th>
                        <th>Status</th>
                        <th>Action</th>
                        <th>UserID</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${history}" varStatus="counter">
                        <tr>
                            <td class="rowAdmin">${dto.historyID}
                            </td>
                            <td class="rowAdmin">
                              ${dto.rentalID}
                        </td>
                    <td class="rowAdmin">${dto.status}</td>
                    <td class="rowAdmin">${dto.action}</td>
                    <td class="rowAdmin">${dto.userID}</td>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
