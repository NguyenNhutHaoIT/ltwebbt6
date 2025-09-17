<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body class="container mt-4">

<h3>Sửa User</h3>

<form action="${pageContext.request.contextPath}/admin/edit-users" method="post">
    <input type="hidden" name="id" value="${userEdit.id}" />

    <div class="mb-3">
        <label class="form-label">Username</label>
        <input type="text" name="username" class="form-control" value="${userEdit.username}" required />
    </div>

    <div class="mb-3">
        <label class="form-label">Password</label>
        <input type="text" name="password" class="form-control" value="${userEdit.password}" required />
    </div>

    <div class="mb-3">
        <label class="form-label">RoleId</label>
        <input type="number" name="roleid" class="form-control" value="${userEdit.roleid}" required />
    </div>

    <button class="btn btn-primary">Cập nhật</button>
    <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-secondary">Hủy</a>
</form>

</body>
</html>
