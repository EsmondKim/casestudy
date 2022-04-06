<html lang="en">

<head>
    <meta charset ="utf-8">
    <title>Sample Project</title>
</head>

<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../include/header.jsp"/>

<div class="container">

    <c:if test="${empty form.id}">
        <h1>Sign Up</h1>
    </c:if>

    <c:if test="${not empty form.id}">
    <h1>Edit User</h1>
    </c:if>

        <form action="/user/registerSubmit" method="post">
    <input type="hidden" name="id" value="${form.id}">

    Email <input type="email" name="email" id="emailId" value="${form.email}">
        <c:forEach items='${bindingResult.getFieldErrors("email")}' var="error">
            <div style="color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>
    <br>
    First Name <input type="text" name="firstName" id="firstNameId" value="${form.firstName}">
        <c:forEach items='${bindingResult.getFieldErrors("firstName")}' var="error">
            <div style="color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>
    <br>
    Last Name <input type="text" name="lastName" id="lastNameId" value="${form.lastName}">
        <c:forEach items='${bindingResult.getFieldErrors("lastName")}' var="error">
            <div style="color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>
    <br>
    Password <input type="password" name="password" id="passwordId" value="${form.password}">
        <c:forEach items='${bindingResult.getFieldErrors("password")}' var="error">
            <div style="color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>
    <br>
    Confirm Password <input type="confirmPassword" name="confirmPassword" id="confirmPasswordId" value="${form.password}">
        <c:forEach items='${bindingResult.getFieldErrors("confirmPassword")}' var="error">
            <div style="color:red;">${error.getDefaultMessage()}</div>
        </c:forEach>
    <br>
    Check Box <input type="checkbox" name="checkbox">
    <br>
    <button type="submit">Submit</button>
    </form>
<%--        <c:if test="${bindingResult.hasErrors()}">--%>
<%--            <br>--%>

<%--            <c:forEach items="${bindingResult.getAllErrors}" var="error">--%>
<%--            <div style="color:red;">${error.getDefaultMessage()}</div>--%>
<%--            </c:forEach>--%>
<%--        </c:if>--%>

<jsp:include page="../include/footer.jsp"/>

