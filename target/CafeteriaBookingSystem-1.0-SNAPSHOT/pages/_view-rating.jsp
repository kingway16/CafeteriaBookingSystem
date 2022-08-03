<%@ page import="java.util.List" %>
<%@ page import="com.models.SysOrder" %>
<%
    List<SysOrder> selectedList = (List<SysOrder>) session.getAttribute("selectedList");
    String bookingIdParam = request.getParameter("bookingId");
    String userid = (String) session.getAttribute("userid");
    session.setAttribute("bookingId", bookingIdParam);
    session.setAttribute("userid", userid);
    session.setAttribute("path", "_view-rating.jsp");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        window.onload = function() {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "<%= request.getContextPath() %>/booking/staff/rating", true);
            xhttp.send();
        }
    </script>
</head>
<body>
    <jsp:include page="component/staff-header.jsp"/>
  <form class="ui form" action="<%= request.getContextPath() %>/booking/staff/rating" method="POST" style="margin: 30px 50px 0">
      <div class="fields">
          <div class="field">
              <input class="ui button" type="submit" value="refresh">
          </div>
      </div>

      <table class="ui celled table">
          <thead>
          <tr>
              <th>booking id</th>
              <th>order id</th>
              <th>customer name</th>
              <th>rate</th>
              <th>feedback</th>
          </tr>
          </thead>
          <tbody>
          <%
              if(selectedList != null){
                  for( SysOrder order : selectedList)
                  {
                      out.println("<tr>");
                      out.println("<td>B00" + order.getBookingId().getBookingId() + "</td>");
                      out.println("<td>O00" + order.getOrderId() + "</td>");
                      out.println("<td>" + order.getBookingId().getSysUser().getName() + "</td>");
                      out.println("<td>" + order.getBookingId().getRating() + "</td>");
                      out.println("<td>" + order.getBookingId().getFeedback() + "</td>");
                      out.println("</tr>");
                  }
              }
          %>
          </tbody>
      </table>
  </form>
</body>
</html>
