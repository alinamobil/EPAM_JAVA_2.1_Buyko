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
    <style>
        tr,
        td,
        thead,
        tbody {
            border: 1px solid black;
        }

        .alert {
            display: flex;
            flex-direction: column;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="alert alert-info text-center mx-auto">
        ${action}
        <form action="back" method="post" autocomplete="off">
            <input type="submit" class="btn btn-primary" name="submit" value="${submit}">
            <input type="hidden" name="id_cl_act" value="${id_act}">
        </form>
    </div>
</div>
</body>
</html>
