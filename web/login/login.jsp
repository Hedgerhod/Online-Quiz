<%-- 
    Document   : login
    Created on : May 24, 2023, 8:15:50 PM
    Author     : LAPTOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/css/login.css">
        <link rel="stylesheet" href="assets/css/logreg.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;500&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <input type="hidden" id="status" value="<%=request.getAttribute("status")%>"
    <body>
        <div class="container">
            <div class="btn-back_home">
                <p>
                    <a class="back_home-detail" href="HomeURL"><i class="fa fa-arrow-left" aria-hidden="true"></i> Homepage</a>
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
                    <div class="spacing_top"></div>
                    <form action="LoginURL" method="POST" id="form-1">
                        <input type="hidden" name="go" value="login">
                        <div class="right-head">
                            <h3 class="right-title">Login</h3>
                            <p class="right-desc">Fill your info</p>
                        </div>
                        <div class="form-group">
                            <input id="username" name="username" value="${username==null?'':username}" type="text" placeholder="User Name" class="form-control">
                            <span class="form-message"></span>
                        </div>
                        <div class="form-group">
                            <input id="password" name="password" value="${password==null?'':password}" type="password" placeholder="Password" class="form-control">
                            <span class="form-message"></span><br>
                            <span class=""><a href="forgotPassword">Forgot Password?</a></span>

                        </div>
                        <span class="validate-message">${validate}</span>
                        <div class="form-remember">
                            <input id="remember-account" name="remember-account" type="checkbox" checked value="on" class="check-remember">
                            <label for="remember-account" class="remember-detail"> Remember me</label>
                        </div>
                        <button id="form-submit" class="form-submit">Login</button>
                    </form>
                    <button class="form-submit email-login-button" onclick="redirectToEmailLogin()"><i class="fab fa-google"></i> Login with email</button>
                    <div class="logreg-link">
                        <span>Don't have an account? <a href="RegisterURL">Register now</a></span>
                    </div>
                    <div class="spacing_bottom"></div>
                </div>
                <div class="right-part_space"></div>
            </div>
        </div>
        <script src="assets/js/login.js"></script>
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">
        <script>

                        Validator({
                            form: "#form-1",
                            formGroupSelector: ".form-group",
                            querySelector: ".form-message",
                            rules: [
                                //Validator.isEmail("#email"),
                                Validator.isRequired("#username"),
                                Validator.isRequired("#password")
                            ]
                        });
        </script>
        <script type="text/javascript">
            var status = document.getElementById("status").value;
            if (status == "resetSuccess") {
                swal("Congrats", "Password Reset Successful", "success");
            }else if(status == "resetFailed"){
                swal("Sorry", "Password Reset Failed", "error");
            }
            
        </script>
    </body>

</html>
