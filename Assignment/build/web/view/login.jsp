<%-- 
    Document   : login
    Created on : Jun 20, 2022, 2:04:44 PM
    Author     : Tuan Anh Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messages - Login</title>
        <script>
            function completePhoneNumber() {
                var x = document.getElementById("dial_codes");
                var code = x.options[x.selectedIndex].text;
                var form = document.getElementById("form");
                document.getElementById("country_code").setAttribute("value", code.split("-")[1].trim().replace("+", ""));
                form.style.display = "block";
            }
        </script>
    </head>
    <body>
        <h1>Sign in to your Messages</h1>
        <p>Please confirm your country</p>
        Country:
        <select id="dial_codes" onchange="completePhoneNumber()" on>
            <c:forEach var="c" items="${sessionScope.country_codes}">
                <option id="dial_code" value="${c.value}">${c.key} - ${c.value}</option>
            </c:forEach>
        </select>
        <br/>
        <form id="form" style="display:none" action="login" method="POST">
            Phone number: 
            <input type="text" name="phone_number" placeholder="Enter your phone number" required/>
            <input id="country_code" type="hidden" value="" name="country_code"/><br/>
            Password:<br/>
            <input type="password" name="password" placeholder="Enter your password" required/><br/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
