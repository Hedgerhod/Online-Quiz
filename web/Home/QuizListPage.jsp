<%@page pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="utils.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .d-flex.justify-content-center {
        margin-top: 20px;
    }

    .pagination {
        display: flex;
        list-style: none;
        padding: 0;
    }

    .page-item {
        margin: 0 5px;
    }

    .page-link {
        display: block;
        padding: 10px 15px;
        background-color: #f8f9fa;
        border: 1px solid #dee2e6;
        color: #495057;
        text-decoration: none;
        border-radius: 5px;
    }

    .page-item.active .page-link {
        background-color: #007bff;
        color: #fff;
        border: 1px solid #007bff;
    }
</style>
<form class="course-pagination">
    <nav aria-label="Page navigation example" class="d-flex justify-content-center">
        <ul class="pagination">
            <li class="page-item ">
                <a class="page-link" href="${requestScope.url}page=${page-1}">Previous</a>
            </li>
            <c:forEach begin="1" end="${totalPage}" var="i">
                <li class="page-item ${i == page ? 'active' : ''}">
                    <a class="page-link" href="${requestScope.url}page=${i}">${i}</a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href="${requestScope.url}page=${page+1}">Next</a>
            </li>
        </ul>
    </nav>
</form>
