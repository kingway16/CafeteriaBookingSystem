<%@ page import="com.models.Booking" %>
<%@ page import="java.util.List" %>
<%
    List<Booking> selectedList = (List<Booking>) session.getAttribute("selectedList");
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
                  for( Booking book : selectedList)
                  {
                      out.println("<tr>");
                      out.println("<td>B00" + book.getBookingid() + "</td>");
                      out.println("<td>O00" + book.getOrderid() + "</td>");
                      out.println("<td>" + book.getName() + "</td>");
                      out.println("<td>" + book.getRating() + "</td>");
                      out.println("<td>" + book.getFeedback() + "</td>");
                      out.println("</tr>");
                  }
              }
          %>
          </tbody>
      </table>
  </form>
</body>
</html>
