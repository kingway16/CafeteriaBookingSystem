<%@ page import="com.models.Booking" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: limwa
  Date: 7/24/2022
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>

<%
    String userid = (String) session.getAttribute("userid");
    String bookingId = request.getParameter("bookingId");
    List<Booking> bookingList = (List<Booking>) session.getAttribute("selectedBookList");
    session.setAttribute("bookingId", bookingId);
    session.setAttribute("userid", userid);
    session.setAttribute("path", "_service-rate.jsp");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="component/cust-header.jsp"/>
     <form class="ui form" action="<%= request.getContextPath() %>/booking/feedback" method="POST" style="margin: 30px 50px 0">
           <h2>Feedback and Rate</h2>
         <div class="fields">
             <div class="ten wide field">
                 <label for="inputRate">rate</label>
                 <input name="rate" type="number" id="inputRate" placeholder="Rate" max="10" min="1">
             </div>
             <div class="sixteen wide field">
                 <label for="inputFeedback">feedback</label>
                 <input name="feedback" type="text" id="inputFeedback" placeholder="Feedback">
             </div>
         </div>
         <div class="fields">
             <input class="ui button" type="submit" value="submit">
         </div>
     </form>
</body>
</html>