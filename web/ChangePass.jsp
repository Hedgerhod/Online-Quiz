<%-- 
    Document   : ChangePass
    Created on : Feb 23, 2024, 5:43:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entity.User" %>
<%@page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Change Password</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }
            .container {
                max-width: 400px;
                margin: 50px auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
                color: #333;
            }
            form {
                margin-top: 20px;
            }
            input[type="password"], input[type="submit"] {
                width: 100%;
                padding: 10px;
                margin-top: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            p.error-message {
                color: red;
                font-size: 14px;
                margin-bottom: 10px;
            }
            p.success-message {
                color: green;
                font-size: 14px;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Change Password</h1>
            <%
                String us = (String) session.getAttribute("username");
            %>
            <form action="change" method="POST">
                <input type="hidden" name="username" value="<%=us%>">
                Old Password <input type="password" name="opass"><br>
                <% 
                String op = (String)request.getAttribute("op");
                if(op != null) {
                    out.println("<p class='error-message'>" + op + "</p>");
                }
                %>
                New Password <input type="password" name="npass"><br>
                <% 
                String np = (String)request.getAttribute("np");
                if(np != null) {
                    out.println("<p class='error-message'>" + np + "</p>");
                }
                %>
                Confirm New Password <input type="password" name="cnpass"><br>
                <% 
                String rp = (String)request.getAttribute("rp");
                if(rp != null) {
                    out.println("<p class='error-message'>" + rp + "</p>");
                }
                %>
                <input type="submit" value="Change Password">
            </form>
            <% 
            String errorMessage = (String)request.getAttribute("errorMessage");
            if(errorMessage != null) {
                out.println("<p class='error-message'>" + errorMessage + "</p>");
            }
            String successMessage = (String)request.getAttribute("successMessage");
            if(successMessage != null) {
                out.println("<p class='success-message'>" + successMessage + "</p>");
            }
            %>
        </div>
    </body>
</html>
