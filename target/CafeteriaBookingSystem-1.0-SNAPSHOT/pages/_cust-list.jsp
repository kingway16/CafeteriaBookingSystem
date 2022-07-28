<%@ page import="com.models.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> custListObject = (List<User>) session.getAttribute("CustomerList");
    String origPath = (String) request.getContextPath();
    session.setAttribute("path", "cust_list.jsp");
%>
<html>
<head>
    <jsp:include page="../header.html"/>
    <title>Title</title>
    <script>
        window.onload = function() {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "<%=request.getContextPath() %>/customer", true);
            xhttp.send();
        }
        function onDeleteAction(id){
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "<%=origPath%>/customer/delete?id=" + id, true);
            xhttp.send();
        }
    </script>
</head>
<body>
    <jsp:include page="component/header.jsp"/>

    <form class="ui form" action="<%= request.getContextPath() %>/customer" method="POST" style="margin: 30px 50px 0">
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

        <table class="ui celled table">
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
               if(custListObject != null){
                   for( User user : custListObject)
                   {
                       out.println("<tr>");
                       out.println("<td> " +
                               "<div class='two fields'> " +
                               "<div class=field>" +
                               "<button style='background: transparent; border: none; cursor: pointer; color: #4183c4' onclick='onDeleteAction(" + user.getUserid() + ")'>delete</button> " +
                               "</div> " +
                               "<div class=field>" +
                               "<button style='background: transparent; border: none; cursor: pointer; color: #4183c4' onclick='onEditAction(" + user.getUserid() + ")'> edit </button> " +
                               "</div>" +
                               "</div>" +
                               "</td>");
                       out.println("<td>C00" + user.getUserid() + "</td>");
                       out.println("<td>" + user.getName() + "</td>");
                       out.println("<td>" + user.getEmail() + "</td>");
                       out.println("<td>" + user.getDob() + "</td>");
                       out.println("<td> <select class='ui fluid search dropdown' name='gender' value='" + user.getGender() + "'>" +
                               "<option value='M'>Male</option> " +
                               "<option value='F'>Female</option> " +
                               "</td>");
                       out.println("<td>" + user.getAddress() + "</td>");
                       out.println("<td>" + user.getTelno() + "</td>");
                       out.println("<td>" + user.getCreationDate() + "</td>");
                       out.println("</tr>");
                   }
               }
           %>
            </tbody>
        </table>
    </form>
</body>
</html>
