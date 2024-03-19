<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title>Quizlet FPT</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
                integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
                integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>

        <style>
            .selected {
                background-color: #007bff;
                color: #fff;
            }
        </style>
    </head>

    <body>

        <form class="filter-dialog">
            <i class="fa-solid fa-filter"></i> <strong>Filters </strong>
            <p class="filter-group-title"></p>
            <ul class="filter-group">
                <li>
                    <input class="d-none" type="radio" name="category" value="-1" id="cat-choice--1"
                           onclick="selectFilter(event)" ${empty param.category || param.category == -1 ? "checked" : ""} />
                    <label for="cat-choice--1">All</label>
                </li>

                <li class="mb-1">
                    <button class="btn d-inline-flex align-items-center rounded" data-bs-toggle="collapse"
                            data-bs-target="#subject-code-collapse" aria-expanded="false" aria-controls="subject-code-collapse"
                            onclick="toggleCustomize(event, 'subject-code-collapse')">
                        SubjectCode
                    </button>
                    <div class="collapse" id="subject-code-collapse">
                        <ul class="list-unstyled fw-normal pb-1 small">
                            <c:forEach var="item" items="${SubNav}">
                                <li><a href="SearchSubject?SubjectId=${item.getSubjectId()}"
                                       class="d-inline-flex align-items-center rounded"
                                       style="text-decoration: none"
                                       onclick="selectSubject(event)">${item.getSubjectCode()}</a></li>
                                </c:forEach>
                        </ul>
                    </div>>
                </li>
            </ul>
        </form>

        <script>
            function toggleCustomize(event, collapseId) {
                // Ngăn chặn sự kiện mặc định của nút
                event.preventDefault();
                $(`#${collapseId}`).css('height', '');
                $(`#${collapseId}`).collapse('toggle');
            }

            function selectSubject(event) {
                const allSubjects = document.querySelectorAll('.list-unstyled .d-inline-flex');
                allSubjects.forEach(subject => {
                    subject.classList.remove('selected');
                });
                event.currentTarget.classList.add('selected');
            }
        </script>

    </body>

</html>
