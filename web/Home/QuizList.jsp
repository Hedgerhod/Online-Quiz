<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="utils.*" %>
<%@ page import="Entity.QuestionSet" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Admin" %>
<%@page pageEncoding="UTF-8" %>

<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: sans-serif;
    }

    .main-container {
        padding: 30px;
    }

    .cards {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
    }

    .card {
        margin: 20px;
        padding: 20px;
        width: 300px;
        display: grid;
        height: 183px;
        border-radius: 10px;
        transition: all 0.2s;
        grid-template-rows: 20px 50px 1fr 50px;
        box-shadow: 0px 6px 10px rgba(74, 70, 74, 0.414);
    }

    .card:hover {
        transform: scale(1.01);
        box-shadow: 0px 6px 10px rgba(0, 0, 0, 0.4);
    }

    .readMore,
    .close,
    .card div {
        cursor: pointer;
        position: relative;
        text-decoration: none;
        color: rgba(255, 255, 255, 0.9);
    }

    .card div ul {
        margin-left:-39px;
        display: flex;
        list-style: none;
    }

    .card div ul li {
        margin: 0 0.3rem;
        font-size: 1.1rem;
    }

    .desc {
        color: white;
    }

    .readMore::after {
        content: "";
        position: absolute;
        top: 25px;
        left: 0;
        width: 0%;
        height: 3px;
        transition: all 0.5s;
        background-color: rgba(255, 255, 255, 0.6);
    }

    .readMore:hover {
        color: #fff;
    }

    .readMore:hover::after {
        width: 100%;
    }

    .close {
        grid-row: 1/2;
        justify-self: end;
    }

    .card div {
        grid-row: 2/3;
        font-size: 30px;
    }

    .read-more {
        grid-row: 4/5;
        align-self: center;
    }

    .card1 {
        background:#4257b4;
        ;
    }

    @media (max-width: 1600px) {
        .cards {
            justify-content: center;
        }
    }
</style>
<div class="custom-container">
    <c:forEach var="item" items="${Blog}" varStatus="loopStatus">
        <c:if test="${loopStatus.index % 3 == 0}">
            <div class="row">
            </c:if>

            <div class="col-md-4">
                <a href="${pageContext.request.contextPath}/QuestionSetURL?go=setDetails&SetId=${item.getSetId()}">
                    <div class="card card1"  style="width: 100%">
                        <div>
                            <ul>
                                <c:if test="${item.getSetVote() == 1}">
                                    <li><i class="fa-solid fa-star"></i></li>
                                    </c:if>
                                    <c:if test="${item.getSetVote() == 2}">
                                    <li><i class="fa-solid fa-star"></i></li>
                                    <li><i class="fa-solid fa-star"></i></li>
                                    </c:if>
                                    <c:if test="${item.getSetVote() == 3}">
                                    <li><i class="fa-solid fa-star"></i></li>
                                    <li><i class="fa-solid fa-star"></i></li>
                                    <li><i class="fa-solid fa-star"></i></li>
                                    </c:if>
                                    <c:if test="${item.getSetVote() == 4}">
                                    <li><i class="fa-solid fa-star"></i></li>
                                    <li><i class="fa-solid fa-star"></i></li>
                                    <li><i class="fa-solid fa-star"></i></li>
                                    <li><i class="fa-solid fa-star"></i></li>
                                    </c:if>
                                    <c:if test="${item.getSetVote() == 5}">
                                    <li><i class="fa-solid fa-star"></i></li>
                                    <li><i class="fa-solid fa-star"></i></li>
                                    <li><i class="fa-solid fa-star"></i></li>
                                    <li><i class="fa-solid fa-star"></i></li>
                                    <li><i class="fa-solid fa-star"></i></li>
                                    </c:if>
                            </ul>
                        </div>
                        <p class="close"><i class="fa-solid fa-xmark"></i></p>
                        <p class="desc" style="tex">${item.getTitle()}</br></p>
                        <p class="desc">Lecture: ${item.getUsername()}</p>
                    </div>
                </a>
            </div>

            <c:if test="${loopStatus.index % 3 == 2 or loopStatus.last}">
            </div>
        </c:if>
    </c:forEach>
</div>


<!-- Add Bootstrap and other scripts at the end of the body for better performance -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
