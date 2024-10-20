
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="context.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tìm Kiếm Sách</title>
        <link rel="icon" href="images/logo.ico" type="image/icon type">        
    </head>
    <body>

        <jsp:include page="header.jsp"></jsp:include>
            <div class="container">
                <h2>Tìm Kiếm Sách</h2>            
                <div class="row">
                <c:forEach items="${listB}" var="b">
                    <div class="col-md-3">		
                        <a href="bookDetails.jsp?bookID=${b.getBookID()}">
                            <div>
                                <img src="${b.getBookImage()}"  alt="" height="400">
                            </div>			
                        </a>			
                        <div class="women">
                            <h6><a href="bookDetails.jsp?bookID=${b.getBookID()}">${b.getBookName()}</a></h6>
                            <p><em class="item_price">${b.getBookPrice()} VNĐ</em>
                                <br>
                                <a href="cart?bookID=${b.getBookID()}" data-text="Thêm vào giỏ">Thêm vào giỏ</a>
                            </p>
                        </div>
                    </div>
                </c:forEach>                
            </div>     
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
