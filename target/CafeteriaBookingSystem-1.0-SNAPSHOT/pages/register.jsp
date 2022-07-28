<%--
  Created by IntelliJ IDEA.
  User: limwa
  Date: 7/21/2022
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../header.html"/>
    <title>Title</title>
    <script>
        function doBack(){
            window.location.assign("<%= request.getContextPath() %>/index.jsp");
        }
    </script>
</head>
<body>
<div class="ui middle aligned center aligned grid" style="margin-top: 20px">
    <div class="fields">
        <div class="field">
            <form class="ui form" action="<%= request.getContextPath() %>/customer/register" method="POST">
                <h2>Register New Customer</h2>
                <div class="fields">
                    <div class="eight wide field">
                        <label for="inputUsername">Username</label>
                        <input name="username" type="text" id="inputUsername"  placeholder="Username" required="required">
                    </div>
                    <div class="eight wide field">
                        <label for="inputPassword">Password</label>
                        <input name="password" type="password" id="inputPassword" placeholder="Password" required="required">
                    </div>

                </div>
                <div class="fields">
                    <div class="eight wide field">
                        <label for="inputEmail">Email</label>
                        <input name="email" type="text" id="inputEmail" placeholder="Email" required="required">
                    </div>
                    <div class="eight wide field">
                        <label for="inputTelNo">Tel No.</label>
                        <input name="telno" type="text" id="inputTelNo" placeholder="Password" required="required">
                    </div>
                </div>
                <div class="fields">
                    <div class="sixteen wide field">
                        <label for="inputAddress">Address</label>
                        <input name="address" type="text" id="inputAddress" placeholder="Address" required="required">
                    </div>
                </div>
                <div class="fields">
                    <div class="sixteen wide field">
                        <label>Date of Birth</label>
                        <div class="sixteen wide field">
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
                </div>
                <div class="fields">
                    <div class="sixteen wide field">
                        <label>Gender</label>
                        <select class="ui fluid search dropdown" name="gender">
                            <option value="M">Male</option>
                            <option value="F">Female</option>
                        </select>
                    </div>
                </div>
                <div class="fields">
                    <div class="sixteen wide field">
                        <div class="field">
                            <input class="ui fluid large teal submit button" type="submit" value="submit">
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="field">
            <div class="one wide field">
                <button class="ui fluid large teal submit button" onclick="doBack();">Back</button>
            </div>
        </div>

    </div>
</div>
</body>
</html>
