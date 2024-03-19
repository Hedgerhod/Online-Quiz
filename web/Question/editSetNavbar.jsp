<%-- 
    Document   : editSetNavbar
    Created on : Mar 13, 2024, 2:25:48 AM
    Author     : hieul
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<nav class="admin-course-nav-container">
    <div class="admin-course-nav">
        <div class="course-nav-header">
            <a href="QuestionSetURL" >
                All question sets
            </a>
        </div>

        <div class="hsep"></div>

        <div  class="course-nav-header"><p>Exams</p></div>
        <ul>
            <c:forEach items="${data}" var="content">
                <li class="nav-item">
                    <a href="EditQuestionSetURL?setId=${content.getSetId()}" class="nav-item-flex"> <i class="fa-regular fa-circle-play"></i>${content.getTitle()}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</nav>
