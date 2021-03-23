<%-- 
    Document   : searchHistory
    Created on : Mar 20, 2021, 10:03:08 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/car.css">
        <title>SearchHistory</title>
    </head>
    <body>
        <form action="DispatchServlet">

            <div class="textSearch">
                Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />

                <input type="date" name="txtBookingDate" value="${param.txtBookingDate}" />
            </div>


            <div class="buttonSearchAdmin"> 
                <input class="searchButton" type="submit" value="SearchHistory" name="btAction" />
            </div>
        </form>

                <c:set var="result" value="${requestScope.SEARCHHISTORY}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>HistoryID</th>
                        <th>rentalID</th>
                        <th>status</th>
                        <th>action</th>
                        <th>userID</th>
                        <th>bookingDate</th>
                        <th>carID</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                        <tr>
                            <td>${counter.count}
                            .</td>
                            <td>${dto.historyID}</td>
                            <td>${dto.rentalID}</td>
                            <td>${dto.status}</td>
                            <td>${dto.action}</td>
                            <td>${dto.userID}</td>
                            <td>${dto.bookingDate}</td>
                            <td>${dto.carID}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
    </body>
</html>
