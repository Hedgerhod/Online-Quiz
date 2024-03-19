<%-- 
    Document   : reviewExam
    Created on : Mar 11, 2024, 5:01:32 PM
    Author     : hieul
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<%@page import="Entity.QuestionExam" %>
<%@page import="Entity.QuestionExamAnswer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/search.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adminCourseNav.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/courseEditorBase.css"/>
        <script src="https://kit.fontawesome.com/4008f7ead4.js" crossorigin="anonymous"></script>
        <script src="/assets/js/base.js"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;500&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sliderStyle3.css" />
        <link rel="stylesheet" href="/CSS/home.css"/>

        <title>${exam.getTitle()}</title>
        <link rel="stylesheet" href="/assets/css/attempt.css"/>
        <%@include file="/Home/headCommon.jsp" %>
        <script>
            // Assuming you have the total number of questions stored in a variable called `totalQuestions`
            var questionList = document.getElementById('question-list');

            // Generate the navigation buttons
            for (var i = 1; i <= totalQuestions; i++) {
                var button = document.createElement('button');
                button.textContent = i;
                button.setAttribute('data-question', i);
                button.addEventListener('click', navigateToQuestion);
                questionList.appendChild(document.createElement('li').appendChild(button));
            }

            // Function to handle navigation when a button is clicked   
            function navigateToQuestion(event) {
                var questionNumber = event.target.getAttribute('data-question');

                // Your logic to display the respective question goes here
                // For example, you can show/hide question sections based on the selected question number

                // Remove 'active' class from all buttons
                var buttons = document.querySelectorAll('.question-nav button');
                buttons.forEach(function (button) {
                    button.classList.remove('active');
                });

                // Add 'active' class to the clicked button
                event.target.classList.add('active');
            }

        </script>
        <link rel="stylesheet" href="/assets/css/review.css"/>
    </head>
    <body>
        <%@include file="/exam/headerExam.jsp" %>
        <div class="content">
            <c:set var="questions" value="${requestScope.questions}" />
            <c:set var="answers" value="${requestScope.answers}" />
            <c:set var="userAnswers" value="${requestScope.userAnswers}" />
            <c:forEach items="${questions}" var="question" varStatus="questionStatus">
                <c:set var="q" value="${question}" />
                <c:if test="${not empty userAnswers[q]}">
                    <!-- Câu hỏi này có trong userAnswers map -->
                    <c:set var="userAnswer" value="${userAnswers[q]}" />
                </c:if>
                <div class="question" id="question${questionStatus.index+1}">
                    <div class="question-inf">
                        Question <h2>${questionStatus.index+1}</h2>
                        <c:choose>
                            <c:when test="${userAnswer == null}">
                                <p>Not answered</p>
                            </c:when>
                            <c:otherwise>
                                <p>Complete</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="question-detail">
                        <div class="question-content">
                            ${q.getContent()}
                        </div>
                        <hr>
                        <div class="choices">
                            <c:set var="answerList" value="${answers[q.getQuesId()]}" />
                            <c:set var="answerlog" value="Please review the class's materials for better understanding" />
                            <c:set var="flag" value="false" />

                            <c:forEach items="${answerList}" var="answer">
                                <c:if test="${userAnswer.getContent() eq answer.getContent()}">
                                    <c:if test="${answer.isCorrect()}">
                                        <c:set var="answerlog" value="The answer ${answer.getContent()} is correct." />
                                        <c:set var="flag" value="true" />
                                    </c:if>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${answerList}" var="answer">
                                <c:choose>
                                    <c:when test="${userAnswer != null && userAnswer.getContent() eq answer.getContent()}">
                                        <div>
                                            <input type="radio" checked>
                                            <label>
                                                ${answer.getContent()}
                                            </label>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div>
                                            <input type="radio">
                                            <label>
                                                ${answer.getContent()}
                                            </label>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${flag eq 'false'}">
                                    <div class="false-answer">
                                        <i class="fa-regular fa-circle-xmark" style="color:#ff8080"></i> Incorrect
                                        <p>${answerlog}</p>
                                    </div>
                                </c:when>
                                <c:when test="${flag eq 'true'}">
                                    <div class="true-answer">
                                        <i class="fa-regular fa-circle-check" style="color: #55ff00"></i> Correct
                                        <p>${answerlog}</p>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>

            </c:forEach>


            <div class="question-nav">
                <h3>Exam Navigation</h3>
                Total Question: <p id="questionCount">${questionCount}</p>
                <ul>
                    <c:forEach var="i" begin="1" end="${questionCount}">
                        <li><button id="${i}">${i}</button></li>
                        </c:forEach>
                </ul>
            </div>
        </div>
        <script>
            var totalQuestions = document.getElementById('questionCount').textContent;
            for (var i = 1; i <= totalQuestions; i++) {
                var button = document.getElementById(i.toString());
                var questionSection = document.getElementById('question' + i);
                questionSection.addEventListener('click', handleDivClick);
                button.setAttribute('data-question', i);
                button.addEventListener('click', navigateToQuestion);
                if (i === 1)
                    button.classList.add('active');
            }
            function handleDivClick(event) {
                var clickedElement = event.target;
                if (clickedElement.tagName === 'INPUT' && clickedElement.type === 'radio') {
                    var radioValue = clickedElement.id;
                    var parts = radioValue.split("_");
                    var button = document.getElementById(parts[0].toString());
                    button.classList.add('selected');
                }
            }
            // Function to handle navigation when a button is clicked
            function navigateToQuestion(event) {
                var questionNumber = event.target.getAttribute('data-question');
                var questionSection = document.getElementById('question' + questionNumber);
                if (questionSection) {
                    questionSection.scrollIntoView({block: "center", behavior: 'smooth'});
                }
                var buttons = document.querySelectorAll('.question-nav button');
                buttons.forEach(function (button) {
                    button.classList.remove('active');
                });
                event.target.classList.add('active');
            }
            var radioButtons = document.querySelectorAll("input[type='radio']");
            for (var i = 0; i < radioButtons.length; i++) {
                radioButtons[i].disabled = true;
            }
        </script>
    </body>
</html>
