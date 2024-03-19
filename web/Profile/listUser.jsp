
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="Entity.User" %>
<%@ page import="Entity.Admin" %>
<%@ page import="Entity.Teacher" %>
<%@ page import="Entity.Student" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Quizlet FPT</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>AccountID

                    </th>
                    <th>UserName
                        <i class="fa fa-sort"></i>
                    </th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Role
                        <i class="fa fa-sort"></i>
                    </th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${data}">

                    <tr data-status="<c:out value="${user.active ? 'active' : 'suspended'}" />">
                        <td>${user['AccountId']}</td>
                        <td>${user['Username']}</td>
                        <td>${user['Email']}</td>
                        <td>${user['Phone']}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user['RoleId'] == 1}">
                                    Admin
                                </c:when>
                                <c:when test="${user['RoleId'] == 2}">
                                    Teacher
                                </c:when>
                                <c:when test="${user['RoleId'] == 3}">
                                    Student
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${user['IsActive'] == true}">
                                    <span class="status text-success">&bull;</span> Active
                                </c:when>
                                <c:otherwise>
                                    <span class="status text-danger">&bull;</span> Suspended
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="UdProfilebyAdminURL?sid=${user['AccountId']}" class="settings" title="Settings" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>
                            <a href="DeleteUserURL?sid=${user['AccountId']}" class="delete" title="Delete" data-toggle="tooltip" onclick="return confirm('Do you want delete?');">
                                <i class="material-icons" style="color: red;">&#xE5C9;</i>
                            </a>

                        </td>
                    </tr>
                </c:forEach>   
            </tbody>
        </table>
    </body>
</html>

