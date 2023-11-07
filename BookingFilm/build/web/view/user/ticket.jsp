
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../component/setupCss.jsp" %>
    </head>
    <body>
        <%@include file="../component/header.jsp" %>
        <section class="py-1">
            <!-- style="background-color: white"-->
            <div class="container d-flex " style="min-height: 800px;">
                <div class="col-md-12">
                    <h3 class="mt-5">History Booking</h3>
                    <h5 class="mt-5">Note: Only refund the newest payment</h5>
                    
                    <table class="table mt-2" style="height: 100px">
                        <thead>
                            <tr>
                                <th scope="col">Booking Code</th>
                                <th scope="col">Film Title</th>
                                <th scope="col">Date</th>
                                <th scope="col">Seat</th>                        
                                <th scope="col">Payment</th>      
                                <th scope="col">Action</th>      
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="isFirstRow" value="true" />
                            <c:forEach items="${history}" var="hs">
                                <tr style="height: 60px">
                                    <th scope="row">${hs.bookingId}</th>
                                    <th scope="row">${hs.film}</th>
                                    <th scope="row">${hs.date}</th>
                                    <th scope="row">${hs.seat}</th>
                                    <th scope="row">Paid</th>
                                    <th scope="row">
                                        <c:choose>
                                            <c:when test="${isFirstRow && not empty sessionScope.transactionId}">
                                                <a href="./refund?bid=${hs.bookingId}">Refund</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="./deleteticket?bid=${hs.bookingId}">delete</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </th>
                                </tr>
                                <c:set var="isFirstRow" value="false" />
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </section>

                
                            
                            
        <%@include file="../component/footer.jsp" %>
    </body>
</html>
