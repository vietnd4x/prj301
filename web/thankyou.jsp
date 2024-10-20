
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cảm Ơn</title>
        <link rel="icon" href="images/logo.ico" type="image/icon type">
    </head>

    <jsp:include page="header.jsp"></jsp:include>
        <br>

        <div class="container">
        <%if (session.getAttribute("thankBuy") == null) {
            } else {%>
        <h2><%=session.getAttribute("thankBuy")%>
            <%session.removeAttribute("thankBuy");%></h2><%}%>
            <%if (session.getAttribute("thankContact") == null) {
                } else {%>
        <h2><%=session.getAttribute("thankContact")%>
            <%session.removeAttribute("thankContact");%></h2><%}%>
            
            <%if (session.getAttribute("thankEdit") == null) {
                } else {%>
        <h2><%=session.getAttribute("thankEdit")%>
            <%session.removeAttribute("thankEdit");%></h2><%}%>

    </div>
    <br>
    <jsp:include page="footer.jsp"></jsp:include>

</html>
