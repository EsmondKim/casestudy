
<jsp:include page="./include/header.jsp"/>

<h1>File Upload</h1>

<form action="/upload" method="POST" enctype="multipart/form-data">

    Subject : <input type="text" name="subject">
    <br>
    Select File : <input type="file" name="file">
    <br>
    <button type="submit">Submit</button>
</form>


<jsp:include page="./include/footer.jsp"/>

