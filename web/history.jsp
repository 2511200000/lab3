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
        <form action="DispatchServlet">

            <div class="textSearch">
                Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />

                <input type="date" name="txtBookingDate" value="${param.txtBookingDate}" />
            </div>


            <div class="buttonSearchAdmin"> 
                <input class="searchButton" type="submit" value="SearchHistory" name="btAction" />
            </div>
        </form>

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
                            <th class="columnAdmin">Action</th>
                            <th class="columnAdmin">UserID</th>
                            <th class="columnAdmin">bookingDate</th>
                            <th class="columnAdmin">carID</th>
                              <th class="columnAdmin">Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${history}" varStatus="counter">
                                                       
                                <form action="DispatchServlet">
                                    <tr>
                                        <td class="rowAdmin">${dto.historyID}
                                            <input type="hidden" name="txtHistoryID" value="${dto.historyID}" />
                                        </td>
                                        <td class="rowAdmin">
                                            ${dto.rentalID}
                                        </td>
                                        <td class="rowAdmin">${dto.status}</td>

                                        <td class="rowAdmin">${dto.action}</td>
                                        <td class="rowAdmin">${dto.userID}</td>
                                        <td class="rowAdmin">${dto.bookingDate}</td>
                                        <td class="rowAdmin">${dto.carID}</td>
                                        
                                        <td class="rowAdmin">
                                            <input type="submit" value="Update" name="btAction" />
                                        </td>
                                    </tr>
                                </form>
                     </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty history}"> 
            No record
        </c:if>
        <a href="DispatchServlet">Add more cars to your cart</a>
    </body>
</html>
