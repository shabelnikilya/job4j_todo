<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/addButton.css">
    <script type="text/javascript" src="js/valid.js"></script>
</head>
<body>
<h1 align="center">Заполните данные для регистрации</h1>
<form action="<%=request.getContextPath()%>/saveUser" id="form" method="post" >
    <div class="form-group" align="center">
        <label style="font-size: 130%">Имя</label>
        <input type="text" placeholder="Имя" title="Заполните поле - Имя" class="form-control"
               name="name" style="width: 20%">
    </div>
    <div class="form-group" align="center">
        <label style="font-size: 130%">Фамилия</label>
        <input type="text" placeholder="Фамилия" title="Заполните поле - Фамилия" class="form-control"
               name="secondName" style="width: 20%">
    </div>
    <div class="form-group" align="center">
        <label style="font-size: 130%">Почта</label>
        <input type="email" placeholder="Email" title="Заполните поле - email" class="form-control"
               name="email" style="width: 20%">
    </div>
    <div class="form-group" align="center">
        <label style="font-size: 130%">Пароль</label>
        <input type="password" placeholder="Password" title="Заполните поле - password" class="form-control"
               name="password" style="width: 20%">
    </div>
    <div align="center"><button type="submit" class="bot3" onclick="return validate()">Зарегестрироваться</button></div>
</form>
</body>
</html>
