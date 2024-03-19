<%-- 
    Document   : slider
    Created on : Jan 20, 2024, 4:56:49 PM
    Author     : Asus
--%>

<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sliderStyle3.css" />
<div class="document-center">
    <!--    <h2 class="carousel-title">Quiz Least</h2>-->
    <div class="carousel-container">
        <div class="carousel" id="carousel-1" auto-scroll="10000">


            <section class="carousel-screen">
                <img src="https://toquoc.mediacdn.vn/280518851207290880/2022/12/22/12-1671683430473740576022.jpg" alt="" />
                <section class="text-container">
                    <p>MATH</p>
                    <p>Lecturer:LE VAN KIEN </p>
                </section>
            </section>
            </a>

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
<script src="${pageContext.request.contextPath}/assets/js/imageSlider2.js"></script>
