<%@ page import="java.util.List" %>
<%@ page import="com.models.Booking" %><%--
  Created by IntelliJ IDEA.
  User: limwa
  Date: 7/23/2022
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    List<Booking> bookingListObject = (List<Booking>) session.getAttribute("bookingList");
    session.setAttribute("path", "_book-list.jsp");
    String origPath = request.getContextPath();
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../header.html"/>
    <title>Title</title>
    <script>
        React.createClass({

        })
        window.onload = function() {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "<%= request.getContextPath() %>/booking", true);
            xhttp.send();
        }
    </script>
</head>
<body>
    <jsp:include page="component/header.jsp"/>
    <form class="ui form" action="<%= request.getContextPath() %>/booking" method="POST" style="margin: 30px 50px 0">
        <div class="fields">
            <div class="field">
                <input class="ui button" type="submit" value="refresh">
            </div>
        </div>

        <table class="ui celled table">
            <thead>
            <tr>
                <th>booking id</th>
                <th>customer name</th>
                <th>rating</th>
                <th>dish name</th>
                <th>feedback</th>
                <th>total</th>
                <th>creation date</th>
                <th>pending</th>
                <th></th>
            </tr></thead>
            <tbody>
            <%
                if(bookingListObject != null){
                    for( Booking booking : bookingListObject)
                    {
                        out.println("<tr>");
                        out.println("<td>B00" + booking.getBookingId() + "</td>");
                        out.println("<td>" + booking.getSysUser().getName() + "</td>");
                        out.println("<td>" + booking.getRating() + "</td>");
                        out.println("<td>" + booking.getDishesId().getDishesName() + "</td>");
                        out.println("<td>" + booking.getFeedback() + "</td>");
                        out.println("<td>RM " + booking.getDishesId().getDishesName() + "</td>");
                        out.println("<td>" + booking.getCreationDate() + "</td>");
                        if ( booking.getPending().equals("Y"))
                        {
                            out.println("<td>Yes</td>");
                            out.println("<td><a href='_booking-assign.jsp?id=" + booking.getBookingId() + "'>assign</a></td>");
                        }
                        else if ( booking.getPending().equals("O"))
                        {
                            out.println("<td>Ongoing</td>");
                            out.println("<td></td>");
                        }
                        else if ( booking.getPending().equals("N"))
                        {
                            out.println("<td>No</td>");
                            out.println("<td></td>");
                        }
                        else {
                            out.println("<td></td>");
                            out.println("<td></td>");
                        }
                        out.println("</tr>");
                    }
                }
            %>
            </tbody>
        </table>
    </form>
</body>
</html>
