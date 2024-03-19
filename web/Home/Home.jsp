
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="utils.*" %>
<%@page import="Entity.User" %>
<%@page import="Entity.QuestionSet" %>
<%@ page import="Entity.Subject" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Quizlet FPT</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/search.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/adminCourseNav.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/courseEditorBase.css"/>
        <script src="https://kit.fontawesome.com/4008f7ead4.js" crossorigin="anonymous"></script>
        <script src="/assets/js/base.js"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;500&display=swap" rel="stylesheet">
        <link rel="icon" href="${pageContext.request.contextPath}/assets/img/coc-cham-hoc-logo.png" type="image/gif" sizes="16x16">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sliderStyle3.css" />

        <style>
            #backToHome {
                display: none;
            }
            .button a {
                text-decoration: none;
                border: 1px solid white;
                border-radius: 3px;
                padding: 8px 30px;
                color: white;
            }

            .nav-header ul li a {
                color: white;
                text-decoration: none;
            }

            .container {
                display: flex;
                justify-content: space-between;
            }
            .container {
                position: relative;
            }

            #prev, #next {
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
            }

            #prev {
                left: -63px;
            }

            #next {
                right: -64px;
            }

            .container .direction {
                text-align: center;
            }

            .container #formList {
                width:1200px;
                max-width: 1000%;
                overflow: auto;
                margin:100px auto 50px;
                scroll-behavior: smooth;
                scroll-snap-type: both;
            }

            /* .direction{
                text-align: center;
            } */
            .direction button{
                font-family: cursive;
                font-weight: bold;
                background-color: #ffffff44;
                border:none;
                width:50px;
                height:50px;
                border-radius: 50%;
                transition: 0.5s;
                margin:0 10px;
            }
            .direction button:hover{
                background-color: #ffffff;
            }
            .item{
                border-radius: 15px;
                width: 358px;
                height: 180px;
                background-color: #4257b4;
                overflow: hidden;
                transition: 0.5s;
                margin:10px;
                scroll-snap-align: start;
            }
            .item .avatar{
                display: block;
                margin:50px auto 10px;
                width:100px;
                height:100px;
                object-fit: cover;
                border-radius: 20px;
                box-shadow: 0 10px 15px #7e878d;
            }
            .item .content{
                padding:30px;
                font-family: monospace;
            }
            .item .content table td{
                color: white;
                padding:10px 0;
                border-bottom: 1px solid #AEC0CE;
            }
            .item .content table td:nth-child(2){
                text-align: right;
            }
            .item .nameGroup{
                text-align: center;
                border-bottom:none!important;
            }
            #list{
                display: flex;
                width:max-content;
            }
            /* #formList{
                width:1280px;
                max-width: 100%;
                overflow: auto;
                margin:100px auto 50px;
                scroll-behavior: smooth;
                scroll-snap-type: both;
            } */
            #formList::-webkit-scrollbar{
                display: none;
            }
            @media screen and (max-width: 1024px){
                .item{
                    width: calc(33.3vw - 20px);
                }
                .direction{
                    display: none;
                }
            }
            @media screen and (max-width: 768px){
                .item{
                    width: calc(50vw - 20px);
                }
                .direction{
                    display: none;
                }
            }
        </style>
    </head>


    <body>
        <%@include file="/Home/header.jsp" %> 
        <div class="document-center">
            <!--            <h2 class="carousel-title">Hot</h2>-->
            <div class="carousel-container">
                <div class="carousel" id="carousel-1" auto-scroll="10000">


                    <section class="carousel-screen">
                        <img src="https://www.generalatlantic.com/wp-content/uploads/2020/05/quizlet-logo-indigo-rgb.jpg" alt="This is a picture of slider" />
                    </section>


                    <!--These are not screens. They will always be on carousel-->
                    <section class="circle-container">
                        <!--These 'circles' need to match the number of carousel screens-->
                        <c:forEach items="${sliderList}" var="course">
                            <div class="circle"></div>
                        </c:forEach>
                    </section>
                    <div class="left-arrow">
                        <span class="chevron left"></span>
                    </div>
                    <div class="right-arrow">
                        <span class="chevron right"></span>
                    </div>
                </div>
            </div>
        </div>


        <!--         <h2 class="carousel-title">Quiz Least</h2>-->
        <div class="container">
            <div id="formList">
                <div id="list">
                    <c:forEach items="${listS}" var="item">
                        <div class="item">
                            <a href="${pageContext.request.contextPath}/QuestionSetURL?go=setDetails&SetId=${item.getSetId()}" style="text-decoration: none">
                                <div class="content">

                                    <table width="100%" cellspacing="0">
                                        <tr>
                                            <td>${item.getTitle()}</td>
                                            <td>100points</td>
                                        </tr>
                                    </table>
                                </div>
                            </a>      

                        </div>
                    </c:forEach>

                    <div class="item">

                        <div class="content">
                            <table width="100%" cellspacing="0">
                                <tr>
                                    <td>Quiz</td>
                                    <td>100points</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <div class="direction">
                <button id="prev"><</button>

            </div>
            <div class="direction">
                <button id="next">></button>
            </div>
        </div>

        <%@include file="/Home/QuizFinder.jsp" %> 
        <%@include file="/Home/footer.jsp" %>



    </body>
    <script>
        function toggleDropdown() {
            var dropdownContent = document.getElementById("myDropdown");
            dropdownContent.classList.toggle("show");
        }

        window.onclick = function (event) {
            if (!event.target.matches('.dropbtn')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
                for (var i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        };

        document.getElementById('next').onclick = function () {
            const widthItem = document.querySelector('.item').offsetWidth;
            document.getElementById('formList').scrollLeft += widthItem;
        };

        document.getElementById('prev').onclick = function () {
            const widthItem = document.querySelector('.item').offsetWidth;
            document.getElementById('formList').scrollLeft -= widthItem;
        };

        // Include the content of imageSlider2.js here if needed
        // You can either copy the code from imageSlider2.js or provide its content directly.

    </script>

    <script src="${pageContext.request.contextPath}/assets/js/imageSlider2.js"></script>
</html>