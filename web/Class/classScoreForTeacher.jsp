<%-- 
    Document   : classScoreForTeacher
    Created on : Feb 27, 2024, 4:10:53 PM
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
                display: flex;
            }
            .student-list p {
                margin-left: 20px;
                margin: 30px;
            }
            .student-list p:hover{
                opacity: 0.7;
            }
            .export-button {
                background-color: #007bff;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
            }

            .export-button:hover {
                color: white;
                background-color: #2a73cc;
                text-decoration: none;
            }
        </style>
    </head>

    <body class="sub_page">
        <!-- Header sesion-->
        <%@include file="/Home/header.jsp" %> 
        <!-- End Header sesion-->       
        <div class="select_class">

            <a href="ClassExamListURL" target="_self" >Go Back </a>


            <a href="" target="_self" class="current-page">Detail</a>

        </div>
        <div class="content">
            <h1>Score Board<span style="margin-left: auto; font-size: 20px" class="classmates-count">${StudentList.size()} Students</span></h1>           
            <div class="student-list">
                <c:if test="${not empty StudentList}">
                    <c:forEach var="student" items="${StudentList}">
                        <div class="List" style="display: flex; border: #888 solid 0.5px">
                            <div class="col-2"> 
                                <div class="box_student"> 
                                    <p style="margin-top: 35px"><strong>${student.username}</strong></p>
                                </div>
                            </div>
                            <c:set var="isEmpty" value="true" />
                            <c:forEach var="score" items="${ScoreList}">
                                <c:if test="${score.studentAccountId == student.accountId}">                                 
                                    <p class="col-1 " style="margin-top: 35px">Score: ${score.score}</p>
                                    <p class="col-2" style="margin-top: 35px">Status: ${score.status} </p>
                                    <p class="col-3" style="margin-top: 35px">From ${score.getStartDate().substring(0, 10)} To ${score.getEndDate().substring(0, 10)}</p>
                                    <div class="col-2" style="margin-top: 30px">
                                        <a href="" class="btn btn-primary">Detail</a>
                                    </div>  
                                    <c:set var="isEmpty" value="false" />
                                </c:if>                                     
                            </c:forEach>
                            <c:if test="${isEmpty}">                                 
                                <p class="col-8" style="margin-top: 35px;text-align: center; color: red">Haven't taken the Exam yet</p>
                            </c:if> 
                        </div>

                    </c:forEach>
                    <a class="export-button" href="ExportExcel" target="_blank">Export to Excel</a>
                </c:if>
            </div>
        </div>

        <!-- footer section -->

        <!-- footer section -->
    </body>
    <script>
    </script>
</html>


