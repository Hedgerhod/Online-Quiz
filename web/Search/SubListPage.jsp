<%-- 
    Document   : QuizListPage
    Created on : Jan 20, 2024, 5:05:37 PM
    Author     : Asus
--%>

<%@page pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="utils.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    /* Pagination container */
    .d-flex.justify-content-center {
        margin-top: 20px; /* Thêm khoảng cách từ trên xuống */
    }

    /* Pagination list */
    .pagination {
        display: flex;
        list-style: none;
        padding: 0;
    }

    /* Pagination item */
    .page-item {
        margin: 0 5px; /* Thêm khoảng cách giữa các trang */
    }

    /* Pagination link */
    .page-link {
        display: block;
        padding: 10px 15px;
        background-color: #f8f9fa; /* Màu nền */
        border: 1px solid #dee2e6; /* Viền */
        color: #495057; /* Màu chữ */
        text-decoration: none;
        border-radius: 5px; /* Đường viền cong */
    }

    /* Active page */
    .page-item.active .page-link {
        background-color: #007bff; /* Màu nền của trang đang active */
        color: #fff; /* Màu chữ của trang đang active */
        border: 1px solid #007bff; /* Viền của trang đang active */
    }

</style>
<form class="course-pagination">
    <nav aria-label="Page navigation example" class="d-flex justify-content-center">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="HomeController?page=${page-1}">Previous</a></li>
                <c:forEach begin="1" end="${totalPage}" var="i">
                <li class="page-item ${i == page?"active":""}"><a class="page-link" href="HomeController?page=${i}">${i}</a></li>
                </c:forEach>
            <li class="page-item"><a class="page-link" href="HomeController?page=${page+1}">Next</a></li>
        </ul>
    </nav>
</form>

