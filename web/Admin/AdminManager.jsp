
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Entity.User" %>
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
                            <a href="ManagerUserURL" style="color: white;"> <h2>User <b>List</b></h2></a>
                        </div>
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal" class="btn btn-secondary"><i class="material-icons">&#xE147;</i> <span>Add New User</span></a>

                        </div>
                    </div>
                </div>
                <div id="addEmployeeModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form action="AddUserURL" method="get" id="form1">

                                <div class="modal-header">						
                                    <h4 class="modal-title">Add New User</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>


                                <div class="modal-body">					
                                    <div class="form-group">
                                        <label for="username">UserName</label>
                                        <input type="text" class="form-control" name="username" id="username" required>
                                        <span class="form-message text-danger" id="username-error"></span>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="email" class="form-control" name="email" id="email" required>
                                        <c:if test="${not empty sessionScope.error}">

                                            <span class="form-message text-danger">${sessionScope.error}</span>

                                        </c:if>

                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" class="form-control" id="password" name="password" required>
                                        <span class="form-message text-danger" id="password-error"></span>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Phone</label>
                                        <input type="text" class="form-control" name="phone" id="phone" required>
                                        <span class="form-message text-danger" id="phone-error"></span>
                                    </div>

                                    <div class="form-group">
                                        <label for="roleSelect">RoleID</label>
                                        <select class="form-control" id="roleSelect" name="roleId" required>
                                            <option value="">Select</option>
                                            <option value="3">Admin</option>
                                            <option value="2">Teacher</option>
                                            <option value="1">Student</option>
                                        </select>
                                    </div>

                                    <div class="form-group additionalFields adminFields" style="display: none;">
                                        <label for="adminName">AdminName</label>
                                        <input type="text" class="form-control" name="adminName" id="adminName">
                                    </div>

                                    <div class="form-group additionalFields teacherFields" style="display: none;">
                                        <label for="teacherName">TeacherName</label>
                                        <input type="text" class="form-control" name="teacherName" id="teacherName">
                                    </div>

                                    <div class="form-group additionalFields studentFields" style="display: none;">
                                        <label for="studentName">Student Name</label>
                                        <input type="text" class="form-control" name="studentName" id="studentName">
                                    </div>

                                    <div class="form-group additionalFields studentFields" style="display: none;">
                                        <label for="studentDOB">Date of Birth</label>
                                        <input type="date" class="form-control" name="studentDOB" id="studentDOB">
                                    </div>

                                    <div class="form-group">
                                        <label for="statusSelect">Status</label>
                                        <select class="form-control" id="statusSelect" name="status" required>
                                            <option value="">Select</option>
                                            <option value="1">Active</option>
                                            <option value="0">Suspended</option>
                                        </select>
                                    </div>

                                </div>
<!--                                    <span class="error_msg text-danger">${Email_DUPP}</span>-->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-success">Add</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>






                <div class="row align-items-center" style="padding-bottom: 15px;">
                    <form id="filterForm" class="col-sm-8" action="ManagerUserURL" method="post">
                        <div class="row">
                            <div class="col-sm-6">
                                <label for="statusDropdown">Status</label>
                                <select id="statusDropdown" name="status">
                                    <option value="all" <% if ("all".equals(request.getParameter("status"))) { %> selected <% } %>>All</option>
                                    <option value="active" <% if ("active".equals(request.getParameter("status"))) { %> selected <% } %>>Active</option>
                                    <option value="suspended" <% if ("suspended".equals(request.getParameter("status"))) { %> selected <% } %>>Suspended</option>
                                </select>
                            </div>
                            <div class="col-sm-6">
                                <label for="roleDropdown">Role</label>
                                <select id="roleDropdown" name="role">
                                    <option value="all" <% if ("all".equals(request.getParameter("role"))) { %> selected <% } %>>All</option>
                                    <option value="3" <% if ("3".equals(request.getParameter("role"))) { %> selected <% } %>>Admin</option>
                                    <option value="2" <% if ("2".equals(request.getParameter("role"))) { %> selected <% } %>>Teacher</option>
                                    <option value="1" <% if ("1".equals(request.getParameter("role"))) { %> selected <% } %>>Student</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <div class="col-sm-4">
                        <form action="SearchUserURL" method="get">
                            <div class="search-box">
                                <label for="searchInput">Search</label>
                                <input type="text" value="${txt}" id="searchInput" name="txt" placeholder="Search...">
                            </div>
                        </form>
                    </div>
                </div>







                <c:if test="${empty sessionScope.messageeee}">
                    <table class="table table-striped table-hover">
                        <caption>List of Users</caption>
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
                                            <c:when test="${user['RoleId'] == 3}">
                                                Admin
                                            </c:when>
                                            <c:when test="${user['RoleId'] == 2}">
                                                Teacher
                                            </c:when>
                                            <c:when test="${user['RoleId'] == 1}">
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
                </c:if>
                <c:if test="${not empty sessionScope.messageeee}">
                    <div class="alert alert-danger">${sessionScope.messageeee}</div>
                    <c:remove var="sessionScope.messageee" />
                </c:if>

                <c:if test="${empty sessionScope.messageeee}">
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>${page}</b> of <b>${totalPage}</b> total page</div>
                        <ul class="pagination">
                            <li class="page-item ">
                                <a href="${requestScope.Url}page=${page-1}" class="page-link">Previous</a>
                            </li>
                            <c:forEach begin="1" end="${totalPage}" var="i">
                                <li class="page-item ${page==i ? "active" : ""}">

                                    <a href="${requestScope.Url}page=${i}" class="page-link">${i}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <a href="${requestScope.Url}page=${page+1}" class="page-link">Next</a>
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
    document.getElementById('statusDropdown').addEventListener('change', function () {
        document.getElementById('filterForm').submit();
    });

    document.getElementById('roleDropdown').addEventListener('change', function () {
        document.getElementById('filterForm').submit();
    });
    $(document).ready(function () {
        $('#roleSelect').change(function () {
            var selectedRole = $(this).val();
            if (selectedRole == '3') {
                $('.adminFields').show();
            } else {
                $('.adminFields').hide();
            }
        });
    });
    $(document).ready(function () {
        $('#roleSelect').change(function () {
            var selectedRole = $(this).val();
            if (selectedRole == '2') {
                $('.teacherFields').show();
            } else {
                $('.teacherFields').hide();
            }
        });
    });

    $(document).ready(function () {
        $('#roleSelect').change(function () {
            var selectedRole = $(this).val();
            if (selectedRole == '1') {
                $('.studentFields').show();
            } else {
                $('.studentFields').hide();
            }
        });
    });



    document.getElementById('form1').addEventListener('submit', function (event) {
        var phoneInput = document.getElementById('phone');
        var phoneValue = phoneInput.value.trim();
        var phoneError = document.getElementById('phone-error');

        var passwordInput = document.getElementById('password');
        var passwordValue = passwordInput.value.trim();
        var passwordError = document.getElementById('password-error');

        var usernameInput = document.getElementById('username');
        var usernameValue = usernameInput.value.trim();
        var usernameError = document.getElementById('username-error');


        phoneError.innerHTML = '';
        passwordError.innerHTML = '';
        usernameError.innerHTML = '';


        if (phoneValue === '') {
            phoneError.innerHTML = 'Phone number is required.';
            event.preventDefault();
        } else if (phoneValue.length !== 10) {
            phoneError.innerHTML = 'Phone number must be 10 digits.';
            event.preventDefault();
        } else if (!(/^\d+$/.test(phoneValue))) {
            phoneError.innerHTML = 'Phone number must contain only digits.';
            event.preventDefault();
            t
        }

        // Ki?m tra ?? dài c?a m?t kh?u
        if (passwordValue.length < 8 || passwordValue.length > 32) {
            passwordError.innerHTML = 'Password must be between 8 and 32 characters.';
            event.preventDefault(); // Ng?n form submit
        }


        var regex = /^[a-zA-Z0-9]+$/;
        if (!regex.test(usernameValue)) {
            usernameError.innerHTML = 'Username must contain only letters and numbers.';
            event.preventDefault(); // 
        }
    });

</script>
<script>
    $(document).ready(function () {
        // Hi?n th? toast n?u session ch?a thông báo
        var messagee = "<c:out value='${sessionScope.messagee}' />";
        if (messagee.trim() !== "") {
            $('.toast').toast('show');
        }
    });
    setTimeout(function () {
        $('#myToast').toast('hide');
    }, 6000); // 2 giây
</script>






