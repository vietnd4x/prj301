
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Thể Loại</title>
        <link rel="icon" href="../images/logo.ico" type="image/icon">
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <!--<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">-->
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-grid.css">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-grid.min.css">
        
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="container">
            <h2>Thêm thể loại sách</h2>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form action="/SellBookWeb/AdminAddCategory" method="POST">
                        Thể loại: <input type="text" name="category">
                        <br><br>
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-9">
                            <input type="submit" value="Thêm">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
