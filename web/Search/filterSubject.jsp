<%-- 
    Document   : filterQuiz
    Created on : Jan 20, 2024, 5:03:48 PM
    Author     : Asus
--%>

<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="filter-dialog">
    <i class="fa-solid fa-filter"></i> <strong>Filters </strong>
    <p class="filter-group-title"></p>
    <ul class="filter-group">
        <li>
            <input class="d-none" type="radio" name="category" value="-1" id="cat-choice--1" onclick="selectFilter(event)" ${empty param.category || param.category == -1 ? "checked" : ""}/> 
            <label for="cat-choice--1">All</label>
        </li>
        <li>
            <a href="Search/SearchSubject.jsp">Search</a>

        </li>


        <c:forEach var="item" items="${SubNav}">
            <li>
                <%--<input class="d-none" type="radio" name="category" value="${item.id}" id="cat-choice-${item.id}" onclick="selectFilter(event)" ${param.category == item.id ? "checked" : ""}/> --%>
                <label for="cat-choice-">${item.getSubjectCode()}</label> 

            </li>
        </c:forEach>
    </ul>


</form>
