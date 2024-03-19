
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
<html>

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
        <div class="container-xl px-4 mt-4">
            <!-- Account page navigation-->
            <nav class="nav nav-borders">
                <p>
                    <a class="back_home-detail" href="HomeController" style="color: black;"><i class="fa fa-arrow-left" aria-hidden="true"></i> Homepage</a>
                </p>
            </nav>
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-xl-4">
                    <!-- Profile picture card-->
                    <div class="card mb-4 mb-xl-0">
                        <div class="card-header">Profile Picture</div>
                        <div class="card-body text-center">
                            <!-- Profile picture image-->
                            <c:if test="${data['RoleId'] == 1}">
                                <img class="img-account-profile rounded-circle mb-2" src="https://img.freepik.com/premium-vector/avatar-graduate-student-icon-vector-illustration-flat-style-isolated-white_647193-1752.jpg"
                                     style="width: 315px; height: 315px;"  alt="">
                            </c:if>
                            <c:if test="${data['RoleId'] == 2}">
                                <img class="img-account-profile rounded-circle mb-2" src="https://cdn1.iconfinder.com/data/icons/flat-education-icons-1/512/37-512.png"
                                     style="width: 315px; height: 315px;"  alt="">
                            </c:if>
                            <c:if test="${data['RoleId'] == 3}">
                                <img class="img-account-profile rounded-circle mb-2" src="https://cdn4.iconfinder.com/data/icons/web-design-and-development-1-7/64/25-512.png"
                                     style="width: 315px; height: 315px;"  alt="">
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="col-xl-8">
                    <!-- Account details card-->
                    <div class="card mb-4">
                        <div class="card-header">Account Details</div>
                        <div class="card-body">

                            <form action="" method="post" id="form-1" onsubmit="return validateForm();">
                                <!-- Form Group (username)-->
                                <input class="form-control" id="inputAccId" type="text" placeholder="Nhập tên người dùng"
                                       name="accId" value="${data['AccountId']}" readonly>



                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">User Name</label>
                                    <input class="form-control" id="inputUsername" type="text" placeholder="Nhập tên người dùng"
                                           name="user" value="${data['Username']}">
                                    <span class="form-message text-danger" id="username-error"></span>
                                </div>
                                <!-- Admin -->

                                <c:if test="${data['RoleId'] == 3}">
                                    <div class="mb-3">
                                        <label class="small mb-1" >Name</label>
                                        <input class="form-control" id="inputAdminName" type="text" placeholder=""
                                               name="adName" value="${data['AdminName']}">

                                    </div>
                                </c:if>

                                <!-- Teacher -->
                                <c:if test="${data['RoleId'] == 2}">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputTeacherName">Name</label>
                                        <input class="form-control" id="inputTeacherName" type="text" placeholder=""
                                               name="tcName" value="${data['TeacherName']}">
                                    </div>

                                </c:if>
                                <!-- Student -->
                                <c:if test="${data['RoleId'] == 1}">
                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputStudentName"> Name</label>
                                        <input class="form-control" id="inputStudentName" type="text" placeholder=""
                                               name="stName" value="${data['StudentName']}">
                                    </div>

                                    <div class="mb-3">
                                        <label class="small mb-1" for="inputDob">Dob</label>
                                        <input class="form-control" id="inputDob" type="date" placeholder="" name="dob" value="${data['Dob']}">
                                        <span class="form-message text-danger" id="dob-error"></span>
                                    </div>

                                </c:if>
                                <!<!-- Phone -->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputPhone">Phone</label>
                                    <input class="form-control" id="inputPhone" type="text" placeholder="Nhập số điện thoại"
                                           name="phone" value="${data['Phone']}" onblur="validatePhoneNumber(this)">
                                    <span class="form-message text-danger" id="phone-error"></span>
                                </div>
                                <!-- Mật khẩu -->


                                <div class="mb-3">
                                    <label class="small mb-1" for="inputPassword">Password</label>
                                    <input type="password" class="form-control" id="ipnPassword" name="pass" value="${data['Password']}" onblur="validatePassword(this)">

                                    <span class="form-message text-danger" id="password-error"></span>
                                </div>




                                <!-- Nhóm biểu mẫu (địa chỉ email)-->
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Email</label>
                                    <input class="form-control" id="inputEmailAddress" type="email" name="email"
                                           placeholder="Nhập địa chỉ email" value="${data.get('Email')}" readonly>
                                </div>
                                <div class="mb-3">
                                    <label class="small mb-1" for="roleSelect">Role</label>
                                    <div class="form-group">
                                        <select class="form-control" id="roleSelect" name="roleId" required>
                                            <option value="">Select</option>
                                            <c:choose>
                                                <c:when test="${data['RoleId'] == 1}">
                                                    <option value="3" >Admin</option>
                                                    <option value="2" >Teacher</option>
                                                    <option value="1" selected>Student</option>
                                                </c:when>
                                                <c:when test="${data['RoleId'] == 2}">
                                                    <option value="3" >Admin</option>
                                                    <option value="2" selected>Teacher</option>
                                                    <option value="1" >Student</option>
                                                </c:when>
                                                <c:when test="${data['RoleId'] == 3}">
                                                    <option value="3" selected>Admin</option>
                                                    <option value="2" >Teacher</option>
                                                    <option value="1" >Student</option>
                                                </c:when>
                                            </c:choose>
                                        </select>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label class="small mb-1" for="statusSelect">Status</label>
                                    <div class="form-group">
                                        <select class="form-control" id="statusSelect" name="status" required>
                                            <option value="">Select</option>
                                            <c:choose>
                                                <c:when test="${data['IsActive'] == true}">
                                                    <option value="1" selected>Active</option>
                                                    <option value="0" >Suspended</option>
                                                </c:when>
                                                <c:when test="${data['IsActive'] == false}">
                                                    <option value="0" selected>Suspended</option>
                                                    <option value="1" >Active</option>
                                                </c:when>
                                            </c:choose>
                                        </select>
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

    <script>
        function validateForm() {
            // Reset error messages
            document.getElementById('username-error').innerText = "";
            document.getElementById('phone-error').innerText = "";
            document.getElementById('password-error').innerText = "";

            // Get values of username, phone, and password fields
            var username = document.getElementById('inputUsername').value;
            var phone = document.getElementById('inputPhone').value;
            var password = document.getElementById('ipnPassword').value;

            // Regular expressions for special characters and password length check
            var specialCharacters = /[!@#$%^&*(),.?":{}|<>]/;
            var passwordLengthRegex = /^.{8,32}$/; // Regex for password length between 8 and 32 characters

            // Variable to track if any validation errors occurred
            var hasErrors = false;

            // Check if the username contains special characters
            if (specialCharacters.test(username)) {
                document.getElementById('username-error').innerText = "Username cannot contain special characters";
                hasErrors = true;
            }

            // Check if the phone number is not exactly 10 digits
            if (!/^\d{10}$/.test(phone)) {
                document.getElementById('phone-error').innerText = "Phone number must be exactly 10 digits";
                hasErrors = true;
            }

            // Check if the password length is within the specified range
            if (!passwordLengthRegex.test(password)) {
                document.getElementById('password-error').innerText = "Password must be between 8 and 32 characters";
                hasErrors = true;
            }

            // If any validation errors occurred, prevent form submission
            if (hasErrors) {
                return false;
            }

            // If all validations pass, allow form submission
            return true;
        }

    </script>



</html>

