<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-5">

<h3 class="text-center">Đăng nhập</h3>

<form action="${pageContext.request.contextPath}/login" method="post" class="col-md-4 offset-md-4">
    <div class="mb-3">
        <label class="form-label">Username</label>
        <input type="text" name="username" class="form-control" required/>
    </div>
    <div class="mb-3">
        <label class="form-label">Password</label>
        <input type="password" name="password" class="form-control" required/>
    </div>
    <button class="btn btn-primary w-100">Login</button>
</form>

<c:if test="${not empty error}">
    <div class="alert alert-danger mt-3">${error}</div>
</c:if>

</body>
</html>
