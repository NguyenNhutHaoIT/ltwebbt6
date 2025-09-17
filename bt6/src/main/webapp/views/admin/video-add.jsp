<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Video</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">

<h3>Thêm Video</h3>

<form action="${pageContext.request.contextPath}/admin/video-insert" 
      method="post" 
      enctype="multipart/form-data">

    <div class="mb-3">
        <label class="form-label">Tiêu đề</label>
        <input type="text" name="title" class="form-control" required/>
    </div>

    <div class="mb-3">
        <label class="form-label">Mô tả</label>
        <textarea name="description" class="form-control"></textarea>
    </div>

    <div class="mb-3">
        <label class="form-label">Poster (ảnh)</label>
        <input type="file" name="poster" class="form-control" required/>
    </div>

    <button class="btn btn-success">Thêm mới</button>
    <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-secondary">Hủy</a>
</form>

</body>
</html>
