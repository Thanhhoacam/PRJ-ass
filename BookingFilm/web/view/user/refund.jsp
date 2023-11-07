
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Successfull</title>
        <%@include file="../component/setupCss.jsp" %>
    </head>
    <body style="">
        <%@include file="../component/header.jsp" %>
        <div class="card text-center mt-5 mb-5 ms-5 d-flex justify-content-center align-content-center"
             style="width: 90%; height: 300px;">
           <c:if test="${not empty sessionScope.error}">
    <h3 id="error-message" style="color: red">${sessionScope.error}</h3>
</c:if>
            <div class="card-body">
                <div>
                    <form action="deleteticket">
                        <h3>${bid}</h3>
                        <input type="hidden" name="bid" value="${bid}">
                        <input type="text" name="paymentId" placeholder="Enter payment id" required="">
                        <input type="submit" name="name" value="Confirm">
                    </form>
                </div>
            </div>
        </div>
        <%@include file="../component/footer.jsp" %>
        
          <script>
    // Kiểm tra nếu có thông báo lỗi và ẩn nó sau 5 giây
    window.onload = function() {
        var errorMessage = document.getElementById("error-message");
        if (errorMessage) {
            setTimeout(function() {
                errorMessage.style.display = "none";
            }, 5000); // 5 giây (5000 ms)
        }
    }
</script>          
    </body>
    
</html>
