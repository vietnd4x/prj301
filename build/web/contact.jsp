
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liên Hệ</title>
        <link rel="icon" href="images/logo.ico" type="image/icon">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="contact">
                <div class="container">
                    <h3>Liên Hệ</h3>
                    <br><br>
                    <div class="col-md-8 contact-grids1">
                        <form action="contact" method="POST">
                            <div class="contact-form2">
                                <h4>Họ và tên</h4>
                                <input type="text" placeholder="" name="name" required>
                            </div>
                            <div class="contact-form2">
                                <h4>Email</h4>
                                <input type="email" name="email" placeholder="" required>
                            </div>
                            <div class="contact-form2">
                                <h4>Tiêu đề</h4>
                                <input type="text" name="title" placeholder="" required>
                            </div>
                            <div class="contact-me ">
                                <h4>Lời nhắn</h4>
                                <textarea type="text" name="message"  placeholder="" required> </textarea>
                            </div>
                            <input type="hidden" name="command" value="insert">
                            <input type="submit" value="Gửi Liên Hệ" >
                        </form>
                    </div>               
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
