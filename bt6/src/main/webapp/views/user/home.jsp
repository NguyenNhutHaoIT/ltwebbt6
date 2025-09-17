<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Trang User</a>
        <div class="d-flex">
            <span class="navbar-text text-white me-3">
                Xin chào, ${sessionScope.user.username}
            </span>
            <a href="${pageContext.request.contextPath}/user/edit" class="btn btn-light btn-sm me-2">Chỉnh sửa thông tin</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">Logout</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="card shadow mb-4">
        <div class="card-header bg-success text-white">
            <h5 class="mb-0">Danh sách Category</h5>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Tên</th>
                            <th>Icon</th>
                            <th>Người tạo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${listcate}">
                            <tr>
                                <td>${c.id}</td>
                                <td>${c.categoryname}</td>
                                <td>
                                    <img src="${pageContext.request.contextPath}/${c.icon}" width="50" class="rounded"/>
                                </td>
                                <td>${c.user.username}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
