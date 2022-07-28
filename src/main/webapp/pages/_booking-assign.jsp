<%@ page import="com.models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: limwa
  Date: 7/24/2022
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    String getParamId = request.getParameter("id");
    List<User> staffListObject = (List<User>) session.getAttribute("staffObjectList");
    session.setAttribute("staffid", getParamId);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        window.onload = function() {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "<%=request.getContextPath()%>/staff/getName", true);
            xhttp.send();
        }
    </script>
</head>
<body>
    <jsp:include page="component/header.jsp"/>
    <form class="ui form" action="<%= request.getContextPath() %>/order/create" method="POST" style="margin: 30px 50px 0">
        <h2>Assign Orders</h2>
        <div class="fields">
            <div class="five wide field">
                <label for="inputBookingId">Booking Id</label>
                <input name="bookingId" type="text" id="inputBookingId" placeholder="Username" value="<%=getParamId%>" readonly>
            </div>
            <div class="five wide field">
                <label>Staff Name</label>
                <select class="ui fluid search dropdown" name="staffDropdownList" >
                    <%
                        if(staffListObject != null)
                        {
                            for (User user: staffListObject)
                            {
                                out.println("<option value='" + user.getUserid() + "'>" + user.getName() + "</option>");
                            }
                        }
                    %>
                </select>
            </div>
            <div class="five wide field">
                <label></label>
                <input class="ui button" style="margin-top: 25px" type="submit" value="assign"/>
            </div>
        </div>
    </form>
</body>
</html>
