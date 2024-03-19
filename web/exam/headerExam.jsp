<%-- 
    Document   : headerExam
    Created on : Mar 12, 2024, 10:14:34 AM
    Author     : hieul
--%>
<%@page import="Utils.TimeUtils" %>
<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="header-container">
    <div class="navbar">
        <c:if test="${status==1}">
            <div class="course-title-container bold">
                <a href="ClassExamURL?examId=${exam.getExamId()}" class="back-btn"> <i class="fa-solid fa-arrow-left"></i> Back</a>
            </div>
            <div class="exam-inf">
                <h1>${exam.getTitle()}</h1>
                <p>Time Limit: <span>${exam.getTimer() / 60} mins</span></p>
            </div>
            <div class="nav-header">
                Time Left<p id="timer"></p>
            </div>
        </c:if>
        <c:if test="${status==2}">
            <div class="course-title-container bold">
                <a href="ClassExamURL?examId=${exam.getExamId()}" class="back-btn"> <i class="fa-solid fa-arrow-left"></i> Back</a>
            </div>
            <div class="exam-inf">
                <h1>${exam.getTitle()}</h1>
                <p>Time Limit: <span>${exam.getTimer() / 60} mins</span></p>
            </div>
            <div>

            </div>
            <div class="nav-header">
                <p>Marks ${takeExam.getScore()}/${questionCount}</p>
                <p>Grade ${Math.ceil((takeExam.getScore()/questionCount)*100)}%</p>
            </div>
            <div class="time-completion">
                <p>Started on <span id="startDate">${takeExam.getStartDate()}</span></p>
                <p>Completed on <span id="endDate">${takeExam.getEndDate()}</span></p>
            </div>
        </c:if>
    </div>
</div>
<script>
    function formatDate(dateString) {
        var options = {year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric'};
        return new Date(dateString).toLocaleDateString('en-US', options);
    }

    window.onload = function () {
        var startDateElement = document.getElementById('startDate');
        var endDateElement = document.getElementById('endDate');
        startDateElement.textContent = formatDate(startDateElement.textContent);
        endDateElement.textContent = formatDate(endDateElement.textContent);
    };
</script>
<style>
    .header-container{
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        background-color: #4257b4;
    }
    .exam-inf {
        margin-left: 40px;
    }
    .exam-inf h1{
        font-size: 14px;
    }
    .exam-inf p {
        font-size: 12px;
    }
    .nav-header p {
        font-size: 12px;
    }
    .time-completion p{
        font-size: 12px;
    }
</style>

