<%-- 
    Document   : UpdateSetting
    Created on : Feb 26, 2024, 1:12:46 AM
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Entity.Role" %>
<%@ page import="Entity.Subject" %>
<%@ page import="Entity.Admin" %>
<%@ page import="Entity.Teacher" %>
<%@ page import="Entity.Student" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : EditAccount
    Created on : Mar 2, 2022, 9:09:03 PM
    Author     : Admin
--%>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Quizlet FPT</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">

            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="UpdateSettingURL" method="post" id="form1" >

                            <div class="modal-header">						
                                <h4 class="modal-title">Update Setting</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>


                            <div class="modal-body">					
                                <div class="form-group">
                                    <label for="username">Name</label>
                                    <input type="text" class="form-control" name="name" id="username" value="${dataa['Name']}" required>
                                    <span class="form-message text-danger" id="username-error"></span>
                                </div>

                                <div class="form-group">
                                    <label for="roleSelect">Type</label>
                                    <select class="form-control" id="typeSelect" name="typeId" required>
                                        <option value="">Select</option>
                                        <c:choose>
                                            <c:when test="${dataa['Type'] == 'Role'}">

                                                <option value="role" selected>Role</option>
                                            </c:when>
                                            <c:when test="${dataa['Type'] == 'Subject'}">

                                                <option value="subject" selected>Subject</option>
                                            </c:when>
                                        </c:choose>
                                    </select>
                                </div>
                                <div class="form-group" id="idField" <c:if test="${dataa['Type'] == 'Role'}">style="display: block;"</c:if>>
                                        <label for="id">ID</label>
                                        <input type="text" class="form-control" name="id" id="id" value="${dataa['Id']}" readonly>
                                    <span class="form-message text-danger" id="id-error"></span>
                                </div>

                                <div class="form-group" id="subjectCodeField" <c:if test="${dataa['Type'] == 'Role'}">style="display: none;"</c:if>>
                                        <label for="subjectCode">Subject Code</label>
                                        <input type="text" class="form-control" name="subjectCode" id="subjectCode" value="${dataa['Value']}">
                                    <span class="form-message text-danger" id="subjectCode-error"></span>
                                </div>

                            </div>

                            <div class="modal-footer">
                                <a href="SettingControllerURL"> <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button></a> 
                                <button type="submit" class="btn btn-success">Update</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>



    </body>
</html>
<script>

    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("typeSelect").addEventListener("change", function () {
            var selectedType = this.value;
            var idField = document.getElementById("idField");
            var subjectCodeField = document.getElementById("subjectCodeField");
            if (selectedType === "role") {
                idField.style.display = "block";
                subjectCodeField.style.display = "none";
            } else if (selectedType === "subject") {
                idField.style.display = "none";
                subjectCodeField.style.display = "block";
            } else {
                idField.style.display = "none";
                subjectCodeField.style.display = "none";
            }
        });

        // Ẩn trường ID khi trang được tải nếu loại mặc định là Subject
        var defaultType = document.getElementById("typeSelect").value;
        if (defaultType === "subject") {
            document.getElementById("idField").style.display = "none";
        }
    });


</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var nameInput = document.getElementById("username");
        var subjectCodeInput = document.getElementById("subjectCode");
        var form = document.getElementById("form1");
        var nameErrorSpan = document.getElementById("username-error");
        var subjectCodeErrorSpan = document.getElementById("subjectCode-error");
        form.addEventListener("submit", function (event) {
            if (!isValidName(nameInput.value)) {
                nameErrorSpan.textContent = "Name cannot contain digits or special characters.";
                event.preventDefault();
            } else {
                nameErrorSpan.textContent = "";
            }
            if (!isValidSubjectCode(subjectCodeInput.value)) {
                subjectCodeErrorSpan.textContent = "Subject Code cannot contain special characters.";
                event.preventDefault();
            } else {
                subjectCodeErrorSpan.textContent = "";
            }
        });
        function isValidName(name) {
            return /^[a-zA-Z\s]*$/.test(name);
        }
        function isValidSubjectCode(subjectCode) {
            return /^[a-zA-Z0-9]*$/.test(subjectCode);
        }
    });
</script>
