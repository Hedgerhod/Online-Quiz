<%-- 
    Document   : searchBar
    Created on : Jan 20, 2024, 5:01:05 PM
    Author     : Asus
--%>

<%-- any content can be specified here e.g.: --%>
<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    .search {
        margin-bottom: 50px; /* Add bottom margin */
    }

    .styled-form {
        display: flex;
        border: 2px solid #ccc;
        border-radius: 5px;
        overflow: hidden;
        width: 500px; /* Increase width to 500px */
    }

    .styled-form input[type="search"] {
        flex: 1;
        padding: 10px; /* Increase padding for more height */
        border: none;
        outline: none;
        width: 80%;
    }

    .styled-form input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        padding: 10px 15px; /* Adjust padding for more height */
        border: none;
        cursor: pointer;
        width: 20%;
    }
</style>
<div class="search">
    <form action="search" method="get" class="styled-form">
        <input type="search" name="txt" value="${key}" placeholder="Search" onkeydown="handleSearch(event)">
        <input type="submit" value="Search">
    </form>
</div>
<script>
    function handleSearch(e) {
        if (e.key === "Enter") {
            const target = e.target;
            const name = target.getAttribute("name");
            const value = target.value;
            applyFilter(name, value);
        }
    }
</script>
