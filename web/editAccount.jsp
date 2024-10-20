
<%@page import="context.AccountDAO"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Account</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-grid.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-grid.min.css">
        <%
            Account account = null;
            AccountDAO accountDAO = new AccountDAO();
            account = (Account) session.getAttribute("account");
        %>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <h2>Chỉnh Sửa Tài Khoản</h2>
                <div class="row" style="padding-top: 100px;">
                    <div class="col-md-4">
                        <p>Tên Tài Khoản</p>
                        <p>Email</p>
                        <p>Mật Khẩu</p>
                        <p>Số Điện Thoại</p>
                    </div>
                    <div class="col-md-8">
                        <form action="EditAccount" method="POST">
                            <p><input type="text" name="name" value="<%=account.getAccountName()%>" /></p>
                        <p><input type="text" name="email" value="<%=account.getAccountEmail()%>" readonly/></p>
                        <p><input type="text" name="password" value="<%=account.getAccountPass()%>" /></p>
                        <p><input type="text" name="phone" value="<%=account.getAccountPhone()%>" /> </p>
                        <input type="submit" class="btn btn-success" value="Lưu" />
                    </form>
                </div>
            </div>
            <br><br>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
