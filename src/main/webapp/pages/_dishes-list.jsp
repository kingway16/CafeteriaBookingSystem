<%@ page import="com.models.Dish" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: limwa
  Date: 7/25/2022
  Time: 1:27 AM
  To change this template use File | Settings | File Templates.
--%>
<% List<Dish> dishesList = (List<Dish>) session.getAttribute("dishesList");%>
<% session.setAttribute("path", "_dishes-list.jsp");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        window.onload = function()
        {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "<%= request.getContextPath() %>/dish/get", false);
            xhttp.send();
        }
        function onDeleteAction(id)
        {
            const xhttp = new XMLHttpRequest();
            xhttp.open("POST", "<%= request.getContextPath() %>/dish/delete?id=" + id.toString(), false);
            xhttp.send();
        }
    </script>
</head>
<body>
    <jsp:include page="component/header.jsp"/>
    <div class="ui middle aligned center aligned grid" style="margin-top: 20px">
        <form class="ui form" action="<%= request.getContextPath() %>/dish/create" method="POST">
            <h2>Create New Dishes</h2>
            <div class="fields">
                <div class="five wide field">
                    <label for="inputDishName">dish name</label>
                    <input name="dishName" type="text" id="inputDishName"  placeholder="name" required="required">
                </div>
                <div class="five wide field">
                    <label for="inputIngredients">ingredients</label>
                    <input name="ingredients" type="text" id="inputIngredients" placeholder="ingredients" required="required">
                </div>
                <div class="five wide field">
                    <label for="inputTotal">total</label>
                    <input name="total" type="number" id="inputTotal" placeholder="total" required="required">
                </div>
                <div class="five wide field">
                    <div class="three wide field">
                        <div class="field">
                            <input class="ui button" type="submit" value="submit">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="fields">
        <form class="ui form" action="<%= request.getContextPath() %>/dish/get" method="POST" style="margin: 30px 50px 0">
            <div class="two fields">
                <div class="field">
                    <input class="ui button" type="submit" value="refresh">
                </div>
            </div>
            <div class="field">
                <table class="ui celled table">
                    <thead>
                        <tr>
                            <th></th>
                            <th>dish id</th>
                            <th>dish name</th>
                            <th>ingredients</th>
                            <th>price</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        if(dishesList != null){
                            for( Dish dish : dishesList)
                            {
                                out.println("<tr>");
                                out.println("<td> " +
                                        "<button style='background: transparent; border: none; cursor: pointer; color: #4183c4' onclick='onDeleteAction(" + dish.getId() + ")'>delete</button> " +
                                        "</td>");
                                out.println("<td>" + "D00" + dish.getId() + "</td>");
                                out.println("<td>" + dish.getName() + "</td>");
                                out.println("<td>" + dish.getIngredients() + "</td>");
                                out.println("<td>" + dish.getPrice() + "</td>");
                                out.println("</tr>");
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
    </div>
</body>
</html>
