<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 01.06.2020
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>French Restaurant</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<div class="row">
    <div class="alert alert-info text-center mx-auto">
        <form action="login" method="post">
            <div class="form-group">
                <label for="exampleInputName1">Nickname</label>
                <input type="text" class="form-control" id="exampleInputName1" aria-describedby="emailHelp" placeholder="Enter your Nickname" name="name">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password">
            </div>
            <input type="submit" class="btn btn-primary" value="Login">
        </form>
    </div>
</div>
</body>
</html>
