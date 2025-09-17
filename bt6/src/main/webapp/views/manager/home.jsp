<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">

<h3>Xin chào Manager: ${sessionScope.user.username}</h3>
<a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">Logout</a>

<a href="${pageContext.request.contextPath}/manager/insert" class="btn btn-success my-3">Thêm Category</a>

<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Icon</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="c" items="${listcate}">
        <tr>
            <td>${c.id}</td>
            <td>${c.categoryname}</td>
            <td><img src="${pageContext.request.contextPath}/${c.icon}" width="50"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/manager/edit?id=${c.id}" class="btn btn-warning btn-sm">Sửa</a>
                <a href="${pageContext.request.contextPath}/manager/delete?id=${c.id}" class="btn btn-danger btn-sm"
                   onclick="return confirm('Bạn chắc chắn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
