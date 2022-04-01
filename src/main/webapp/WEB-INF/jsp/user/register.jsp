<html lang="en">

<head>
    <meta charset ="utf-8">
    <title>Sample Project</title>
</head>

<body>

<jsp:include page="../include/header.jsp"/>

<div class="container">

<h1>Sign Up</h1>
<form action="/user/registerSubmit" method="post">
    Email <input type="email" name="email" id="emailId">
    <br>
    First Name <input type="text" name="firstName" id="firstNameId">
    <br>
    Last Name <input type="text" name="lastName" id="lastNameId">
    <br>
    Password <input type="password" name="password" id="passwordId">
    <br>
    Confirm Password <input type="confirmPassword" name="confirmPassword" id="confirmPasswordId">
    <br><br>
    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp"/>

