<%-- 
    Document   : header
    Created on : Jan 20, 2024, 4:53:30 PM
    Author     : Asus
--%>

<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
    .role {
        font-size: 16px;
        font-weight: 300;
        display: flex;
        margin: 15px;
        margin: 0;
        color: yellow;
    }
    .header-container{
        background-color: #4257b4;
    }
</style>

<div class="header-container">
    <c:set var="acc" value="${sessionScope.acc}" />
    <div class="navbar">
        <div class="bold">
            <a href="HomeController">
                <img src="${pageContext.request.contextPath}/assets/img/quiz.png" alt="alt" width="70px" style="margin: 10px"/>
            </a>
        </div>
        <div class="bold" id="backToHome">
            <a href="HomeController">
                <label style="cursor: pointer;margin: 10px">Back To Home</label>
            </a>
        </div>
        <nav class="nav-header">
            <ul>
                <li><a href="HomeController">HOME</a></li>
                <c:if test="${acc != null}">
                    <li><a href="ClassJoinListURL">CLASS</a></li>

                </c:if>
                <c:if test="${acc != null}">
                    <li><a href="QuestionSetURL">QUIZ</a></li>

                </c:if>

            </ul>
        </nav>
        <div class="user-items">
            <c:if test="${acc != null}">
                <span class="role">
                    <c:choose>
                        <c:when test="${acc.getRoleId() eq 1}">STUDENT</c:when>
                        <c:when test="${acc.getRoleId() eq 2}">TEACHER</c:when>
                        <c:when test="${acc.getRoleId() eq 3}">ADMIN</c:when>
                    </c:choose>
                </span>
                <div class="dropdown">
                    <button class="dropbtn" onclick="toggleDropdown()" style="background-color: #4257b4 ; color: yellow">
                        Hi, ${acc.getUsername()}
                    </button>
                    <div class="dropdown-content" id="myDropdown">
                        <a class="dropdown-item" href="UpdateProfile?sid=${acc.accountId}"><i class="fa-solid fa-user"></i> Profile</a>

                        <a class="dropdown-item" href="MyEnrollURL"><i class="fa-solid fa-pen-to-square"></i> My Enroll</a>

                        <c:if test="${acc.getRoleId() == '3'}">
                            <a class="dropdown-item admin-item" href="/QuizzesOnline/ManagerUserURL"><i class="fa-solid fa-pen-to-square"></i>Manager User</a>
                            <a class="dropdown-item admin-item" href="/QuizzesOnline/SettingControllerURL"><i class="fa-solid fa-gear"></i> Setting List</a>
                        </c:if>


                        <a class="dropdown-item" href="LogoutURL"><i class="fa-solid fa-right-from-bracket"></i>  Logout</a>
                    </div>
                </div>
            </c:if>
            <c:if test="${acc == null}">
                <div class="button normal" style="margin-bottom: 20px"><a href="LoginURL"><i class="fa-solid fa-right-to-bracket"></i>  Login</a></div>
            </c:if>
        </div>
    </div>
</div>
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

</script>