<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Flashcards</title>
        <style>
            .front {
                backface-visibility: hidden;
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                margin: auto; /* Center the card vertically and horizontally */
                text-align: center; /* Center the text horizontally */
                display: flex;
                justify-content: center; /* Center content horizontally */
                align-items: center; /* Center content vertically */
            }
            .container {
                height: 320px;
                width: 600px;
                margin: auto;
                position: absolute;
                top: 0;
                bottom: 0;
                left: 0;
                right: 0;
            }

            .flip-container {
                perspective: 1000px;
                margin: 10px;
                float: left;
                cursor: pointer;
            }

            .flippable {
                transition: 0.5s;
                transform-style: preserve-3d;
                position: relative;
            }

            .flipme {
                transform: rotateY(180deg);
            }

            .flip-container,
            .front,
            .back {
                width: 600px; /* Increase width */
                height: 300px; /* Increase height */
                line-height: 50px; /* Match height for vertical centering */
                text-align: center;
                font-size: 30px;
                border-radius: 5px;
                vertical-align: middle; /* Align text vertically */
            }

            .front,
            .back {
                backface-visibility: hidden;
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                margin: auto; /* Center the card vertically and horizontally */
                text-align: center;
                border-radius: 10px;
                border: #0b67a3 solid thick;
                font-family: Arial, sans-serif;
            }

            .front i {
                font-size: 20px;
                font-family: verdana;
            }

            .back {
                transform: rotateY(180deg);
                font-size: 18px;
                border: 1px solid;
                border: #0b67a3 solid thick;
                color: white;
            }

            .ac-bicycle .front {
                background: #1189d1;
            }

            .ac-bicycle .back {
                background: #1189d1;
                border-color: #30aee6;
            }

            .button-container {
                text-align: center;
                margin-top: 20px;
            }

            .button {
                display: inline-block;
                padding: 10px 20px;
                background-color: #1189d1;
                color: white;
                font-size: 16px;
                border-radius: 10px;
                cursor: pointer;
                transition: background-color 0.3s;
                margin-right: 10px;
            }

            .button:hover {
                background-color: #0b67a3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="flip-container" ontouchstart="this.classList.toggle('hover');">
                <div class="flippable ac-bicycle" >

                    <c:forEach items="${question}" var="ques" varStatus="status">


                        <div class="front">
                            <div style="display: flex; flex-direction: column;">
                                <i>${ques.getContent()}</i>&nbsp;
                                <c:forEach items="${content[status.index]}" var="answer">
                                    ${answer.getContent()}
                                </c:forEach>
                            </div>
                        </div>
                        <div class="back" style="display: flex; justify-content: center; align-items: center; font-family: Arial, sans-serif;">
                            <c:forEach items="${content[status.index]}" var="answer">
                                <c:if test="${answer.isCorrect() == true}">
                                    <i class="Correct">${answer.getContent()}</i>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>


            <div class="button-container">
                <div class="button" id="returnButton">Return</div>
                <div class="button" id="nextButton">Next</div>

            </div>

        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".flippable").click(function () {
                    $(this).toggleClass("flipme");
                });

                $("#nextButton").click(function () {
                    // Add logic to move to the next question
                });

                $("#returnButton").click(function () {
                    // Add logic to return to the previous question
                });
            });
        </script>
    </body>
</html>
