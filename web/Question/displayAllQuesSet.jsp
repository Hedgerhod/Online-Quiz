<%-- 
    Document   : displayAllQuesSet
    Created on : Jan 26, 2024, 6:08:15 AM
    Author     : hieul
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "Entity.QuestionSet"%>
<!DOCTYPE html>
<html>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            /*            margin: 10px;*/

            background: #F4F6FC;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            font-size: 28px;
            margin-left: 20px;

        }

        table {
            width: 95%;
            border-collapse: collapse;
            margin-top: 20px;
            margin-left: auto;
            margin-right: auto
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;

        }

        th {
            justify-content: center;
            align-items: center;
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        h2 {
            margin-top: 20px;
        }

        a {
            text-decoration: none;
            color: #007BFF;
        }

        a:hover {
            text-decoration: underline;
        }
        .action-container {
            margin-top: 2rem;
            display: flex;
            gap: 0.5rem;
            margin-left: auto;
            margin-right: auto
        }
        .btn-import {
            background: #fcd980;
            border: 1px solid #fcd980;
            color: black;
            padding: 0.5rem 2rem;
            margin: 0.2rem;
            border-radius: 4px;
        }
        .btn-add {
            background: #fcd980;
            border: 1px solid #fcd980;
            color: black;
            padding: 0.5rem 2rem;
            margin: 0.2rem;
            border-radius: 4px;
        }
        .action-container1 {
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px;
        }

        .btn-edit {
            margin: 10px;
        }
        .btn-delete {
            margin: 10px;
        }


    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>All Question set</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css"/>

    </head>
    <body>
        <%@include file="/Home/header.jsp" %> 

        <h1><span>Set list</span></h1>
        <div class="action-container">
            <p><a href="importQues.jsp" style="color: #007BFF; text-decoration: none; font-weight: bold;" class="btn-import">Import</a></p>
            <p><a href="QuestionSetURL?go=addNewSet" style="color: #007BFF; text-decoration: none; font-weight: bold;" class="btn-add" >Add new Set</a></p>
        </div>

        <!--        <p><a href="importQues.jsp" style="color: #007BFF; text-decoration: none; font-weight: bold;">Import Question</a></p>
                <p><a href="QuestionSetURL?go=addNewSet" style="color: #007BFF; text-decoration: none; font-weight: bold;" >Add a new Set</a></p>
        -->        <table border="1">
            <tr>
                <th>Title</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${data}" var="content">
                <tr>
<!--                    <td>${content.getSetId()}</td>-->
                    <td><a href="QuestionSetURL?go=setDetails&SetId=${content.getSetId()}" >${content.getTitle()}</a></td>
                    <td>
                        <div class="action-container1">
                            <a class="btn-edit" href="EditQuestionSetURL?setId=${content.getSetId()}"><i class="fa-solid fa-pen-to-square"></i>Edit</a>
                        </div>
                    </td>
                    <!--                    <td>
                                            <div class="action-container1">
                                                <a class="btn-edit" href="EditQuestionSetURL?setId=${content.getSetId()}"><i class="fa-solid fa-pen-to-square"></i></a>
                                                <a class="btn-delete" href="QuestionSetURL?go=deleteSet&setId=${content.getSetId()}"><i class="gg-trash"></i></a>
                                            </div>
                                        </td>-->
                </tr>
            </c:forEach>
        </table>

    </body>
    <link href='https://unpkg.com/css.gg@2.0.0/icons/css/trash.css' rel='stylesheet'>
</html>
