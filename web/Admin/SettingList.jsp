<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Entity.Role" %>
<%@ page import="Entity.Subject" %>
<%@ page import="Entity.Admin" %>
<%@ page import="Entity.Teacher" %>
<%@ page import="Entity.Student" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/Class/images/quiz.png">
        <title> User Management Data Table</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;

            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background: #299be4;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #566787;
                float: right;
                font-size: 13px;
                background: #fff;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                color: #566787;
                background: #f2f2f2;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.settings {
                color: #2196F3;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .status {
                font-size: 30px;
                margin: 2px 2px 0 0;
                display: inline-block;
                vertical-align: middle;
                line-height: 10px;
            }
            .text-success {
                color: #10c469;
            }
            .text-info {
                color: #62c9e8;
            }
            .text-warning {
                color: #FFC107;
            }
            .text-danger {
                color: #ff5b5b;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li .active {
                background-color: #666; /* Background color of the active page */
            }
            /*            .pagination li.active a.page-link {
                            background: #03A9F4;
                            color: white;
                        }*/
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                background-color: #007bff;
                color: white;

            }
            /*            .pagination li.active a, .pagination li.active a.page-link {
                            background: #03A9F4;
                        }
                        .pagination li.active a:hover {
                            background: #0397d6;
                        }
                        .pagination li.disabled i {
                            color: #ccc;
                        }
                        .pagination li i {
                            font-size: 16px;
                            padding-top: 6px
                        }*/
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }
            th div {
                display: inline-block;
                margin-left: 5px;
            }
            .custom-toast {
                width: 300px;
                position: absolute;
                z-index: 1000;
                top: 50px;
                right: 20px;
            }
            #idField, #subjectCodeField {
                display: none;
            }

        </style>
        <script>
            $(document).ready(function () {

                var userNameSortOrder = 'asc';
                var roleSortOrder = 'asc';

                $('.fa-sort').click(function () {

                    var columnIdx = $(this).closest('th').index();

                    var rows = $('table tbody tr').get();

                    rows.sort(function (a, b) {
                        var A = $(a).children('td').eq(columnIdx).text().toUpperCase();
                        var B = $(b).children('td').eq(columnIdx).text().toUpperCase();

                        if (columnIdx === 1) { // s?p x?p theo tên ng??i dùng
                            if (userNameSortOrder === 'asc') {
                                return A < B ? -1 : A > B ? 1 : 0;
                            } else {
                                return A > B ? -1 : A < B ? 1 : 0;
                            }
                        } else if (columnIdx === 4) { // s?p x?p theo vai trò
                            if (roleSortOrder === 'asc') {
                                return A < B ? -1 : A > B ? 1 : 0;
                            } else {
                                return A > B ? -1 : A < B ? 1 : 0;
                            }
                        } else {
                            return 0;
                        }
                    });

                    $.each(rows, function (index, row) {
                        $('table').children('tbody').append(row);
                    });

                    if (columnIdx === 1) {
                        userNameSortOrder = userNameSortOrder === 'asc' ? 'desc' : 'asc';
                    } else if (columnIdx === 4) {
                        roleSortOrder = roleSortOrder === 'asc' ? 'desc' : 'asc';
                    }

                    $('.fa-sort').removeClass('fa-sort-up fa-sort-down');

                    if (columnIdx === 1) {
                        if (userNameSortOrder === 'asc') {
                            $(this).removeClass('fa-sort').addClass('fa-sort-up');
                            $(this).siblings('.fa-sort').removeClass('fa-sort-up fa-sort-down').addClass('fa-sort');
                        } else {
                            $(this).removeClass('fa-sort').addClass('fa-sort-down');
                            $(this).siblings('.fa-sort').removeClass('fa-sort-up fa-sort-down').addClass('fa-sort');
                        }
                    } else if (columnIdx === 4) {
                        if (roleSortOrder === 'asc') {
                            $(this).removeClass('fa-sort').addClass('fa-sort-up');
                            $(this).siblings('.fa-sort').removeClass('fa-sort-up fa-sort-down').addClass('fa-sort');
                        } else {
                            $(this).removeClass('fa-sort').addClass('fa-sort-down');
                            $(this).siblings('.fa-sort').removeClass('fa-sort-up fa-sort-down').addClass('fa-sort');
                        }
                    }
                });
            });

        </script>
        <!-- JavaScript -->
        <script>
            $(document).ready(function () {
                // Activate tooltip
                $('[data-toggle="tooltip"]').tooltip();

                // Select/Deselect checkboxes
                var checkbox = $('table tbody input[type="checkbox"]');
                $("#selectAll").click(function () {
                    if (this.checked) {
                        checkbox.each(function () {
                            this.checked = true;
                        });
                    } else {
                        checkbox.each(function () {
                            this.checked = false;
                        });
                    }
                });
                checkbox.click(function () {
                    if (!this.checked) {
                        $("#selectAll").prop("checked", false);
                    }
                });

                // Show add employee modal
                $('a[href="#addEmployeeModal"]').click(function () {
                    $("#addEmployeeModal").modal('show');
                });
                $('a[href="#updateEmployeeModal"]').click(function () {
                    $("#updateEmployeeModal").modal('show');
                });
            });
            $(document).ready(function () {
                $('#statusDropdown, #roleDropdown, #searchInput').change(function () {
                    $('#filterForm').submit();
                });
            });

        </script>


    <div id="myToast" class="toast custom-toast bg-danger text-white" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="toast-header">
            Thông Báo!!!!
            <button type="button" class="ml-auto mb-1 close" data-dismiss="toast" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="toast-body">
            <c:out value="${sessionScope.messagee}" />
        </div>
    </div>



</head>
<body>

    <div class="container-xl">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title" style="background-color:  #4257B4;">
                    <div class="row">
                        <div class="col-sm-6">
                            <p>
                                <a class="back_home-detail" href="HomeController" style="color: white;"><i class="fa fa-arrow-left" aria-hidden="true"></i> Homepage</a>
                            </p>
                            <a href="SettingControllerURL" style="color: white;"> <h2>Setting <b>List</b></h2></a>
                            <%-- N?u session có thông báo, hi?n th? c?a s? thông báo --%>



                        </div>
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal" class="btn btn-secondary"><i class="material-icons">&#xE147;</i> <span>Add New Setting</span></a>

                        </div>
                    </div>
                </div>
                <div id="addEmployeeModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="AddSettingURL" method="get" id="form1" onsubmit="return validateForm()">


                                <div class="modal-header">						
                                    <h4 class="modal-title">Add New Setting</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>


                                <div class="modal-body">					


                                    <div class="form-group">
                                        <label for="roleSelect">Type</label>
                                        <select class="form-control" id="typeSelect" name="typeId" required>
                                            <option value="">Select</option>
                                            <option value="role">Role</option>
                                            <option value="subject">Subject</option>

                                        </select>
                                    </div>
                                    <div class="form-group" id="idField">
                                        <label for="id">ID</label>
                                        <input type="text" class="form-control" name="id" id="id">
                                        <span class="form-message text-danger" id="id-error"></span>
                                    </div>



                                    <div class="form-group" id="subjectCodeField">
                                        <label for="subjectCode">Subject Code</label>
                                        <input type="text" class="form-control" name="subjectCode" id="subjectCode">
                                        <span class="form-message text-danger" id="subjectCode-error"></span>
                                    </div>

                                    <div class="form-group">
                                        <label for="username">Name</label>
                                        <input type="text" class="form-control" name="name" id="username" required>
                                        <span class="form-message text-danger" id="username-error"></span>
                                    </div>

                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-success">Add</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>


                <div class="row align-items-center" style="padding-bottom: 15px;">
                    <form id="filterForm" class="col-sm-8" action="FilterSetting" method="">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="statusDropdown">Type</label>
                                <select id="statusDropdown" name="type">
                                    <option value="all" <% if ("all".equals(request.getParameter("type"))) { %> selected <% } %>>All</option>
                                    <option value="role" <% if ("role".equals(request.getParameter("type"))) { %> selected <% } %>>Role</option>
                                    <option value="subject" <% if ("subject".equals(request.getParameter("type"))) { %> selected <% } %>>Subject</option>
                                </select>
                            </div>
                            <div class="col-sm-6">

                            </div>
                        </div>
                    </form>
                    <div class="col-sm-4">
                        <form action="SearchSettingURL" method="">
                            <div class="search-box">
                                <label for="searchInput">Search</label>
                                <input type="text" value="${txt}" id="searchInput" name="txt" placeholder="Search...">
                            </div>
                        </form>
                    </div>
                </div>







                <c:if test="${empty sessionScope.messageee}">
                    <table class="table table-striped table-hover">

                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Name
                                    <i class="fa fa-sort"></i></th>
                                <th>Type</th>
                                <th>Value</th>
                                <th>Id </th>
                                <th>Action</th>


                            </tr>
                        </thead>
                        <tbody>


                            <c:forEach var="setting" items="${data}" varStatus="status">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td>${setting['Name']}</td>
                                    <td>${setting['Type']}</td>
                                    <td>${setting['Value']}</td>
                                    <td>${setting['Order']}</td>
                                    <td>
                                        <c:url value="UpdateSettingURL" var="updateUrl">
                                            <c:param name="sid" value="${setting['Order']}" />
                                            <c:param name="type" value="${setting['Type']}" />
                                        </c:url>

                                        <c:url value="DeleteSettingURL" var="deleteUrl">
                                            <c:param name="sid" value="${setting['Order']}" />
                                            <c:param name="type" value="${setting['Type']}" />
                                        </c:url>

                                        <a href="${updateUrl}" class="settings" title="Settings" data-toggle="tooltip">
                                            <i class="material-icons">&#xE8B8;</i>
                                        </a>
                                        <a href="${deleteUrl}" class="delete" title="Delete" data-toggle="tooltip" onclick="return confirm('Do you want delete?');">
                                            <i class="material-icons" style="color: red;">&#xE5C9;</i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>   
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${not empty sessionScope.messageee}">
                    <div class="alert alert-danger">${sessionScope.messageee}</div>
                    <c:remove var="sessionScope.messageee" />
                </c:if>


                <c:if test="${empty sessionScope.messageee}">
                    <div class="clearfix">
                        <div class="hint-text">Page <b>${page}</b>of <b>${totalPage}</b> total pages</div>
                        <ul class="pagination">
                            <li class="page-item ">
                                <a href="${requestScope.Ur}page=${page-1}" class="page-link">Previous</a>
                            </li>
                            <c:forEach begin="1" end="${totalPage}" var="i">
                                <li class="page-item ${page==i ? "active" : ""}">

                                    <a href="${requestScope.Ur}page=${i}" class="page-link">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <a href="${requestScope.Ur}page=${page+1}" class="page-link">Next</a>
                            </li>
                        </ul>

                    </div>
                </c:if>
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
    });

    $(document).ready(function () {
        // Hi?n th? toast n?u session ch?a thông báo
        var message = "<c:out value='${sessionScope.messagee}' />";
        if (message.trim() !== "") {
            $('.toast').toast('show');
        }
    });
    setTimeout(function () {
        $('#myToast').toast('hide');
    }, 5000); // 2 giây

</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // L?y các ph?n t? c?n ki?m tra
        var nameInput = document.getElementById("username");
        var idInput = document.getElementById("id");
        var subjectCodeInput = document.getElementById("subjectCode");
        var form = document.getElementById("form1");
        var nameErrorSpan = document.getElementById("username-error");
        var idErrorSpan = document.getElementById("id-error");
        var subjectCodeErrorSpan = document.getElementById("subjectCode-error");

        // Thêm s? ki?n submit cho form
        form.addEventListener("submit", function (event) {
            // Ki?m tra ?i?u ki?n cho tr??ng Name
            if (!isValidName(nameInput.value)) {
                nameErrorSpan.textContent = "Name cannot contain digits or special characters.";
                event.preventDefault(); // Ng?n ch?n vi?c submit form n?u ?i?u ki?n không h?p l?
            } else {
                nameErrorSpan.textContent = ""; // Xóa thông báo l?i n?u ?i?u ki?n h?p l?
            }

            // Ki?m tra ?i?u ki?n cho tr??ng ID
            if (!isValidId(idInput.value)) {
                idErrorSpan.textContent = "ID must contain only digits.";
                event.preventDefault(); // Ng?n ch?n vi?c submit form n?u ?i?u ki?n không h?p l?
            } else {
                idErrorSpan.textContent = ""; // Xóa thông báo l?i n?u ?i?u ki?n h?p l?
            }

            // Ki?m tra ?i?u ki?n cho tr??ng Subject Code
            if (!isValidSubjectCode(subjectCodeInput.value)) {
                subjectCodeErrorSpan.textContent = "Subject Code cannot contain special characters.";
                event.preventDefault(); // Ng?n ch?n vi?c submit form n?u ?i?u ki?n không h?p l?
            } else {
                subjectCodeErrorSpan.textContent = ""; // Xóa thông báo l?i n?u ?i?u ki?n h?p l?
            }
        });

        // Hàm ki?m tra tên h?p l?
        function isValidName(name) {
            return /^[a-zA-Z\s]*$/.test(name); // Ki?m tra xem name ch? ch?a ch? cái và kho?ng tr?ng
        }

        // Hàm ki?m tra ID h?p l?
        function isValidId(id) {
            return /^[0-9]*$/.test(id); // Ki?m tra xem ID ch? ch?a s?
        }

        // Hàm ki?m tra Subject Code h?p l?
        function isValidSubjectCode(subjectCode) {
            return /^[a-zA-Z0-9]*$/.test(subjectCode); // Ki?m tra xem subjectCode ch? ch?a ch? cái và s?
        }
    });
</script>
