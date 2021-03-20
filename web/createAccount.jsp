<%-- 
    Document   : createAccount
    Created on : Mar 14, 2021, 10:51:07 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/car.css">
        <title>CreateAccount</title>
    </head>
    <body <c:if test="${not empty requestScope.CREATERR}">
            onload="alertMessage()"
        </c:if>
        >
        <div class="backgoundCreateAccount"></div>
        <div class="createPage">
            <div class="createForm">
                <form action="DispatchServlet" method="POST">
                    <h1>CreateAccount</h1>
                    Email:</b> <input id="txtTextfile" type="text" name="txtEmail" value="" /><br>
                    FullName: </b> <input id="txtTextfile" type="text" name="txtName" value="" /><br>
                    Password: </b> <input id="txtTextfile" type="password" name="txtPassword" value="" /><br>
                    Confirm </b> <input id="txtTextfile" type="password" name="txtComfirm" value="" /><br>
                    <div  class="buttonCreate">
                        <input id="buttonLogin" type="submit" value="CreateNewAccount" name="btAction" />
                        <input id="buttonLoginReset" type="reset" value="Reset" /><br>
                    </div>
                </form>
            </div>
        </div>
        <script>
            function alertMessage() {
                alert("${requestScope.CREATERR}");
            }
        </script>
    </body>
</html>
