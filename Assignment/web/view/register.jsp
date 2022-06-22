<%-- 
    Document   : register.jsp
    Created on : Jun 20, 2022, 5:47:23 PM
    Author     : Tuan Anh Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messages - Register</title>
    </head>
    <body>
        <h1>Register for a free account</h1>
        <p>Please enter required informations:</p>
        <form action="register" method="POST">
            First name:<br/>
            <input type="text" name="first_name"/><br/>
            Last name:<br/>
            <input type="text" name="last_name"/><br/>
            Country:<br/>
            <select name="country_code" onchange="completePhoneNumber()">
                <c:forEach var="c" items="${requestScope.country_codes}">
                    <option value="${c.value}">${c.key} ${c.value}</option>
                </c:forEach>
            </select>
            <br/>
            Phone number:
            <input type="text" name="phone_number" required/><br/>
            Password:
            <input type="password" name="password" required/><br/>
            <input type="submit" value="Signup"/>
        </form>
    </body>
</html>
