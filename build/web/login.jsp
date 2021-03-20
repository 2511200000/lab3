<%-- 
    Document   : login
    Created on : Mar 9, 2021, 10:49:50 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/car.css">
        <title>Login</title>
        <script src="https://www.google.com/recaptcha/api.js"></script>

    </head>
    <body  <c:if test="${not empty requestScope.ERRORLGOIN}">
            onload="alertMessage()"
        </c:if> >
        <div class="imageHanaShop"></div>
        <div class="loginPage">
            <div class="loginForm">
                <h1>Login</h1>
                <form action="DispatchServlet" method="POST">
                    Username: <input id="txtTextfile" type="text" name="txtUsername" value="" /><br>
                    Password: <input id="txtTextfile" type="password" name="txtPassword" value="" /><br>
<!--                                <div class="g-recaptcha" id="captcha" data-sitekey="6LfU2n0aAAAAACz1AZNl3ZQtKnS0o87v_3SldRA1" ></div><br>-->
                    <div  class="btLogin">
                        <input id="buttonLogin" type="submit" value="Login" name="btAction" />
                        <input id="buttonLoginReset" type="reset" value="Reset" /><br>
                    </div>


                    <a href="createAccount.jsp">Create New Account</a>
                    <br/>
                </form>

            </div>
        </div>
         <div class="imageHanaShopBottom"></div>
        <script>
            function alertMessage() {
                alert("${requestScope.ERRORLGOIN}");
            }


        </script>

    </body>
</html>
