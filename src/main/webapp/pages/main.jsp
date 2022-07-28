<%@ page import="com.models.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> staffListObject = (List<User>) session.getAttribute("staffObjectList");
    Integer registerResult = (Integer) session.getAttribute("result");
    Integer userid = (Integer) session.getAttribute("userid");
    String origPath = request.getContextPath();
    session.setAttribute("path", "main.jsp");
    session.setAttribute("userid", userid);
%>

<html>
<head>
    <jsp:include page="../header.html"/>
    <script>
        window.onload = function() {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "<%=origPath%>/staff", true);
            xhttp.send();
        }
        function onDeleteAction(id){
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "<%=origPath%>/staff/delete?id=" + id, true);
            xhttp.send();
        }
    </script>
    <title>Title</title>
</head>
<body>
    <jsp:include page="component/header.jsp"/>
    <div class="ui middle aligned center aligned grid" style="margin-top: 20px">
        <form class="ui form" action="<%= request.getContextPath() %>/staff/register" method="POST">
            <h2>Add New Staff</h2>
            <div class="fields">
                <div class="five wide field">
                    <label for="inputUsername">Username</label>
                    <input name="username" type="text" id="inputUsername"  placeholder="Username" required="required">
                </div>
                    <div class="five wide field">
                    <label for="inputPassword">Password</label>
                    <input name="password" type="password" id="inputPassword" placeholder="Password" required="required">
                </div>
                <div class="five wide field">
                    <label for="inputEmail">Email</label>
                    <input name="email" type="text" id="inputEmail" placeholder="Email" required="required">
                </div>
                <div class="five wide field">
                    <label for="inputTelNo">Tel No.</label>
                    <input onchange="onNameChanged()" name="telno" type="text" id="inputTelNo" placeholder="Password" required="required">
                </div>
            </div>
            <div class="fields">
                <div class="ten wide field">
                    <label>Date of Birth</label>
                    <div class="three fields">
                        <div class="field">
                            <input type="text" name="inputDay" maxlength="2" placeholder="Day">
                        </div>
                        <div class="field">
                            <select class="ui fluid search dropdown" name="inputMonth">
                                <option value="1">January</option>
                                <option value="2">February</option>
                                <option value="3">March</option>
                                <option value="4">April</option>
                                <option value="5">May</option>
                                <option value="6">June</option>
                                <option value="7">July</option>
                                <option value="8">August</option>
                                <option value="9">September</option>
                                <option value="10">October</option>
                                <option value="11">November</option>
                                <option value="12">December</option>
                            </select>
                        </div>
                        <div class="field">
                            <input type="text" name="inputYear" maxlength="4" placeholder="Year">
                        </div>
                    </div>
                </div>
                <div class="five wide field">
                    <label for="inputAddress">Address</label>
                    <input name="address" type="text" id="inputAddress" placeholder="Address" required="required">
                </div>
                <div class="two wide field">
                    <label>Gender</label>
                    <select class="ui fluid search dropdown" name="gender">
                        <option value="M">Male</option>
                        <option value="F">Female</option>
                    </select>
                </div>
            </div>
            <div class="fields">
                <div class="three wide field">
                    <div class="field">
                        <input class="ui button" type="submit" value="submit">
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="fields">
            <form class="ui form" action="<%= request.getContextPath() %>/staff" method="POST" style="margin: 30px 50px 0">
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

                <table class="ui celled table" style="margin-bottom: 80px">
                    <thead>
                    <tr>
                        <th></th>
                        <th>id</th>
                        <th>name</th>
                        <th>email</th>
                        <th>date of birth</th>
                        <th>gender</th>
                        <th>address</th>
                        <th>tel no</th>
                        <th>creation date</th>
                    </tr></thead>
                    <tbody>
                    <%
                        String str = request.getContextPath();

                        if(staffListObject != null){
                            for( User user : staffListObject)
                            {
                                out.println("<form action='" + str + "/staff/admin-update?userid=' method='POST'>");
                                out.println("<tr>");
                                out.println("<td> " +
                                        "<div class='two fields'> " +
                                        "<div class=field>" +
                                        "<button style='background: transparent; border: none; cursor: pointer; color: #4183c4' onclick='onDeleteAction(" + user.getUserid() + ")'>delete</button> " +
                                        "</div> " +
                                        "<div class=field>" +
                                        "<input background: transparent; border: none; cursor: pointer; color: #4183c4' type='submit' value='update'/>" +
                                        "</div>" +
                                        "</div>" +
                                        "</td>");
                                out.println("<td>" + user.getUserid() + "</td>");
                                out.println("<td> <input type='text' name='username' value='" + user.getName() + "'/></td>");
                                out.println("<td> <input type='text' name='email' value='" + user.getEmail() + "' /></td>");
                                out.println("<td> <input type='text' name='dob' value='" + user.getDob() + "'/></td>");
                                out.println("<td> <input type='text' name='gender' value='" + user.getGender() + "'/></td>");
                                out.println("<td> <input type='text' name='address' value='" + user.getAddress() + "'/></td>");
                                out.println("<td> <input type='text' name='telno' value='" + user.getTelno() + "'/></td>");
                                out.println("<td>" + user.getCreationDate() + "</td>");
                                out.println("</tr>");
                                out.println("</form>");
                            }
                        }
                    %>
                    </tbody>
                </table>
            </form>
<%--    <button onclick="buildHtmlTable(<%=session.getAttribute("")%>)">reload</button>--%>

        </div>
</body>
</html>
