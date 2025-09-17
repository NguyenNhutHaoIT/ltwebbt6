<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">

<h3>Sửa Category</h3>

<form action="${pageContext.request.contextPath}/admin/edit-categories" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${cate.id}"/>

    <div class="mb-3">
        <label class="form-label">Tên Category</label>
        <input type="text" name="categoryname" class="form-control" value="${cate.categoryname}" required/>
    </div>

    <div class="mb-3">
        <label class="form-label">Ảnh hiện tại</label><br/>
        <img src="${pageContext.request.contextPath}/${cate.icon}" width="120" class="border rounded"/>
    </div>

    <div class="mb-3">
        <label class="form-label">Chọn ảnh mới (nếu muốn thay)</label>
        <input type="file" name="icon" class="form-control"/>
    </div>

    <button class="btn btn-primary">Cập nhật</button>
    <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-secondary">Hủy</a>
</form>

</body>
</html>
