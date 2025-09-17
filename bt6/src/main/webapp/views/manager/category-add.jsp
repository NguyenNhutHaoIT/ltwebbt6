<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">

<h3>Thêm Category</h3>

<form action="${pageContext.request.contextPath}/manager/insert" method="post" enctype="multipart/form-data">
    <div class="mb-3">
        <label class="form-label">Tên Category</label>
        <input type="text" name="categoryname" class="form-control" required/>
    </div>
    <div class="mb-3">
        <label class="form-label">Icon</label>
        <input type="file" name="icon" class="form-control" required/>
    </div>
    <button class="btn btn-success">Thêm mới</button>
    <a href="${pageContext.request.contextPath}/manager/home" class="btn btn-secondary">Hủy</a>
</form>

</body>
</html>
