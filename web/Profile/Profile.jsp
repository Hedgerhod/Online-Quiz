
<%@ page import="Entity.User" %>
<%@ page import="Entity.Admin" %>
<%@ page import="Entity.Teacher" %>
<%@ page import="Entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="Entity.Admin" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="/CSS/profile.css" />
        <!-- font awesome 5.13.1 -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" />
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Profile</title>
        <script>
            function confirmUpdate() {
                return confirm("Are you sure you want to save changes?");
            }

        </script>
        <script>
            // step 1
            const ipnElement = document.querySelector('#ipnPassword')
            const btnElement = document.querySelector('#btnPassword')

            // step 2
            btnElement.addEventListener('click', function () {
                // step 3
                const currentType = ipnElement.getAttribute('type')
                // step 4
                ipnElement.setAttribute(
                        'type',
                        currentType === 'password' ? 'text' : 'password'
                        )
            })
        </script>
    </head>

    <body>
        <c:set var="accId" value="${sessionScope.acc.getAccountId()}" />
        <div class="container-xl px-4 mt-4">
            <!-- Account page navigation-->
            <nav class="nav nav-borders">
                <a class="nav-link active ms-0"
                   href="https://www.bootdey.com/snippets/view/bs5-edit-profile-account-details" target="__blank">Profile</a>
            </nav>
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-xl-4">
                    <!-- Profile picture card-->
                    <div class="card mb-4 mb-xl-0">
                        <div class="card-header">Profile Picture</div>
                        <div class="card-body text-center">
                            <!-- Profile picture image-->
                            <img class="img-account-profile rounded-circle mb-2" src="http://bootdey.com/img/Content/avatar/avatar1.png"
                                 alt="">
                            <a href="/QuizzesOnline/change?username=${acc.getUsername()}">Change Password</a>
                        </div>

                    </div>
                </div>
                <div class="col-xl-8">
                    <!-- Account details card-->
                    <div class="card mb-4">
                        <div class="card-header">Account Details</div>
                        <div class="card-body">

                            <form action="" method="post" onsubmit="return confirmUpdate();">
                                <!-- Form Group (username)-->
                                <input class="form-control" id="inputUsername" type="text" placeholder="Nhập tên người dùng"
                                       name="accId" value="${data['AccountId']}" readonly>


                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">Tên người dùng</label>
                                    <input class="form-control" id="inputUsername" type="text" placeholder="Nhập tên người dùng"
                                           name="user" value="${data['Username']}">
                                </div>

                                <!-- Admin -->
                                <c:if test="${data['RoleId'] == 1}">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Admin Name</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder=""
                                               name="adName" value="${data['AdminName']}">
                                    </div>
                                </c:if>

                                <!-- Teacher -->
                                <c:if test="${data['RoleId'] == 2}">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Teacher Name</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder=""
                                               name="tcName" value="${data['TeacherName']}">
                                    </div>

                                </c:if>
                                <!-- Student -->
                                <c:if test="${data['RoleId'] == 3}">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Student Name</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder=""
                                               name="stName" value="${data['StudentName']}">
                                    </div>

                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputUsername">Dob</label>
                                        <input class="form-control" id="inputUsername" type="text" placeholder=""
                                               name="dob" value="${data['Dob']}">
                                    </div>
                                </c:if>
                                <!<!-- Phone -->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">Điện thoại</label>
                                    <input class="form-control" id="inputUsername" type="text" placeholder="Nhập số điện thoại"
                                           name="phone" value="${data['Phone']}">
                                    <p>${mess}</p>
                                </div>
                                <!-- Mật khẩu -->


                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">Mật khẩu</label>
                                    <input type="password" class="form-control" id="ipnPassword" name="pass" value="${data['Password']}">

                                </div>




                                <!-- Nhóm biểu mẫu (địa chỉ email)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Địa chỉ email</label>
                                    <input class="form-control" id="inputEmailAddress" type="email" name="email"
                                           placeholder="Nhập địa chỉ email" value="${data.get('Email')}" readonly>
                                </div>


                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Status</label>
                                    <div class="form-group">
                                        <input class="form-control" id="inputEmailAddress" type="text" name="status"
                                               placeholder="Status" value="${data['IsActive'] ? 'Active' : 'Suspended'}" readonly>
                                    </div>
                                </div>




                                <!-- Nút Lưu thay đổi-->
                                <button class="btn btn-primary" type="submit">Lưu thay đổi</button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>

