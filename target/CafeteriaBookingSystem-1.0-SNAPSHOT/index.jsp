<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String error = (String) session.getAttribute("errorMessage");
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <jsp:include page="header.html"/>
    <title>APU Cafeteria - Online Booking System</title>
    <script type="text/javascript">
        function doRegister(){
            window.location.assign("<%= request.getContextPath() %>/pages/register.jsp");
        }
    </script>
</head>
<body>
<br/>
<div class="ui middle aligned center aligned grid">
    <div class="fields">
        <div class="one wide field">
            <form class="ui form" action="<%= request.getContextPath() %>/pages" method="POST">
                <div class="field">
                    <h1>APU Cafeteria OBS</h1>
                </div>
                <div class="field">
                    <label for="inputUsername">Username</label>
                    <input name="name" type="text" id="inputUsername" class="form-control" placeholder="Username" required="required" autofocus="autofocus">
                </div>
                <div class="field">
                    <label for="inputPassword">Password</label>
                    <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required="required">
                </div>
                <p style="font-size: 10px; color: red; margin-top: -10px"><%
                    if(error == null) { out.println(""); } else {out.println(error); }
                %></p>
                <input class="ui fluid large teal submit button" type="submit" value="submit">
            </form>
        </div>
        <div class="one wide field">
            <button class="ui fluid large teal submit button" onclick="doRegister();">customer registration</button>
        </div>
    </div>
</div>
</body>
</html>