<%@ page import="java.util.List" %>
<%@ page import="com.models.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userid = (String) session.getAttribute("userid");
    List<Order> orderList = (List<Order>) session.getAttribute("orderList");
    session.setAttribute("userid", userid);
%>
<head>
    <title>Title</title>
    <script>
        function onUpdateStatus(orderId, bookingId){
            const xhttp1 = new XMLHttpRequest();
            xhttp1.open("POST", "<%= request.getContextPath() %>/order/update?orderId=" + orderId + "&bookingId=" + bookingId, true);
            xhttp1.send();

            const xhttp2 = new XMLHttpRequest();
            xhttp2.open("POST", "<%= request.getContextPath() %>/staff/order?userid=<%=userid%>", true);
            xhttp2.send();
        }
    </script>
</head>
<body>
    <jsp:include page="component/staff-header.jsp"/>
    <form class="ui form" action="<%= request.getContextPath() %>/staff/order?userid=<%=userid%>" method="POST" style="margin: 30px 50px 0">
        <div class="fields">
            <div class="field">
                <input class="ui button" type="submit" value="refresh">
            </div>
        </div>

        <table class="ui celled table">
            <thead>
            <tr>
                <th>order id</th>
                <th>booking id</th>
                <th>dish name</th>
                <th>notes</th>
                <th>customer name</th>
                <th>order status</th>
            </tr></thead>
            <tbody>
            <%
                if(orderList != null){
                    for( Order order : orderList)
                    {
                        out.println("<tr>");
                        out.println("<td>O00" + order.getOrderId() + "</td>");
                        out.println("<td>" + order.getBookingId() + "</td>");
                        out.println("<td>" + order.getDishesName() + "</td>");
                        out.println("<td>" + order.getNotes() + "</td>");
                        out.println("<td>" + order.getUserName() + "</td>");
                        if ( order.getOrderStatus().equals("P"))
                        {
                            out.println("<td> Preparing... </td>");
                        }
                        out.println("<td> " +
                                "<button style='background: transparent; border: none; cursor: pointer; color: #4183c4' onclick='onUpdateStatus(" + order.getOrderId() + "," + order.getBookingId() +")'>completed</button> " +
                                "</td>");
                        out.println("</tr>");
                    }
                }
            %>
            </tbody>
        </table>
    </form>
</body>
</html>
