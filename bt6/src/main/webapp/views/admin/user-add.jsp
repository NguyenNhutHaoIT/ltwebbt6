<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">

<h3>Thêm User</h3>

<form action="${pageContext.request.contextPath}/admin/insert-users" method="post">
    <div class="mb-3">
        <label class="form-label">Username</label>
        <input type="text" name="username" class="form-control" required/>
    </div>

    <div class="mb-3">
        <label class="form-label">Password</label>
        <input type="password" name="password" class="form-control" required/>
    </div>

    <div class="mb-3">
        <label class="form-label">RoleId</label>
        <input type="number" name="roleid" class="form-control" required/>
    </div>

    <button class="btn btn-success">Thêm mới</button>
    <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-secondary">Hủy</a>
</form>

</body>
</html>
