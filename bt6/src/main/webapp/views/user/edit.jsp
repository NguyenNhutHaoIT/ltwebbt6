<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa thông tin cá nhân</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">

            <div class="card shadow border-0 rounded-3">
                <div class="card-header bg-success text-white text-center">
                    <h4>Chỉnh sửa thông tin cá nhân</h4>
                </div>
                <div class="card-body">

                    <form action="${pageContext.request.contextPath}/user/update" method="post">

                        <!-- Hidden ID -->
                        <input type="hidden" name="id" value="${user.id}" />

                        <!-- Username (readonly) -->
                        <div class="mb-3">
                            <label class="form-label">Tên đăng nhập</label>
                            <input type="text" class="form-control" name="username"
                                   value="${user.username}" readonly/>
                        </div>

                        <!-- Password -->
                        <div class="mb-3">
                            <label class="form-label">Mật khẩu mới</label>
                            <input type="password" class="form-control" name="password" 
                                   placeholder="Nhập mật khẩu mới" required/>
                        </div>

                        <!-- Confirm Password -->
                        <div class="mb-3">
                            <label class="form-label">Xác nhận mật khẩu</label>
                            <input type="password" class="form-control" name="confirmPassword" 
                                   placeholder="Nhập lại mật khẩu mới" required/>
                        </div>

                        <!-- Submit -->
                        <div class="d-flex justify-content-between">
                            <a href="${pageContext.request.contextPath}/user/home" 
                               class="btn btn-secondary">Hủy</a>
                            <button type="submit" class="btn btn-success">Lưu thay đổi</button>
                        </div>
                    </form>

                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>
