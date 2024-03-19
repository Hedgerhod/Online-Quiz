<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Quizlet FPT</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <style>
            body {
                font-family: 'Arial', sans-serif;
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
                padding: 23px;
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

            .Result {
                padding-bottom: 3%;
            }

            li.Result {
                font-weight: bold;
                color: #007BFF; /* Blue color for "Result" label */
            }

            label.Correct {
                color: #008000; /* Green color for correct answers */
            }

            label.Incorrect {
                color: #FF0000; /* Red color for incorrect answers */
            }

            .label-container {
                display: flex;
                align-items: center; /* Căn chỉnh biểu tượng theo chiều dọc */
            }

            .fa-check, .fa-close {
                font-size: 48px;
                margin-right: 10px; /* Khoảng cách giữa biểu tượng và nội dung văn bản */
            }

            .fa-check {
                color: green;
            }

            .fa-close {
                color: red;
            }
        </style>
    </head>
    <body>
        <%@include file="/Home/header.jsp" %>

        <h1> Quiz Practice</h1>

        <form action="TestURL" method="post">
            <input type="hidden" name="SetId" value="${param.SetId}">
            <table>
                <c:forEach items="${question}" var="ques" varStatus="status">
                    <tr>
                        <th>Q${status.index + 1}</br>(Mark 1.00)</th>
                        <th>Question: ${ques.getContent()}</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <ul>
                                <%-- Sử dụng status.index để truy cập vào QuesAnswers tương ứng --%>
                                <%-- Dùng một biến mới để lưu trữ danh sách các đáp án cho câu hỏi hiện tại --%>
                                <%-- Sử dụng chỉ mục của vòng lặp bên trong để truy cập vào QuesAnswers --%>
                                <c:set var="currentAnswers" value="${content[status.index]}" />
                                <c:forEach items="${currentAnswers}" var="answer" varStatus="statu">
                                    <li style="display: flex;">
                                        <input type="radio" name="question${status.index + 1}" value="${answer.getContent()}" 
                                               ${not empty selectedAnswers[status.index] && selectedAnswers[status.index] eq answer.getContent() ? 'checked' : ''} 
                                               ${not empty selectedAnswers[status.index] ? 'disabled' : ''} />

                                        <div class="label-container">
                                            <label for="question-${status.index + 1}-answers-${statu.index}"
                                                   class="${not empty selectedAnswers[status.index] && answer.getContent() eq selectedAnswers[status.index] ? (answer.isCorrect() ? 'Correct' : 'Incorrect') : ''}">
                                                ${(statu.index + 1)}. ${answer.getContent()}
                                            </label>
                                        </div>
                                    </li>
                                </c:forEach>

                                <li class="Result">Result</li>
                                    <c:forEach items="${content[status.index]}" var="answer">
                                        <c:if test="${answer.isCorrect() == true}">
                                        <li class="Correct" style="font-weight: bold;">The correct answer: ${answer.getContent()}</li>
                                        </c:if>
                                    </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </form>

        <div class="Result">
            <h1>Your Quiz Result</h1>
            <c:if test="${not empty sessionScope.correctCount}">
                <p>Your score: ${sessionScope.correctCount}/${sessionScope.total}</p>
            </c:if>
        </div>   
    </body>
</html>
