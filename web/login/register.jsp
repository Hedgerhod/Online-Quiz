<%-- 
    Document   : register
    Created on : May 26, 2023, 6:47:30 PM
    Author     : hoang
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link rel="stylesheet" href="assets/css/logreg.css">
        <link rel="stylesheet" href="assets/css/register.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;500&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>

    <body>
        <div class="container">
            <div class="btn-back_home">
                <p>
                    <a class="back_home-detail" href="homeAction"><i class="fa fa-arrow-left" aria-hidden="true"></i> Homepage</a>
                </p>
            </div>
            <div class="container-form">
                <div class="left-part_space"></div>
                <div class="left_part">
                    <div class="left-heading">
                        <span>One Step Closer To your dream</span>
                    </div>
                    <div class="left-des">
                        <span>A free E-Learning service that is ready to help you become an expert</span>

                    </div>
                </div>
                <div class="right_part">
                    <!-- form register student -->

                    <form action="RegisterURL" method="POST" id="form-1">
                        <input type="hidden" name="go" value="registerStudent">
                        <div class="right-head">
                            <h3 class="right-title">Student Register</h3>
                            <p class="right-desc">Create your student account or</p>
                            <span><a href="#" id="change_form_teacher">create teacher account</a></span>

                        </div>


                        <div class="form-group"> 
                            <input id="username" name="username" type="text" placeholder="User Name" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="fullname" name="fullname" type="text" placeholder="Your Name" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="date" name="date" type="date" placeholder="Your birth day" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="phone" name="phone" type="text" placeholder="Your phone Number" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="email" name="email" type="text" placeholder="Email" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input type="password" id="password" name="password" class="form-control" placeholder="Password" >
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="password-confirm" name="password-confirm" type="password" placeholder="Re-enter your password" class="form-control" >
                            <span id="msg"class="form-message"></span>
                        </div>
                        <span class="error_msg">${Email_DUP}</span>
                        <div class="eulabox">
                            <input type="checkbox" id="accept-eula" name="checkbox" required>
                            <label for="accept-eula">I agree to COC <a href="${pageContext.request.contextPath}/home">Terms of use</a></label>
                        </div>
                        <button class="form-submit">Register</button>
                        
                    </form>

                    <!-- form register teacher -->
                    <form action="RegisterURL" method="POST" id="form-2">
                        <input type="hidden" name="go" value="registerTeacher">
                        <div class="right-head">
                            <h3 class="right-title">Teacher Register</h3>
                            <p class="right-desc">Create your teacher account or</p>
                            <span><a href="#" id="change_form_student">create student account</a></span>
                        </div>
                        <div class="form-group"> 
                            <input id="username" name="username" type="text" placeholder="User Name" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="fullname" name="fullname" type="text" placeholder="Your full Name" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="phone" name="phone" type="text" placeholder="Your phone Number" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="email" name="email" type="text" placeholder="Email" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input type="password" id="password" name="password" class="form-control" placeholder="Password" >
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group"> 
                            <input id="password-confirm" name="password-confirm" type="password" placeholder="Re-enter your password" class="form-control" >
                            <span id="msg"class="form-message"></span>
                        </div>
                        <span class="error_msg">${Email_DUP}</span>
                        <div class="eulabox">
                            <input type="checkbox" id="accept-eula" name="checkbox" required>
                            <label for="accept-eula">I agree to COC <a href="${pageContext.request.contextPath}/home">Terms of use</a></label>
                        </div>
                        <button class="form-submit">Register</button>

                    </form>
                    <div class="logreg-link">
                        <span>Already have an account? <a href="LoginURL">Login</a></span>
                    </div>
                </div>
                <div class="right-part_space"></div>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="assets/js/register.js"></script>
        <script>
            function toggleChangeSignUp(e) {
                e.preventDefault();
                $("#form-1").toggle(); // display:block or none
                $("#form-2").toggle(); // display:block or none
            }

            $(() => {
                // Login Register Form
                $("#change_form_teacher").click(toggleChangeSignUp);
                $("#change_form_student").click(toggleChangeSignUp);
            });
            Validator({
                form: "#form-1",
                formGroupSelector: ".form-group",
                querySelector: ".form-message",
                rules: [
                    Validator.isRequired("#fullname"),
                    Validator.isRequired("#username"),
                    Validator.isDatePast("#date"),
                    Validator.isPhoneNumber("#phone"),
                    Validator.isEmail("#email"),
                    Validator.isRequired("#email"),
                    Validator.isMinlength("#password", 8),
                    Validator.isMaxlength("#password", 32),
                    Validator.isRequired("#password-confirm"),
                    Validator.isConfirmed("#password-confirm", function () {
                        return document.querySelector("#form-1 #password").value;
                    }, "Password doesn't match!")
                ]
            });
            Validator({
                form: "#form-2",
                formGroupSelector: ".form-group",
                querySelector: ".form-message",
                rules: [
                    Validator.isRequired("#fullname"),
                    Validator.isRequired("#username"),
                    Validator.isPhoneNumber("#phone"),
                    Validator.isEmail("#email"),
                    Validator.isRequired("#email"),
                    Validator.isMinlength("#password", 8),
                    Validator.isMaxlength("#password", 32),
                    Validator.isRequired("#password-confirm"),
                    Validator.isConfirmed("#password-confirm", function () {
                        return document.querySelector("#form-2 #password").value;
                    }, "Password doesn't match!")
                ]
            });
        </script>  
    </body>
</html>
