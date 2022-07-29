<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
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
        <a class="item" href="<%=request.getContextPath() %>/pages/main.jsp">Manage Staff</a>
        <a class="item" href="<%= request.getContextPath() %>/pages/_cust-list.jsp">Manage Customers</a>
        <a class="item" href="<%= request.getContextPath() %>/pages/_dishes-list.jsp">Manage Dishes</a>
        <a class="item" href="<%= request.getContextPath() %>/pages/_book-list.jsp">Bookings</a>
        <a class="item" href="<%= request.getContextPath() %>/pages/_view-report.jsp">Collect Payment</a>
        <div class="right menu">
            <a class="item" onclick="redirectToLoginPage('<%=request.getContextPath()%>')">Log out</a>
        </div>
    </div>
</body>
</html>
