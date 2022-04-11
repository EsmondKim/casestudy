
<jsp:include page="../include/header.jsp"/>
<body>

<h1>This is a Login page.</h1>

<form action="/login/loginSubmit" method="POST">
    <input type="hidden" name="id" value="${form.id}">
        Username : <input type="text" name="username">
        <br>
        Password : <input type="text" name="password">
    <br>
    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp"/>
