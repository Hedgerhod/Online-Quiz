<%-- 
    Document   : classList
    Created on : Jan 20, 2024, 1:24:13 PM
    Author     : phamg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Class" %>
<%@ page import="Entity.QuestionSet" %>
<%@ page import="Entity.Student" %>
<%@ page import="Entity.Teacher" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Mobile M   etas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />                                  
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title> Class </title>
        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Class/css/bootstrap.css" />
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <link href="${pageContext.request.contextPath}/Class/css/mainStyle.css" rel="stylesheet" />
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f5f5f5;
                color: #000;
            }

            h1 {
                color: #6d9eff;
                margin-bottom: 10px;
                border-bottom: #6d9eff solid 1px;
                margin-top: 30px;
                margin-bottom: 20px;
                display: flex;
            }

            .content {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .teacher-info {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .teacher-info img {
                margin-left: auto;
            }
            p {
                margin: 10px;
            }
            .box_student{
                border-bottom: #333 solid 1px;
                display: flex;
            }
            .student-list p {
                margin-left: 20px;
                margin: 30px;
            }
            .student-list p:hover{
                opacity: 0.7;
            }
        </style>
    </head>

    <body class="sub_page">
        <!-- Header sesion-->
        <%@include file="/Home/header.jsp" %> 
        <!-- End Header sesion-->    
        <c:set var="accId" value="${sessionScope.acc.getAccountId()}" />
        <div class="select_class">

            <a href="ClassDetailURL?classId=${classId}" target="_self" >Practice</a>


            <c:choose>
                <c:when test="${accId == teacher.getAccountId()}">
                    <a href="ClassExamListURL" target="_self">Exam</a>
                </c:when>
                <c:otherwise>
                    <a href="ScoreListForStudentURL" target="_self">Exam</a>
                </c:otherwise>
            </c:choose>


            <a href="" target="_self" class="current-page">People</a>
        </div>
        <div class="content">
            <h1>Teacher</h1>
            <div class="student-list">
                <c:if test="${not empty teacher}">
                    <div class="teacher-info">
                        <p><strong>${teacher.getUsername()}</strong></p>
                        <p title="${teacher.getEmail()}" onclick="openGmailCompose('${teacher.getEmail()}')">
                            <img src="${pageContext.request.contextPath}/Class/images/gmail.png" width="30px" alt="alt"/>
                        </p>
                    </div>
                </c:if>
            </div>

            <h1>Classmates<span style="margin-left: auto; font-size: 20px" class="classmates-count">${StudentList.size()} Students</span></h1>           
            <div class="student-list">
                <c:if test="${not empty StudentList}">
                    <c:forEach var="student" items="${StudentList}">
                        <div class="box_student"> 
                            <p><img src="${pageContext.request.contextPath}/Class/images/student-card.png" width="30px" alt="alt"/></p>
                            <p style="margin-top: 35px"><strong>${student.getUsername()}</strong></p>
                            <p title="${student.getEmail()}" style="float: right; margin-left: auto;" onclick="openGmailCompose('${student.getEmail()}')">
                                <img src="${pageContext.request.contextPath}/Class/images/gmail.png" width="30px" alt="alt"/>
                            </p>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <!-- footer section -->

        <!-- footer section -->
    </body>
    <script>
        function openGmailCompose(email) {
            window.open('https://mail.google.com/mail/?view=cm&fs=1&to=' + email, '_blank');
        }

    </script>
</html>


