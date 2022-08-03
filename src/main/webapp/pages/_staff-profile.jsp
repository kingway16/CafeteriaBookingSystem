<%@ page import="com.models.SysUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String name = "";
    String password = "";
    String email = "";
    String telno = "";
    String address = "";
    String day = "";
    String gender = "";
    String userid = "";

    String origPath = request.getContextPath();
    SysUser staffDetails = (SysUser) session.getAttribute("staffObject");
    if(staffDetails != null)
    {
        name = staffDetails.getName();
        password = staffDetails.getPassword();
        email = staffDetails.getEmail();
        telno = staffDetails.getTelno();
        address = staffDetails.getAddress();
        day = staffDetails.getDob();
        gender = staffDetails.getGender();
    }
    userid = (String) session.getAttribute("userid");
    session.setAttribute("userid", userid);
    request.setAttribute("path", "_staff-profile.jsp");
%>
    <html>
<head>
    <title>Title</title>
    <script>
        window.onload = function() {
           onLoadStaffInfo();
        }
        function onLoadStaffInfo(){
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "<%=origPath%>/staff/get?userid=<%=userid%>&path=_staff-profile.jsp", true);
            xhttp.send();
        }
    </script>
</head>
<body>
    <jsp:include page="component/staff-header.jsp"/>
    <div class="ui middle aligned center aligned grid" style="margin-top: 20px">
        <div class="fields">
            <div class="field">
                <form class="ui form" action="<%= request.getContextPath() %>/staff/update?userid=<%=userid%>&path=_staff-profile.jsp" method="POST">
                    <h2>Update Staff Information</h2>
                    <div class="fields">
                        <div class="eight wide field">
                            <label for="inputUsername">Username</label>
                            <input name="username" type="text" value="<%=name%>" id="inputUsername" placeholder="Username"  >
                        </div>
                        <div class="eight wide field">
                            <label for="inputPassword">Password</label>
                            <input name="password" type="text" value="<%=password%>" id="inputPassword" placeholder="Password"  >
                        </div>
                    </div>
                    <div class="fields">
                        <div class="eight wide field">
                            <label for="inputEmail">Email</label>
                            <input name="email" type="text" id="inputEmail" value="<%=email%>" placeholder="Email"  >
                        </div>
                        <div class="eight wide field">
                            <label for="inputTelNo">Tel No.</label>
                            <input name="telno" type="text" id="inputTelNo" value="<%=telno%>" placeholder="Password"  >
                        </div>
                    </div>
                    <div class="fields">
                        <div class="sixteen wide field">
                            <label for="inputAddress">Address</label>
                            <input name="address" value="<%=address%>" type="text" id="inputAddress" placeholder="Address"  >
                        </div>
                    </div>
                    <div class="fields">
                        <div class="sixteen wide field">
                            <label>Date of Birth</label>
                            <div class="sixteen wide field">
                                <div class="field">
                                    <input type="text" name="day" value="<%=day%>" maxlength="10" placeholder="Day"  >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="fields">
                        <div class="sixteen wide field">
                            <label>Gender</label>
                            <select class="ui fluid search dropdown" value="<%=gender%>" name="gender"  >
                                <option value="M">Male</option>
                                <option value="F">Female</option>
                            </select>
                        </div>
                    </div>
                    <div class="fields">
                        <div class="sixteen wide field">
                            <div class="field">
                                <input class="ui fluid large teal submit button" type="submit" value="update">
                            </div>
                        </div>

                    </div>
                    <%
                        if(staffDetails == null)
                        {
                            out.println("<p style='color: red'>Please refresh the page.</p>");
                        }
                    %>
                </form>
            </div>
    </div>
    </div>
</body>
</html>
