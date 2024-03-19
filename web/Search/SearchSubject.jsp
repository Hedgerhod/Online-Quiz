<%-- 
    Document   : SearchSubject
    Created on : Jan 25, 2024, 10:48:27 AM
    Author     : Asus
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Quizlet FPT</title>
        <%@include file="/Home/headCommon.jsp" %> 
    </head>
    <body>
        <%@include file="/Search/header.jsp" %> 
        <%@include file="/Search/SubjectFinder.jsp" %>
        <%@include file="/Home/footer.jsp" %>
    </body>
</html>
