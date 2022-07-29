<%@ page import="com.models.Payment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Payment> paymentList = (List<Payment>) session.getAttribute("paymentList");
%>
<html>
<head>
    <title>Title</title>
    <script>
        function onPrintAction(payment)
        {
          alert(payment)
        }
    </script>
</head>
<body>
<jsp:include page="component/header.jsp"/>
<div class="fields">
  <form class="ui form" action="<%= request.getContextPath() %>/payment/get" method="POST" style="margin: 30px 50px 0">
    <div class="two fields">
      <div class="field">
        <input class="ui button" type="submit" value="refresh">
      </div>
    </div>

    <table class="ui celled table" style="margin-bottom: 80px">
      <thead>
      <tr>
        <th></th>
        <th>payment id</th>
        <th>booking id</th>
        <th>order id</th>
        <th>customer name</th>
        <th>total</th>
        <th>transaction date</th>
      </tr></thead>
      <tbody>
      <%
        String str = request.getContextPath();

        if(paymentList != null){
          for( Payment payment : paymentList)
          {
            out.println("<form>");
            out.println("<tr>");
            out.println("<td> " +
                    "<button style='background: transparent; border: none; cursor: pointer; color: #4183c4' onclick='onPrintAction'>hello</button>" +
                    "</td>");
            out.println("<td> <input type='text' id='id' value='" + payment.getId() + "'/></td>");
            out.println("<td> <input type='text' id='bookingId' value='" + payment.getBookingId() + "' /></td>");
            out.println("<td> <input type='text' id='orderId' value='" + payment.getOrderId() + "'/></td>");
            out.println("<td> <input type='text' id='name' value='" + payment.getName() + "'/></td>");
            out.println("<td> <input type='text' id=='total' value='" + payment.getTotal() + "'/></td>");
            out.println("<td> <input type='text' id='trx' value='" + payment.getTrxDate() + "'/></td>");
            out.println("</tr>");
            out.println("</form>");
          }
        }
      %>
      </tbody>
    </table>
  </form>
</div>
</body>
</html>
