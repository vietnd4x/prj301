
<%@page import="java.util.ArrayList"%>
<%@page import="context.AccountDAO"%>
<%@page import="model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tài Khoản</title>
        <link rel="icon" href="../images/logo.ico" type="image/icon">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <!--<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">-->
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-grid.css">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-grid.min.css">
        <%
            
            AccountDAO accountDAO = new AccountDAO();
            ArrayList<Account> list = accountDAO.getListAccount();
        %>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <h2>Quản lý người dùng</h2>
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-10">
                        <table border="2">
                            <tr style="text-align: center;">
                                <th>Mã Tài Khoản</th>
                                <th>Tên Tài Khoản</th>
                                <th>Email</th>
                                <th style="width: 80px;">Mật Khẩu</th>
                                <th style="width: 100px;">Số Điện Thoại</th>
                                <th style="width: 100px;">Status</th>
                            </tr>
                        <%for (Account account : list) {%>
                        <tr>
                            <td style="text-align: center;"><%=account.getAccountID()%></td>
                            <td><%=account.getAccountName()%></td>
                            <td><%=account.getAccountEmail()%></td>
                            <td><%=account.getAccountPass()%></td>
                            <td style="text-align: center;"><%=account.getAccountPhone()%></td>
                            <td style="text-align: center;"><%=account.getStatus2()%></td>
                            <%
                                String sign = "";
                                if (account.getStatus() == 1) {
                                    sign = "Block";
                                } else {
                                    sign = "Active";
                                }
                            %>
                        
                            <td style="text-align: center; width: 50px;"><a href="/SellBookWeb/AdminUpdateAccount?id=<%=account.getAccountID()%>"><%=sign%></a></td>
                            <td style="text-align: center; width: 50px;"><a href="/SellBookWeb/AdminDeleteAccount?id=<%=account.getAccountID()%>">Delete</a></td>
                        </tr>
                        <%}%>
                    </table>
                    <br><br>
                </div>
            </div>
        </div>
    </body>
</html>
