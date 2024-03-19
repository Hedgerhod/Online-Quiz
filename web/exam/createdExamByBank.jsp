<%-- 
    Document   : createdExamByBank
    Created on : Mar 12, 2024, 11:23:41 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/createQuiz.css">
        <title>JSP Page</title>\
        <style>
            .time-input-container {
                display: flex;
                align-items: center;
            }

            .time-input-container label {
                margin-right: 0.5em;
            }

            .time-input-container input {
                width: 6em;
            }

            .time-input-container span {
                margin-left: 0.2em;
                margin-right: 0.2em;
            }
        </style>
    </head>
    <body>
        <h1 class="mt-3">Create Exam By QuestionSet</h1>
        <form id="quizForm" action="ExamByQuestionSetURL" method="post">
            <input type="hidden" name="go" value="createdExam">
            <label style="width: 100px" for="exam-title">Title</label>
            <input type="text" id="exam-title" name="ExamTitle" value="Exam" required/>
            <label  style="width: 100px" for="exam-summary">Summary</label>
            <input type="text" id="exam-summary" name="ExamSummary" value="" />
            <label  style="width: 100px" for="exam-timer">Timer</label>

            <div class="time-input-container">
                <label for="hour">Hour: </label>
                <input type="number" id="hour" name="hour" min="0" max="23" step="1" value="0">

                <label for="minute">Minute: </label>
                <input type="number" id="minute" name="minute" min="0" max="59" step="1" value="30">

                <label for="second">Second: </label>
                <input type="number" id="second" name="second" min="0" max="59" step="1" value="0">

            </div>
            <label style="width: 100px" for="exam-name">Start Date</label>
            <input type="datetime" id="exam-start" name="ExamStart" value="" placeholder="yyyy/mm/dd" required/>
            <label  style="width: 100px" for="exam-duration">End Date</label>
            <input type="datetime" id="exam-end" name="ExamEnd" value="" placeholder="yyyy/mm/dd"  required/><br/>
            <label style="width: 100px" for="exam-score">Score</label>
            <input type="number" id="exam-score" name="ExamScore" value="100" required/>                        
            <label style="width: 100px" for="exam-taking-time">Taking Timers</label>
            <input type="number" id="exam-taking-time" name="ExamTakingTimers" value="1" min="1" required/>
            <label  style="width: 100px" for="exam-permission">Permission</label>
            <select name="permission " class="form-select">
                <option value="false" >unavailable</option>
                <option value="true" >available</option>
            </select>
            <hr>
            <div class="container">
                <h2>Questions Set</h2>

                <div class="input-group">
                    <input style="border-radius: 10px " id="questionSearch" value="" name="question" class="form-control form-control-lg" type="text" placeholder="Search Question ..." aria-describedby="button-addon2" autofocus="">
                    </button>
                </div>
                <div style="height: 200px; overflow-y: auto;">
                    <table class="table table-hover" id="myTable">

                        <thead>
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">Question</th>
                                <th scope="col">Chose</th>
                            </tr>
                        </thead>

                        <tbody>

                            <c:forEach var="questionSet" items="${QuestionSetList}" varStatus="listStatus">
                                <tr>
                                    <td>${listStatus.count}</td>
                                    <td class="questions">${questionSet.getTitle()}</td>
                                    <td>
                                        <input type="checkbox" name="selectedQuetionSet" value="${questionSet.getSetId()}">
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                </div>
                </table>
            </div>
            <button type="submit">Create Exam</button>
        </form>
        <script>
            function filterQuestions() {
                var input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("questionSearch");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable"); // Thay đổi 'myTable' bằng ID thực tế của bảng của bạn
                tr = table.getElementsByTagName("tr");

                // Lặp qua tất cả các hàng trong bảng và ẩn những cái không khớp với từ khóa tìm kiếm
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByClassName("questions")[0];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }

// Gắn hàm filterQuestions() với sự kiện 'keyup' của input để lọc kết quả khi người dùng nhập từ khóa
            document.getElementById("questionSearch").addEventListener("keyup", filterQuestions);
        </script>
    </body>


</html>
