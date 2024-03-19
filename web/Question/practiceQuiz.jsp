<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Practice</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                /*            margin: 20px;*/
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

            li.Result {
                font-weight: bold;
                color: #007BFF; /* Blue color for "Result" label */
            }

            label.Correct {
                color: #008000; /* Green color for correct answers */
            }
            .butto{
                padding-bottom: 3%;

            }
            .alert-danger {
                color: #721c24;
                background-color: #f8d7da;
                border-color: #f5c6cb;
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: .25rem;
            }

        </style>
    </head>
    <body>
        <%@include file="/Home/header.jsp" %>
        <c:choose>
            <c:when test="${empty question}">
                <div class="alert alert-danger" role="alert">
                    No questions found.
                </div>
            </c:when>
            <c:otherwise>
                <h1> Quiz Practice</h1>
                <form action="AnsURL" method="">
                    <input type="hidden" name="SetId" value="${param.SetId}" />
                    <table style="border: 1px solid gray;">
                        <c:forEach items="${question}" var="ques" varStatus="status">
                            <tr style="border: 1px solid gray;">
                                <th>Q${status.index + 1}</br>
                                    (Mark 1.00)</th>
                                <th>Question: ${ques.getContent()}</th>
                            </tr>

                            <tr>
                                <td></td>
                                <td>
                                    <ul>
                                        <c:forEach items="${content[status.index]}" var="answer" varStatus="statu">
                                            <li>
                                                <%-- Truy cập vào QuesAnswers bằng chỉ mục statu.index để lấy NormalQuestionAnswer --%>
                                                <%-- Sử dụng nội dung của NormalQuestionAnswer làm giá trị cho radio button --%>
                                                <input type="radio" name="question${status.index + 1}" value="${answer.getContent()}" />
                                                <label for="question-${status.index + 1}-answers-${statu.index}">
                                                    ${(statu.index + 1)}. ${answer.getContent()}
                                                </label>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="butto">
                        <h3>All done! Are you ready to submit your test?</h3></br>
                        <button type="submit" style="font-size: 20px; background-color: #1C1E53; color: white; height: 50px;">Submit test</button>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>