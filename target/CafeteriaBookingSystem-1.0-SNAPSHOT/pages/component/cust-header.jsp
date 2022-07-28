<%--
  Created by IntelliJ IDEA.
  User: limwa
  Date: 7/24/2022
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <jsp:include page="../../header.html"/>
    <title>Title</title>
      <script>
          function redirectToLoginPage(path)
          {
              window.location.replace(path + "/");
          }
      </script>
  </head>
  <body>
  <div class="ui menu" style="margin: 0 30px">
      <a class="item" href="<%=request.getContextPath() %>/pages/_customer.jsp">Manage Booking</a>
      <a class="item" href="<%= request.getContextPath() %>/pages/_customer-profile.jsp">Profile</a>
      <div class="right menu">
          <a class="item" onclick="redirectToLoginPage('<%=request.getContextPath()%>')">Log out</a>
      </div>
  </div>
  </body>
</html>
