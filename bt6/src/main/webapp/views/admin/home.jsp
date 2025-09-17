<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">

<h3>Xin chào Manager: ${sessionScope.user.username}</h3>
<a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">Logout</a>

<!-- CATEGORY -->
<h4 class="mt-4">Danh sách Category</h4>
<a href="${pageContext.request.contextPath}/admin/insert-categories" class="btn btn-success my-3">Thêm Category</a>
<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Icon</th>
        <th>Người tạo</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="c" items="${listcate}">
        <tr>
            <td>${c.id}</td>
            <td>${c.categoryname}</td>
            <td><img src="${pageContext.request.contextPath}/${c.icon}" width="50"/></td>
            <td>${c.user.username}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/edit-categories?id=${c.id}" class="btn btn-warning btn-sm">Sửa</a>
                <a href="${pageContext.request.contextPath}/admin/delete-categories?id=${c.id}" class="btn btn-danger btn-sm"
                   onclick="return confirm('Bạn chắc chắn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- USER -->
<h4 class="mt-4">Danh sách User</h4>
<a href="${pageContext.request.contextPath}/admin/insert-users" class="btn btn-success my-3">Thêm User</a>
<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>RoleId</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="u" items="${listuser}">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.password}</td>
            <td>${u.roleid}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/edit-users?id=${u.id}" class="btn btn-warning btn-sm">Sửa</a>
                <a href="${pageContext.request.contextPath}/admin/delete-users?id=${u.id}" class="btn btn-danger btn-sm"
                   onclick="return confirm('Bạn chắc chắn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- VIDEO -->
<h4 class="mt-4">Danh sách Video</h4>
<a href="${pageContext.request.contextPath}/admin/video-insert" class="btn btn-success my-3">Thêm Video</a>

<form class="d-flex mb-3" method="get" action="${pageContext.request.contextPath}/admin/video-search">
    <input class="form-control me-2" type="text" name="keyword" placeholder="Tìm video..." value="${param.keyword}">
    <button class="btn btn-primary" type="submit">Tìm kiếm</button>
</form>

<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>Tiêu đề</th>
        <th>Mô tả</th>
        <th>Poster</th>
        <th>Người tạo</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="v" items="${listvideo}">
        <tr>
            <td>${v.id}</td>
            <td>${v.title}</td>
            <td>${v.description}</td>
            <td><img src="${pageContext.request.contextPath}/${v.poster}" width="80"/></td>
            <td>${v.user.username}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/video-edit?id=${v.id}" class="btn btn-warning btn-sm">Sửa</a>
                <a href="${pageContext.request.contextPath}/admin/video-delete?id=${v.id}" class="btn btn-danger btn-sm"
                   onclick="return confirm('Bạn chắc chắn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
