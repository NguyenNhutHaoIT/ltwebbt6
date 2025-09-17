<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa Video</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">

<h3>Sửa Video</h3>

<form action="${pageContext.request.contextPath}/admin/video-update" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${video.id}"/>

    <div class="mb-3">
        <label class="form-label">Tiêu đề</label>
        <input type="text" name="title" class="form-control" value="${video.title}" required/>
    </div>
    <div class="mb-3">
        <label class="form-label">Mô tả</label>
        <textarea name="description" class="form-control" rows="3">${video.description}</textarea>
    </div>
    <div class="mb-3">
        <label class="form-label">Poster hiện tại</label><br/>
        <c:if test="${not empty video.poster}">
            <img src="${pageContext.request.contextPath}/${video.poster}" width="120"/>
        </c:if>
    </div>
    <div class="mb-3">
        <label class="form-label">Chọn Poster mới (nếu muốn thay)</label>
        <input type="file" name="poster" class="form-control"/>
    </div>

    <button class="btn btn-primary">Cập nhật</button>
    <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-secondary">Hủy</a>
</form>

</body>
</html>
