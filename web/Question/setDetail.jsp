<%-- 
    Document   : setDetail
    Created on : Jan 26, 2024, 6:17:02 AM
    Author     : hieul
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    li {
        list-style-type: none;
    }

</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Quizlet FPT</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>

    </head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            display: flex;
            text-align: center;
            text-align: center;
        }

        h1 {
            color: #333;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        li {
            margin-bottom: 5px;
            color: #333; /* Default color for list items */
        }

        li.Result {
            font-weight: bold;
            color: #007BFF; /* Blue color for "Result" label */
        }

        li.Correct {
            color: #008000; /* Green color for correct answers */
        }
        .action-container {
            margin-top: 2rem;
            display: flex;
            gap: 0.5rem;
            margin-left: auto;
            margin-right: auto
        }
        .btn-import {
            background: #fcd980;
            border: 1px solid #fcd980;
            color: black;
            padding: 0.5rem 2rem;
            margin: 0.2rem;
            border-radius: 4px;
        }
        .btn-add {
            background: #fcd980;
            border: 1px solid #fcd980;
            color: black;
            padding: 0.5rem 2rem;
            margin: 0.2rem;
            border-radius: 4px;
        }
        .Popup {
            position: relative;
            text-align: center;
            width: 100%;
        }
        .behind {
            width: 100%;
            height: 100%;
            position: fixed;
            top: 0;
            left: 0;
            background-color: rgba(18, 18, 18, 0.7);
            z-index: 9;
            display: none;
        }
        .formPopup {
            display: none;
            position: fixed;
            left: 50%;
            top: 10%;
            transform: translate(-50%, 10%);
            z-index: 10;
            border-radius: 10px;
        }
        .formContainer {
            width: 500px;
            height: 300px;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            position: relative;
        }
        .enroll h1 {
            margin: 0;
        }

        .enroll button,.formContainer button {
            background-color: #2a73cc;
            border: none;
            color: white;
            padding: 10px 24px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-top: 10px;
        </style>
    </head>
    <body>
        <%@include file="/Home/header.jsp" %> 
        <!-- Your existing HTML content -->
        <c:if test="${acc != null}"> 
            <div class="head" style="display: flex;
                 justify-content: center;
                 align-items: center;">
                <c:if test="${!userSetExists}">    
                    <div class="enroll" style="width: 800px;
                         display: flex;">
                        <h1>${name.getTitle()}</h1>
                        <button style="margin-left: auto;" onclick="openForm()">Save</button>
                    </div>
                </c:if>
                <c:if test="${userSetExists}">    
                    <div class="enroll" style="width: 800px;
                         display: flex;">
                        <h1>${name.getTitle()}</h1>
                        <button style="margin-left: auto;
                                background-color: #cccccc" disabled>Saved</button>
                        </div>
                    </c:if>
                </div>
            </c:if>
            <div class="action-container">
                <c:if test="${not empty param.SetId}">
                    <p><a href="FlashCardURL?go=flashCard&SetId=${param.SetId}" style="color: #007BFF;
                text-decoration: none;
                font-weight: bold;" class="btn-import">FLASHCARD</a></p>
                <p><a href="TestURL?go=listQuestion&SetId=${param.SetId}" style="color: #007BFF;
                text-decoration: none;
                font-weight: bold;" class="btn-add" >TEST</a></p>
            </c:if>
        </div>
        <!--Popup-->
        <div class="Popup">
            <div class="behind" id="popupBehind" onclick="closeForm()"></div>
            <div class="formPopup" id="popupForm"> 
                <form action="MyEnrollURL?go=enroll&SetId=${SetId}" method="post" class="formContainer" onsubmit="return validateForm()" >
                    <h1 style="text-align: left;
                        font-weight: bold">${name.getTitle()}</h1>
                        <h3 style="text-align: left;
                        margin-top: 20px ">${subject.getSubjectName()} (${subject.getSubjectCode()})</h3>
                        <input type="hidden" name="go" value="addClass"> 
                        <ul style="display: flex;
                        margin-top: 20px">
                            <c:forEach var="i" begin="1" end="${name.getSetVote()}">
                                <li>
                                    <img style="vertical-align: middle;
                                    width: 30px;
                                    float: right;
                                    margin : 5px" src="${pageContext.request.contextPath}/Class/images/star.png" alt="Star">
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="under" style=" display: flex;
                        margin-top: 50px" >
                            <h4>Create by: ${teacher.getTeacherName()}</h4>
                            <button style="margin: 0 0 0 auto; ">Enroll</button>
                    </div>
                </form>
            </div>
        </div>
        <!--End Popup-->
        <table border="1">
            <tr>
                <th>Question</th>
                <th>Content</th>
            </tr>
            <c:forEach items="${question}" var="ques" varStatus="status">
                <tr>
                    <td style="color: #FF5733;">${ques.getContent()}</td>
                    <td>
                        <ul>
                            <c:forEach items="${content[status.index]}" var="answer">
                                <li>${answer.getContent()}</li>
                                </c:forEach>
                            <li class="Result">Result</li>
                                <c:forEach items="${content[status.index]}" var="answer">
                                    <c:if test="${answer.isCorrect() == true}">
                                    <li class="Correct">${answer.getContent()}</li>
                                    </c:if>
                                </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
    <script>
        function openForm() {
            document.getElementById("popupForm").style.display = "block";
            document.getElementById("popupBehind").style.display = "block";
        }
        function closeForm() {
            document.getElementById("popupForm").style.display = "none";
            document.getElementById("popupBehind").style.display = "none";
        }
    </script>
</html>
