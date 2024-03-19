
<%-- 
    Document   : addSet
    Created on : Jan 26, 2024, 9:40:52 AM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Question Set</title>
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
        <title>OnlineQuiz</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sliderStyle3.css" />
        <link rel="stylesheet" href="/CSS/home.css"/>

    </head>
    <body>
        <%@include file="/Home/header.jsp" %> 
        <div class="course-editor-title-bar">
            <h1 class="editor-default-title">
                Question Set
            </h1>
        </div>
        <div class="course-editor-title-bar">
            <a href="QuestionSetURL" >
                All question set
            </a>
        </div>
        <!-- form dien toan bo thong tin -->
        <form action="QuestionSetURL" method="post">           
            <input type="hidden" name="go" value="addNewSetDetails">
            <input type="hidden" name="setId" value="${param.setId}">
            <div>
                <label style="width: 100px" for="exam-name">Title</label>
                <input type="text" id="exam-name" name="title" value="${set.getTitle()}" required/>

                <label style="width: 100px" for="exam-name">Subject</label>
<!--                <input type="text" id="exam-name" name="subjectId" value="${set.getSubjectId()}" required/>-->
                <select name="subjectId">
                    <c:forEach var="entry" items="${subjectMap}">
                        <option value="${entry.key}">${entry.value}</option>
                    </c:forEach>
                </select>
                <label style="width: 100px" for="exam-name">Question Content</label>
                <div style="border:#ccc 1px solid; padding:10px">

                    <ul>
                        <c:forEach var="question" items="${questions}" varStatus="questionStatus">
                            <li style="border:#ccc 1px solid; padding:10px; margin: 10px">
                                <label for="exam-question">Question: ${questionStatus.count}</label>
                                <div class="question">
                                    <input type="text" id="exam-question" name="QuestionDetail${questionStatus.index}" value="${question.getContent()}"  required/>
                                    <a class="btn-delete" href="DeleteQuestionSetURL?go=deleteQuestion&setId=${param.setId}&quesId=${question.getQuesId()}"><i class="gg-trash"></i></a>
                                </div>
                                <input type="hidden" name="QuestionId${questionStatus.index}" value="${question.getQuesId()}"/>
                                <a href="NewAnswerURL?setId=${param.setId}&questionId=${question.getQuesId()}">Add new Answer</a>                             
                                <div style="margin: 10px">
                                    <ul style="border: 10px #000">
                                        <c:forEach var="answer" items="${questionAnswer.get(question.getQuesId())}" varStatus="answerStatus">
                                            <li style="margin: 10px">
                                                <label for="choice-description">${answerStatus.count}</label>
                                                <div class="choice">
                                                    <input type="text" id="choice-description" name="AnswerDetail${questionStatus.index}-${answerStatus.index}" value="${answer.getContent()}"  required/>
                                                    <a class="btn-delete" href="DeleteQuestionSetURL?go=deleteAnswer&setId=${param.setId}&answerId=${answer.getAnswerId()}"><i class="gg-trash"></i></a>
                                                </div>
                                                <input type="checkbox" name="IsTrueAnswer${questionStatus.index}-${answerStatus.index}" ${answer.isCorrect() ? "checked":""} value="true" id="choice-radio"+""/>
                                                <label>True answer</label>
                                                <input type="hidden" name="AnswerId${questionStatus.index}-${answerStatus.index}" value="${answer.getAnswerId()}"/>
                                            </li> 
                                        </c:forEach>
                                    </ul>
                                </div>
                            </li>    
                        </c:forEach>
                    </ul>
                    <a href="NewQuestionURL?setId=${param.setId}">Add new Question</a>
                </div>
            </div>
            <div class="action-container">
                <input type="submit" name="submit" value="Save" class="btn-save"/>
            </div>
        </form>
        <form action="QuestionSetURL" method="post">
            <div class="action-container">
                <input type="hidden" name="go" value="deleteSet">
                <input type="hidden" name="setId" value="${param.setId}">
                <input type="button" name="action" value="Delete" class="btn-del" onclick="showDeleteConfirmation(${param.setId})"/>
            </div>
        </form>
        <style>
            .question{
                display: flex;
                align-items: center;
            }
            .choice{
                display: flex;
                align-items: center;
            }
            .btn-delete{
                margin: 10px
            }
            body {
                background: #F4F6FC;
            }

            .course-editor-main {
                padding: 2rem;
            }
            input[type=text], select {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            input[type=number], select {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            form{
                margin-left: 20px;
                margin-right: 20px;
            }
            form .action-container {
                margin-top: 2rem;
                display: flex;
                gap: 0.5rem;
            }

            element.style {
                border: #ccc 1px solid;
                padding: 10px;
            }
            .btn-save {
                background: #fcd980;
                border: 1px solid #fcd980;
                color: black;
                padding: 0.5rem 2rem;
                margin: 0.2rem;
                border-radius: 4px;
            }
            .btn-del {
                background: transparent;
                border: 1px solid red;
                color: red;
                padding: 0.5rem 2rem;
                margin: 0.2rem;
                border-radius: 4px;
                cursor: pointer;
            }

        </style>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
                    function showDeleteConfirmation(setId) {
                        Swal.fire({
                            title: 'Delete Question Set',
                            text: 'Are you sure?',
                            icon: 'warning',
                            showCancelButton: true,
                            confirmButtonText: 'Delete',
                            cancelButtonText: 'Cancel'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = "QuestionSetURL?go=deleteSet&setId=" + setId;
                            }
                        });
                    }
        </script>        <script src="/assets/js/base.js"></script>
        <link href='https://unpkg.com/css.gg@2.0.0/icons/css/trash.css' rel='stylesheet'>
    </body>
</html>
