<%-- 
    Document   : main
    Created on : Jun 20, 2022, 9:17:51 PM
    Author     : Tuan Anh Tran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
    </head>
    <body>
        <form style="display: inline; border: 1px">
            <c:choose>
                <c:when test="${requestScope.contacts.size() == 0}">
                    <p>You have no contact. Would you like to add a new contact ?</p><br/>
                    <button onclick=""></button>
                </c:when>
            </c:choose>
        </form>
    </body>
</html>
