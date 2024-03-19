<%-- 
    Document   : exam
    Created on : Mar 11, 2024, 5:01:37 PM
    Author     : hieul
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${exam.getTitle()}</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/exam.css"/>
    </head>
    <body>
        <%@include file="/Home/headCommon.jsp" %>
        <%@include file="/Home/header.jsp" %>
        <c:set var="limit" value="${limit}" />
        <c:set var="time" value="${time}" />
        <div class="course-editor-frame">
            <div class="course-editor-title-bar">          
                <h1>Exam</h1>
            </div>
            <main in class="course-editor-main">
                <div class="EXAM-INF">
                    <div>
                        <h2 class="editor-default-title">
                            ${exam.getTitle()}
                        </h2>
                        <p>Time Limit: <span>${exam.getTimer() / 60} minutes</span></p>
                    </div>
                    <div class="content">
                        <c:choose>
                            <c:when test="${time < limit}">
                                <div class="link">
                                    <a href="ExamClassDispatchURL?examId=${exam.getExamId()}&status=1">
                                        <c:if test="${takeExams == null}">
                                            Attempt Exam 
                                        </c:if>
                                        <c:if test="${takeExams != null}">
                                            Re-Attempt Exam 
                                        </c:if>    
                                    </a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="link">
                                    <p>Time limit reached.</p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <hr>
                <div class="EXAM-ATTEMPT">
                    <div>
                        <c:if test="${bestAttempt != null}">
                            <c:set var="mark" value="${bestAttempt.getScore()}"></c:set>                      
                        </c:if>
                        <h3>Grade</h3>
                        <p></p>
                    </div>
                    <div class="attempt">
                        <div>
                            Your grade
                            <c:if test="${bestAttempt == null}">
                                <p>_</p>
                            </c:if>
                            <c:if test="${bestAttempt != null}">
                                <p style="color: #55ff00">${mark}</p>
                            </c:if>
                        </div>
                        <div>
                            <c:if test="${takeExams != null}">
                                <div class="link">
                                    <a href="ExamClassDispatchURL?takeExamId=${bestAttempt.getTakeExamId()}&status=2">
                                        View Feedback
                                    </a>
                                </div>
                                <p class="small">We keep your highest score</p>
                            </c:if>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="EXAM-PREV">
                    <c:if test="${takeExams != null}">
                        <h2>Previous Attempt</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th class="cel-1">Attempt</th>
                                    <th class="cel-2">State</th>
                                    <th class="cel-3">Grade</th>
                                    <th class="cel-4">Marks out of ${questionCount}</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${takeExams}" var="paper" varStatus="paperID">
                                    <tr class="clickable-row" data-href="<c:choose><c:when test='${paper.getStatus() == 1}'>ExamClassDispatchURL?examId=${exam.getExamId()}&status=1</c:when><c:otherwise>ExamClassDispatchURL?takeExamId=${paper.getTakeExamId()}&status=2</c:otherwise></c:choose>">
                                        <td>${paperID.index+1}</td>
                                        <td>
                                            <p>${paper.getStatus() == 2 ? "Finished" : "In Progress"}</p>
                                            <c:if test="${paper.getStatus()== 2}">
                                                <p>Submitted ${paper.getEndDate()}</p>
                                            </c:if>
                                        </td>
                                        <td>${Math.ceil((paper.getScore()/questionCount)*100)}%</td>
                                        <td>
                                            <p>${paper.getScore()}/${questionCount}</p>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </main>
        </div>
        <script src="${pageContext.request.contextPath}/assets/js/base.js"></script>
        <script>
            // Wait for the document to be ready
            document.addEventListener("DOMContentLoaded", function () {
                // Get all elements with class "clickable-row"
                const clickableRows = document.querySelectorAll(".clickable-row");

                // Add click event listeners to each clickable row
                clickableRows.forEach(row => {
                    row.addEventListener("click", function () {
                        // Get the URL from the "data-href" attribute of the clicked row
                        const url = row.dataset.href;

                        // Redirect to the specified URL
                        window.location.href = url;
                    });
                });
            });
        </script>
    </body>
</html>

