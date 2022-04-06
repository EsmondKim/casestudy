<html lang="en">

<head>
    <meta charset ="utf-8">
    <title>Sample Project</title>
</head>

<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <br>
    First Name <input type="text" name="firstName" id="firstNameId" value="${form.firstName}">
    <br>
    Last Name <input type="text" name="lastName" id="lastNameId" value="${form.lastName}">
    <br>
    Password <input type="password" name="password" id="passwordId" value="${form.password}">
    <br>
    Confirm Password <input type="confirmPassword" name="confirmPassword" id="confirmPasswordId" value="${form.password}">
    <br><br>
    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp"/>

