<%-- 
    Document   : QuizFinder
    Created on : Jan 20, 2024, 4:58:25 PM
    Author     : Asus
--%>

<%@page pageEncoding="UTF-8" %>
<main class="course-finder">
    <div class="search-container">
        <%@include file="searchBar.jsp" %>

    </div>

    <div class="filter-container">
        <%@include file="filterQuiz.jsp" %> 
    </div>

    <div class="list-container">
        <%@include file="QuizList.jsp" %>
    </div>

    <div class="pagi-container">
        <c:if test="${pageCount != 0}">
            <%@include file="QuizListPage.jsp" %>
        </c:if>
    </div>
</main>
