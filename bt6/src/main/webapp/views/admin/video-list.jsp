<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Video</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">

<h3>Danh sách Video</h3>

<form action="${pageContext.request.contextPath}/admin/video-search" method="get" class="mb-3">
    <div class="input-group">
        <input type="text" name="keyword" class="form-control" placeholder="Nhập từ khóa tìm kiếm..."/>
        <button class="btn btn-primary">Tìm kiếm</button>
    </div>
</form>

<a href="${pageContext.request.contextPath}/admin/video-insert" class="btn btn-success mb-3">+ Thêm Video</a>
<a href="${pageContext.request.contextPath}/admin/home" class="btn btn-secondary mb-3">Quay lại Trang Admin</a>

<table class="table table-bordered">
    <thead>
        <tr>
            <th>ID</th>
            <th>Tiêu đề</th>
            <th>Mô tả</th>
            <th>Poster</th>
            <th>Người đăng</th>
            <th>Hành động</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="v" items="${videos}">
            <tr>
                <td>${v.id}</td>
                <td>${v.title}</td>
                <td>${v.description}</td>
                <td>
                    <c:if test="${not empty v.poster}">
                        <img src="${pageContext.request.contextPath}/${v.poster}" width="100"/>
                    </c:if>
                </td>
                <td>${v.user.username}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/video-edit?id=${v.id}" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="${pageContext.request.contextPath}/admin/video-delete?id=${v.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
