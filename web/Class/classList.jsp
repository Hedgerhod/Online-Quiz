<%-- 
    Document   : classList
    Created on : Jan 20, 2024, 1:24:13 PM
    Author     : phamg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.Class" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Mobile M   etas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />                                  
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title> Class </title>
        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Class/css/bootstrap.css" />
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>
        <link href="${pageContext.request.contextPath}/Class/css/style.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/Class/css/mainStyle.css" rel="stylesheet" />
        <style>
            .detail-box {
                width: 100%;
                background-image: url('${pageContext.request.contextPath}/Class/images/img_read.png');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                text-align: left;
                border-radius: 10px 10px 0px 0px;
                padding: 10px 0 0 10px;
            }

        </style>
    </head>

    <body class="sub_page">
        <!-- Header sesion-->
        <%@include file="/Home/header.jsp" %> 
        <!-- End Header sesion-->       
        <div class="select_class">      
            <!-- My Class -->
            <a href="ClassListURL" target="_self" class="current-page">My Class</a>

            <!-- Learn Class -->
            <a href="ClassJoinListURL" target="_self">Learn Class</a>

            <img style="height:20px; margin: 5px 0px " onclick="openForm()"
                 src="${pageContext.request.contextPath}/Class/images/add.png" alt="alt" />
        </div>
        <!-- Class List -->
        <section class="service_section layout_padding">
            <div class="service_container">
                <div class="container">
                    <div class="row">
                        <%
                        String nameTeacher = (String) session.getAttribute("nameTeacher");
                        ArrayList<Class> classList = (ArrayList<Class>) request.getAttribute("data");
                        char firstCharacter = nameTeacher.charAt(0);

                        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
                        int itemsPerPage = 6; 
                        int startIdx = (currentPage - 1) * itemsPerPage;
                        int endIdx = Math.min(startIdx + itemsPerPage, classList.size());

                        for (int i = startIdx; i < endIdx; i++) {
                            Class myclass = classList.get(i);
                        %>

                        <div class="col-md-4">
                            <div class="box" style="padding: 0px ">
                                <div class="detail-box">
                                    <h5 style="font-size: 1.375rem;">
                                        <a href="ClassDetailURL?classId=<%= myclass.getClassId() %>">
                                            <%= myclass.getClassName() %>
                                        </a>
                                    </h5>
                                    <p style="font-size: 0.8125rem;">
                                        <%= nameTeacher %>
                                    </p>
                                    <p style="font-size: 0.8125rem;">
                                        Created on:
                                        <%= myclass.getCreateDate() %>
                                    </p>
                                </div>
                                <div class="circle"><%= firstCharacter %></div>
                            </div>
                            <div class="outer-box">
                                <a href="#" onclick="showDeleteConfirmation('<%= myclass.getClassId()%>')">
                                    <img style="height:20px"
                                         src="${pageContext.request.contextPath}/Class/images/delete.png" title="Delete Class" alt="alt" />
                                </a>
                            </div>
                        </div>

                        <%
                        }
                        %>
                    </div>
                </div>
            </div>
        </section>
        <!-- end Class List -->

        <!--Popup-->
        <div class="Popup">
            <div class="behind" id="popupBehind" onclick="closeForm()"></div>
            <div class="formPopup" id="popupForm">    
                <form action="/QuizzesOnline/ClassListURL" method="post" class="formContainer" onsubmit="return validateForm() >
                            <p style="text-align: left; font-weight: bold">Create a Class</p>
                    <input type="hidden" name="go" value="addClass">
                    <div class="inputGroup">
                        <input type="text" id="className" name="className" placeholder=" " required>
                        <span class="title">Class Name (required)</span>
                    </div>

                    <div class="inputGroup">
                        <input type="text" id="subject" name="subject" placeholder=" " required>
                        <span class="title">Subject</span>
                    </div>

                    <button type="button" class="btn_cancel" onclick="closeForm()">Cancel</button>
                    <button type="submit" class="btn_add">Add</button>
                </form>
            </div>
        </div>
        <!--End Popup-->

        <!-- Pagination -->
                      <%
                      if (classList.isEmpty()) {
                      %>
                      <div class="pagination">
                        <img src="${pageContext.request.contextPath}/Class/images/EmptyClass.png" alt="alt"/>
                    </div>
                    <%
                    } else {
                    %>
                    <div class="pagination">
                        <%
                        int totalPages = (int) Math.ceil((double) classList.size() / itemsPerPage);
                        int previousPage = currentPage - 1;
                        String firstPageLink = "ClassListURL?page=1"; 
                        String previousPageLink = "ClassListURL?page=" + previousPage;
                        if (previousPage > 0) {
                        %>
                        <a href="<%= firstPageLink %>">F</a> 
                        <a href="<%= previousPageLink %>"><%= currentPage - 1 %></a>
                        <%
                        }
                        String currentPageLink = "ClassListURL?page=" + currentPage;
                        %>
                        <a href="<%= currentPageLink %>" class="current-page"><%= currentPage %></a>
                        <%
                        int nextPage = currentPage + 1;
                        String lastPageLink = "ClassListURL?page=" + totalPages; 
                        String nextPageLink = "ClassListURL?page=" + nextPage;
                        if (nextPage <= totalPages) {
                        %>
                        <span class="next-page-container">
                            <a href="<%= nextPageLink %>"><%= nextPage %></a>
                        </span>
                        <a href="<%= lastPageLink %>">L</a> 
                        <%
                        }
                        %>
                    </div>
                    <%
                        }
                    %>
                    <!-- End Pagination -->


                    <!-- footer section -->

                    <!-- footer section -->
                    </body>
                    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
    function showDeleteConfirmation(classId) {
                    Swal.fire({
                    title: 'Delete Class',
                            text: 'Are you sure you want to delete this class?',
                            icon: 'warning',
                            showCancelButton: true,
                            confirmButtonText: 'Delete',
                            cancelButtonText: 'Cancel',
                    }).then((result) => {
            if (result.isConfirmed) {
            window.location.href = "ClassListURL?go=Delete&ClassId=" + classId;
            }
            });
        }
        </script>

                    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var currentPath = window.location.pathname;
            var links = document.querySelectorAll('a');
            links.forEach(function (link) {
            if (link.getAttribute('href') === currentPath) {
            link.classList.add('current-page');
            }
            });
                });
                </script>

                <script>
                function openForm(){
                    document.getElementById("popupForm").style.display = "block";
                    document.getElementById("popupBehind").style.display = "block";
        }
        function closeForm() {
                    document.getElementById("popupForm").style.display = "none";
            document.getElementById("popupBehind").style.display = "none";
            }
                    </script>
            <script>
            function validateForm() {
                    var className = document.getElementById('className').value.trim();
            var subject = document.getElementById('subject').value.trim();
            className.replaceAll("\\s+", " ");
                subject.replaceAll("\\s+", " ");
            if (className === "" || subject === "") {
            alert('Class name cannot be empty !');
            return false;
            }
            if (!/[^ ]{7}/.test(className)) {
            alert('Class Name must contain at least 7 characters !');
            return false;
            }
            if (!/[^ ]{3}/.test(subject)) {
            alert('subject must contain at least 3 characters !');
            return false;
            }
            return true;
                        }
</script>
</html>