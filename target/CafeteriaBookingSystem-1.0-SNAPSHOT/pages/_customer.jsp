<%@ page import="java.util.List" %>
<%@ page import="com.models.Booking" %>
<%@ page import="com.models.Dishes" %><%--
  Created by IntelliJ IDEA.
  User: limwa
  Date: 7/23/2022
  Time: 12:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    String userid = (String) session.getAttribute("userid");
    List<Booking> bookingList = (List<Booking>) session.getAttribute("bookingList");
    List<Dishes> dishesList = (List<Dishes>) session.getAttribute("dishesList");
    session.setAttribute("path", "_customer.jsp");
    session.setAttribute("userid", userid);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        window.onload = function() {
            const xhttp1 = new XMLHttpRequest();
            xhttp1.open("POST", "<%= request.getContextPath() %>/booking/get", true);
            xhttp1.send();

            const xhttp2 = new XMLHttpRequest();
            xhttp2.open("POST", "<%= request.getContextPath() %>/dish/get", false);
            xhttp2.send();
        }
        function onDeleteAction(id)
        {
            const xhttp1 = new XMLHttpRequest();
            xhttp1.open("POST", "<%= request.getContextPath() %>/booking/delete?id=" + id, true);
            xhttp1.send();
        }
        function onUpdateAction(id)
        {

        }
    </script>
</head>
<body>
    <jsp:include page="component/cust-header.jsp"/>
    <div class="ui middle aligned center aligned grid" style="margin-top: 20px">
    <form class="ui form" action="<%= request.getContextPath() %>/booking/create" method="POST">
        <h2>Create New Booking</h2>
        <div class="fields">
            <div class="eight wide field">
                <label>List of Dishes</label>
                    <div class="field">
                        <select class="ui fluid search dropdown" name="dishId">
                            <%
                                if(dishesList != null){
                                    for (Dishes dish: dishesList)
                                    {
                                        out.println("<option value='" + dish.getDishesId()+ "'>" + dish.getDishesName() + " - RM " + dish.getTotal() +"</option>");
                                    }
                                }
                            %>
                        </select>
                    </div>
            </div>
            <div class="eight wide field">
                <label for="inputNotes">Notes</label>
                <input name="notes" type="text" id="inputNotes"  placeholder="Optional">
            </div>
            <div class="three wide field">
                <div class="field">
                    <input class="ui button" style='margin-top: 25px' type="submit" value="pay">
                </div>
            </div>
        </div>
    </form>
    </div>

    <div class="fields">
        <form class="ui form" action="<%= request.getContextPath() %>/booking/get" method="POST" style="margin: 30px 50px 0">
            <div class="two fields">
                <div class="field">
                    <input class="ui button" type="submit" value="refresh">
                </div>
                <div class="field">
                    <div class="two fields" style="justify-content: right">
                        <div class="field">
                            <div class="ui icon input">
                                <input name="search" type="text" id="inputSearch" placeholder="Search">
                                <i class="search icon"></i>
                            </div>
                        </div>
                        <button class="ui button">Search</button>
                    </div>
                </div>
            </div>
                <div class="field">
                <table class="ui celled table">
                    <thead>
                    <tr>
                        <th>booking id</th>
                        <th>dishes name</th>
                        <th>notes</th>
                        <th>total</th>
                        <th>creation date</th>
                        <th>Status</th>
                        <th></th>
                    </tr></thead>
                    <tbody>
                    <%
                        if(bookingList != null){
                            for( Booking booking : bookingList)
                            {
                                out.println("<tr>");
                                out.println("<td>" + booking.getBookingId() + "</td>");
                                out.println("<td>" + booking.getDishesId().getDishesId() + "</td>");
                                out.println("<td>" + booking.getNotes() + "</td>");
                                out.println("<td>" + booking.getDishesId().getTotal() + "</td>");
                                out.println("<td>" + booking.getCreationDate() + "</td>");
                                if( booking.getPending().equals("Y"))
                                {
                                    out.println("<td>Pending</td>");
                                    out.println("<td> " +
                                            "<div class='two fields'>" +
                                            "<div class='field'>" +
                                            "<button style='background: transparent; border: none; cursor: pointer; color: #4183c4' onclick='onDeleteAction('" + booking.getBookingId() + ")'>delete</button>" +
                                            "</div>" +
                                            "<div class='field'>" +
                                            "<button style='background: transparent; border: none; cursor: pointer; color: #4183c4' onclick='onUpdateAction(" + booking.getBookingId() + ")'>update</button>" +
                                            "</div>" +
                                            "</div>" +
                                            "</td>");
                                }
                                else if( booking.getPending().equals("O"))
                                {
                                    out.println("<td>Ongoing</td><td/>");
                                }
                                else if( booking.getPending().equals("N"))
                                {
                                    out.println("<td>Completed</td>");
                                    if(booking.getFeedback() == null || booking.getRating() == null)
                                    {
                                        out.println("<td><a href='_service-rate.jsp?bookingId=" + booking.getBookingId() + "'>rate</a></td>");
                                    }
                                    else
                                    {
                                        out.println("<td/>");

                                    }
                                }
                                else
                                {
                                    out.println("<td/><td/>");
                                }
                                out.println("</tr>");
                            }
                        }
                    %>
                    </tbody>
                </table>
                </div>
        </form>
    </div>
</div>
</body>
</html>
