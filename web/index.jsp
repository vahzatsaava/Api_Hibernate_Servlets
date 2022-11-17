<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.11.2022
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <p> Hello Man: <%= new Date(System.currentTimeMillis()).toString() %>>
  </p>

  <form method="get" action="getAll">
    <input type="submit" value="get All Users"> <br>
  </form>
  <form method="get" action="find">
    <label><input type="number" name="id"></label><br>
    <input type="submit" value="find"> <br>
  </form>
  <form method="post" action="delete">
    <label><input type="number" name="remove"></label><br>
    <input type="submit" value="delete"> <br>
  </form>
  <form  method="post" action="put">
    <label><input type="text" name="first_name"></label><br>
    <label><input type="text" name="last_name"></label><br>
    <input type="submit" value="put value"> <br>

  </form>
  <form  method="post" action="update">
    <label><input type="number" name="id"></label><br>
    <label><input type="text" name="first"></label><br>
    <label><input type="text" name="last"></label><br>
    <input type="submit" value="update value"> <br>

  </form>
  </body>
</html>
