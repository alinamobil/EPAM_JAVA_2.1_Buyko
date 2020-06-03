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
        <h3>${greeting}</h3>
        <form action="client_action" method="post" autocomplete="off">
            <p><input type="radio" name="action_cl" value="create"> Create an order</p>
            <p><input type="radio" name="action_cl" value="show"> Show my orders</p>
            <p><input type="radio" name="action_cl" value="add"> Add dishes to order</p>
            <p><input type="radio" name="action_cl" value="pay"> Pay for order</p>
            <input type="hidden" name="id_cl" value="${id}">
            <input type="submit" class="btn btn-primary" value="Choose">
        </form>
        <p class="mb-0"><a href="/restaurant_war_exploded">Main Page</a></p>
    </div>
</div>
</body>
</html>
