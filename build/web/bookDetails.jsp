
<%@page import="model.Category"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="context.AccountDAO"%>
<%@page import="model.Books"%>
<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Details</title>
        <link rel="icon" href="images/logo.ico" type="image/icon type">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">

        <%
            
            String id = request.getParameter("bookID");
            Account account = null;
            AccountDAO accountDAO = new AccountDAO();
            account = (Account) session.getAttribute("account");
            Books book = new AccountDAO().getBookById(id);
            DecimalFormat formatter = new DecimalFormat("###,###,###");
        %>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container" style="padding-top: 50px;">
                <h2>Chi tiết sản phẩm</h2>
                <div class="row">
                    <div class="col-md-6">		
                        <a href="bookDetails.jsp?bookID=<%=book.getBookID()%>">
                        <div>
                            <img src="<%=book.getBookImage()%>"  alt="" height="600">
                        </div>			
                    </a>			
                </div>
                <div class="col-md-6">
                    <div style="font-size: 40px;">
                        <a href="bookDetails.jsp?bookID=<%=book.getBookID()%>"><%=book.getBookName()%></a>
                        <p><em class="item_price"><%=formatter.format(book.getBookPrice())%> VNĐ</em>
                            <br>
                            <%if (account != null && account.getIsAdmin() == 1) {%>             
                            <a href="admin/editBook.jsp?id=<%=book.getBookID()%>" data-text="Sửa Sách Này">Sửa Sách Này</a>        
                            <%}%>    

                            <%if (account != null && account.getIsAdmin() == 0) {%>             
                            <a href="cart?bookID=<%=book.getBookID()%>" data-text="Thêm vào giỏ">Thêm vào giỏ</a>
                            <%}%>
                            <%if (account == null) {%>             
                            <a href="login.jsp" data-text="Thêm vào giỏ">Thêm vào giỏ</a>
                            <%}%>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row" style="padding-top: 100px;">
                <div class="col-md-4">
                    <p>Mã Sách</p>
                    <p>Thể Loại</p>
                    <p>Tên Sách</p>
                    <p>Giá</p>
                    <p>Mô tả</p>
                </div>
                <div class="col-md-8">
                    <p><%=book.getBookID()%></p>
                    <p><%if (book.getCategoryID() == 1) {%>
                        Sách trong nước
                        <%} else {%>
                        Sách nước ngoài
                       
                        <%}%>
                    </p>
                    <p><%=book.getBookName()%></p>
                    <p><%=formatter.format(book.getBookPrice())%> VNĐ</p>
                    <p><%=book.getBookDescription()%></p>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
