<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="Entity.Class" %>
    <%@ page import="Entity.QuestionSet" %>
    <%@ page import="java.util.HashMap" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta charset="utf-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>My Enroll</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <style type="text/css">
            .btn-save {
                background-color: #f5f5f5;
                color: #333333;
            }

            .btn-save:hover {
                background-color: #cccccc;
            }

            h1,h2,h3{
                text-align: center;
                color: white;
            }
            li{
                color: #2c3e50;
                font-size: 16px;
                line-height: 30px;
                text-align: justify;
                letter-spacing: 1px;
            }
            .SG{
                margin: 0;
                padding: 0;
                text-align: center;
                display: flex;
                flex-wrap: wrap;
            }
            .SG .sgLi {
                width: 100%;
                margin: 1%;
            }
            .SG .box{
                padding: 5px;
                height: 150px;
                background-color: #eeeeee;
                border-radius: 10px;
                border: 3px solid #e5e7e8;
            }

            #toggleContainer {
                background-color: white;
                color: black;
                border: none;
                border-radius: 0px;
                padding: 10px 20px;
                cursor: pointer;
                transition: background-color 1s ease;
                outline: none;
                margin: 0;
            }
            #toggleContainer:hover {
                background-color: #cccccc;
                border-radius: 0px;
            }
            #searchInput,
            .btn btn-save .button {
                margin: 10px;
                padding: 8px 12px;
                border: none;
                border-radius: 0;
                outline: none;
                font-size: 16px;
                background-color: #f2f2f2;
                color: #333;
            }

            .btn btn-save .button:hover {
                background-color: #ddd;
            }
            .search-container {
                display: flex;
                align-items: center;
            }

            .Category {
                border-radius: 5px;
                width: 200px;
                height: 500px;
                background-color: #6d9eff;
                margin-top: 80px;
                margin-left: 10%;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
                overflow: auto;
            }
            .Category::-webkit-scrollbar {
                display: none;
                width: 5px;
            }
            .Category::-webkit-scrollbar-track {
                background-color: #6d9eff;
            }

            .Category::-webkit-scrollbar-thumb {
                background-color: rgba(0, 0, 0, 0.3);
            }
            .Category:hover::-webkit-scrollbar {
                display: block;
            }
            .CategoryHeader {
                background-color: #4257b4;
                padding: 1px 0 ;
                text-align: center;
                cursor: pointer;
            }

            .CategoryHeader h2 {
                margin: 0;
                color: #fff;
            }

            .CategoryContent {
                height: 0px;
                overflow: hidden;
                transition: height 0.3s ease;
            }

            .CategoryContent ul {
                list-style-type: none;
                padding: 0;
                justify-content: center;
            }

            .CategoryContent li {
            }

            .CategoryContent ul li a {
                color: #fff;
                text-decoration: none;
                font-weight: bold;
                display: block;
                padding: 5px;
                transition: background-color 0.3s;
            }

            .CategoryContent ul li a:hover {
                background-color: #576b95;
            }
            .StarRate {
                height: auto;
                overflow: hidden;
                transition: height 0.3s ease;
            }

            .StarRate ul {
                list-style-type: none;
                padding: 0;
            }

            .StarRate li {
                padding: 5px;
            }
            .StarRate li:hover {
                background-color: #576b95;
            }
            .StarRate ul li a {
                color: #fff;
                text-decoration: none;
                font-weight: bold;
                display: block;
                padding: 5px;
                transition: background-color 0.3s;
            }

            .StarRate ul li a:hover {
                background-color: #576b95;
            }

            .delete img {
                width: 30px;
                height: auto;
                cursor: pointer;
                transition: opacity 0.3s ease;
            }
            .delete img:hover {
                opacity: 0.7;
            }


        </style>
    </head>
    <body style="background-color: #eeeeee">
        <div class="header">
            <%@include file="/Home/header.jsp" %> 
        </div>
        <!--        List All Question Set -->
        <h2 style="color: black; text-align: left; margin-left: 10%;">
            <a href="MyEnrollURL" style="color: inherit; text-decoration: none;">My Enroll</a>
        </h2>
        <c:if test="${empty questionSetListAll}">
            <div style="text-align: center;">
                <img src="${pageContext.request.contextPath}/Class/images/Try.jpg" width="500px" alt="alt"/>
                <h1 style="color: black"><a href="HomeController" style="color: inherit; text-decoration: none;">Let's begin learning!</a></h1>
            </div>
        </c:if>
        <div  style="display: flex">
            <c:if test="${not empty questionSetListAll}"> 
                <div class="Category">
                    <div class="CategoryHeader" onclick="toggleCategoryContent()">
                        <h3>Categories</h3>
                    </div>
                    <div class="CategoryContent" id="CategoryContent">
                        <ul>
                            <c:forEach var="Categories" items="${Categories}">
                                <li><a href="MyEnrollURL?go=category&SubCode=${Categories.getSubjectId()}" style="padding:10px">${Categories.getSubjectCode()}</a></li>
                                </c:forEach>
                        </ul>
                    </div>  
                </div>
                <div class="Containt" style="width: 60%;">
                    <div id="paginationControls" style="display: flex ;align-content: center;justify-content: center; width: 100%">
                        <form  id="searchForm" action="MyEnrollURL" method="Post">
                            <input type="hidden" name="go" value="search">
                            <div class="search-container">
                                <input type="text" id="searchInput" name="searchQuery" placeholder="Enter search ...">
                                <button class="btn btn-save" type="submit">Search</button>
                            </div>
                        </form>
                    </div>
                </c:if>
                <ul class="SG" id="questionSetListAll">
                    <c:forEach var="questionSetAll" items="${questionSetListAll}">
                        <li class="sgLi"  style="">
                            <div class="box" style="display: flex; justify-content: space-between;">
                                <div style="width: 50%; cursor: pointer;" onclick="redirectToSet('${questionSetAll.getSetId()}')" >
                                    <h4 style="color: #f5f5f5; margin-left: 10px; color: black">${questionSetAll.getTitle()}</h4>
                                    <h5 style="color: #f5f5f5; margin-left: 10px; color: black">${subjectMapAll[questionSetAll.getSubjectId()].getSubjectName()} (${subjectMapAll[questionSetAll.getSubjectId()].getSubjectCode()})</h5>
                                    <br>
                                    <h5 style="color: #f5f5f5; margin-left: 10px; color: black">
                                        Star Rate : ${questionSetAll.getSetVote()} <img style="margin-bottom: 5px" src="${pageContext.request.contextPath}/Class/images/star.png" width="20px"/> 
                                    </h5>
                                </div> 
                                <div class="delete" >
                                    <img src="${pageContext.request.contextPath}/Class/images/close.png" alt="Unenroll" class="deleteButton" onclick="confirmDelete(${questionSetAll.getSetId()})">
                                </div>  
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <c:if test="${not empty questionSetListAll}">
                    <div id="paginationControls" style="display: flex ;align-content: center;justify-content: center; width: 100%;">
                        <button onclick="previousPageAll()" class="btn btn-save" style="outline: none;border-radius: 50%"><</button>
                        <span id="currentPageAll" class="pagination-info" style="margin: 5px 10px"></span>
                        <button onclick="nextPageAll()" class="btn btn-save" style="outline: none;border-radius: 50%">></button>
                    </div>
                </c:if>
            </div> 
            <c:if test="${not empty questionSetListAll}">
                <div class="Category" style="margin-left: 0">
                    <div class="CategoryHeader">
                        <h3 style="color: gold">Star rating</h3>
                    </div>
                    <div class="StarRate" id="StarRate">
                        <ul id="starList">
                        </ul>
                    </div>
                </div>
            </c:if>
        </div>  
    </body>
    <script>
        var currentPageAll = 1; // Trang hiện tại cho danh sách questionSetListAll
        var itemsPerPageAll = 3; // Số lượng mục trên mỗi trang cho questionSetListAll

        function displayPageAll(page) {
            var startIndex = (page - 1) * itemsPerPageAll;
            var endIndex = startIndex + itemsPerPageAll;
            var questionSetsAll = document.getElementById("questionSetListAll").getElementsByTagName("li");
            for (var i = 0; i < questionSetsAll.length; i++) {
                if (i >= startIndex && i < endIndex) {
                    questionSetsAll[i].style.display = "block";
                } else {
                    questionSetsAll[i].style.display = "none";
                }
            }

            // Hiển thị trang hiện tại
            document.getElementById("currentPageAll").innerText = currentPageAll;
        }

        // Hiển thị trang đầu tiên khi trang được tải
        displayPageAll(currentPageAll);
        function nextPageAll() {
            var totalQuestionsAll = document.getElementById("questionSetListAll").getElementsByTagName("li").length;
            var totalPagesAll = Math.ceil(totalQuestionsAll / itemsPerPageAll);
            if (currentPageAll < totalPagesAll) {
                currentPageAll++;
                displayPageAll(currentPageAll);
            }
        }

        function previousPageAll() {
            currentPageAll--;
            if (currentPageAll < 1) {
                currentPageAll = 1;
            }
            displayPageAll(currentPageAll);
        }
    </script>
    <script>
        function toggleCategoryContent() {
            var categoryContent = document.getElementById("CategoryContent");
            if (categoryContent.style.height === "0px" || categoryContent.style.height === "") {
                categoryContent.style.height = 'auto';
            } else {
                categoryContent.style.height = "0px";
            }
        }
    </script>
    <script>
        function redirectToSet(setId) {
            var url = "QuestionSetURL?go=setDetails&SetId=" + setId;
            window.location.href = url;
        }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
        function confirmDelete(setId) {
            Swal.fire({
                title: 'Do you want to unenroll?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes',
                cancelButtonText: 'No'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "MyEnrollURL?go=unenroll&SetId=" + setId;
                }
            });
        }
    </script>
    <script>
        const starList = document.getElementById("starList");

        for (let i = 5; i >= 1; i--) {
            const listItem = document.createElement("li");
            listItem.style.display = "flex";

            for (let j = 0; j < i; j++) {
                const starListItem = document.createElement("li");
                starListItem.style.marginRight = "5px";

                const starImg = document.createElement("img");
                starImg.style.verticalAlign = "middle";
                starImg.style.width = "20px";
                starImg.src = "${pageContext.request.contextPath}/Class/images/star.png";
                starImg.alt = "Star";

                starListItem.appendChild(starImg);
                listItem.appendChild(starListItem);
            }

            // Thêm sự kiện click cho mỗi phần tử <li> để chuyển hướng người dùng
            listItem.addEventListener('click', function () {
                redirectToPage("MyEnrollURL?go=Star&Num=" + i);
            });

            starList.appendChild(listItem);
        }

        // Hàm chuyển hướng người dùng đến địa chỉ URL mong muốn
        function redirectToPage(url) {
            window.location.href = url;
        }
    </script>
</html>
