<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset ="utf-8">
    <title>Sample Project</title>
</head>

<script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<body>

<div class="container">

    <a href="/index">Index</a> &nbsp;  | &nbsp;|
    <a href="/ajax">AJAX Example</a> &nbsp;  | &nbsp;|
    <a href="/user/register">Sign Up</a> &nbsp;  | &nbsp;|
    <a href="/upload">Upload File</a> &nbsp;  | &nbsp;|
<%--    <a href="/user/search">Search</a> &nbsp;  | &nbsp;|--%>

    <sec:authorize access = "hasAuthority('ADMIN')">
        <a href="/user/search">Search</a>  &nbsp;  | &nbsp;|
    </sec:authorize>

    <sec:authorize access="!isAuthenticated()">
        <a href="/login/login">Login</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="/login/login">Logout</a> &nbsp;  | &nbsp;|
        <sec:authentication property="principal.username" />
    </sec:authorize>


    <hr>