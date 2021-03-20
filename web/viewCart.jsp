<%-- 
    Document   : viewCart
    Created on : Mar 16, 2021, 5:11:27 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/car.css">
        <title>ViewCart</title>
    </head>
    <body>
        <div class="backgoundCreateItem"></div>
        <c:set var="cart" value="${sessionScope.CUSTCART}" />
        <c:if test="${not empty cart.items}">
            <div class="displayAdmin">
                <table border="1" class="tableAdmin">
                    <thead>
                        <tr>
                            <th class="columnAdmin">No.</th>
                            <th class="columnAdmin">CarName</th>
                            <th class="columnAdmin">Quantity</th>
                            <th class="columnAdmin">price</th>
                            <th class="columnAdmin">total</th>
                            <th class="columnAdmin">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="DispatchServlet">
                        <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                            <tr>
                                <td class="rowAdmin">
                                    ${counter.count}
                                </td>
                                <td class="rowAdmin">
                                    ${entry.key.carName}
                                    <input type="hidden" name="txtItemID" value="${entry.key.carID}" />
                                </td>
                                <td class="rowAdmin">
                                    <span class="increasequantity" onclick="decrease('${entry.key.carID}')" style="cursor: pointer;">-</span>
                                    <span  class="quantity" id="quantity${entry.key.carID}">${entry.value}</span>
                                    <input type="hidden" name="quantity" value="${entry.value}" id="quan${entry.key.carID}"/>
                                    <span class="creasequantity" onclick="increase('${entry.key.carID}')" style="cursor: pointer;">+</span>
                                </td>
                                <td class="rowAdmin">
                                    <span id="price${entry.key.carID}">${entry.key.price}</span>
                                    <input id="price" type="hidden" name="price" value="${entry.key.price}" />
                                </td>
                                <td class="rowAdmin">
                                    <span id="total${entry.key.carID}">${entry.key.price * entry.value}</span>
                                    <input type="hidden" name="total" value="${entry.key.price * entry.value}" id="totalAll${entry.key.carID}" />
                                </td>

                                <td class="rowAdmin">
                                    <input type="checkbox" name="chkItem" value="${entry.key.carID}" />
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="2" class="rowAdmin">
                                <a href="DispatchServlet">Add more books to your cart</a>
                            </td>
                            <td colspan="3">
                                <c:set var="tong" value="0"/>
                                <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                                    <c:set var="tong" value="${tong + (entry.key.price * entry.value)}"/>
                                </c:forEach>
                                TotalAll: <span id="totalBill">${tong}</span>
                                <input type="hidden" name="totalAll" value="${tong}" />


                            </td>
                            <td class="rowAdmin"> 
                                <input Onclick="return ConfirmDelete();" type="submit"
                                       value="RemoveSelectedCars" name="btAction" />
                            </td>
                        </tr>

                        <input type="submit" value="Confirm" name="btAction" />

                    </form>
                    </tbody>
                </table>
            </div>

        </c:if>
        <c:if test="${empty cart.items}">
            <h2>No cart exists</h2>
            <a href="DispatchServlet">Add more cars to your cart</a>
        </c:if>
        <script>
            function increase(id) {
                var x = document.getElementById("quantity" + id).innerHTML;
                x++;

                document.getElementById("quantity" + id).innerHTML = x;
                var y = document.getElementById("price" + id).innerHTML;

                var z = x * y;

                document.getElementById("total" + id).innerHTML = z;
                document.getElementById("quan" + id).value = x;
                document.getElementById("totalAll" + id).value = z;

                var totalBill = document.getElementById("totalBill").innerHTML;
                totalBill = parseFloat(totalBill);
                y = parseFloat(y);
                var totalAll = totalBill + y;
                document.getElementById("totalBill").innerHTML = totalAll;
                console.log(z);
            }
            function decrease(id) {
                var x = document.getElementById("quantity" + id).innerHTML;
                x--;
                if (x < 1) {
                    return;
                }
                document.getElementById("quantity" + id).innerHTML = x;
                var y = document.getElementById("price" + id).innerHTML;

                var z = x * y;

                document.getElementById("total" + id).innerHTML = z;
                document.getElementById("quan" + id).value = x;
                document.getElementById("totalAll" + id).value = z;
                var totalBill = document.getElementById("totalBill").innerHTML;
                totalBill = parseFloat(totalBill);
                y = parseFloat(y);
                var totalAll = totalBill - y;
                document.getElementById("totalBill").innerHTML = totalAll;

                console.log(z);
            }
        </script>
        <script>
            function ConfirmDelete() {
                var x = confirm("Are you sure you want to delete?");
                if (x)
                    return true;
                else
                    return false;
            }
        </script>
    </body>
</html>
