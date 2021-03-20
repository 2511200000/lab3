<%-- 
    Document   : vertify
    Created on : Mar 14, 2021, 11:44:35 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vertify</title>
    </head>
    <body <c:if test="${not empty requestScope.VERTIFY}">
            onload="alertMessage()"
        </c:if>
        >
        <form action="DispatchServlet" method="POST">
            <h2>We've sent a code for you to verify your email </h2>
            <input type="text" name="txtCode" value="" />
            <input type="submit" value="vertify" name="btAction" />
        </form>
        <script>
            function alertMessage() {
                alert("${requestScope.VERTIFY}");
            }
        </script>
    </body>
</html>
