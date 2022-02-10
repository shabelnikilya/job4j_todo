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
    <link rel="stylesheet" type="text/css" href="tableList.css">
    <link rel="stylesheet" type="text/css" href="addButton.css">
    <link rel="stylesheet" type="text/css" href="buttonStatus.css">
    <link rel="stylesheet" type="text/css" href="hideForm.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>TODO</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
<script>
    $(document).ready(function() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/job4jtodo/list',
            dataType: 'json'
        }).done(function (data) {
            for (var item of data) {
                var done = item.done ? '&#10004' : '&#10008';
                $('#items').append(
                                    `<tr>
                                    <th>${item.description}</th>
                                    <th style="text-align:center">${item.created}</th>
                                    <th style="text-align:center">
                                    <button type="submit" class="btn" id="${item.id}" onclick="update(${item.id});">${done}</th>
                                    </tr>`
                )
            };
        }).fail(function (err) {
            console.log(err);
        })
    })
</script>
<script>
    function sendItem() {
        if (!validate()) {
            return false;
        }
        console.log($('#items').html() !== false)
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/job4jtodo/addItem',
                data: 'description=' + $('#descriptionItem').val(),
                dataType: 'json'
            }).done(function (data) {
                $('#items tr:last').append(`<tr>
                                    <th>${data.description}</th>
                                    <th style="text-align:center">${data.created}</th>
                                    <th style="text-align:center">
                                    <button type="submit" class="btn" id="${item.id}" onclick="update(${item.id});">&#10008</th>
                                    </tr>`)
            }).fail(function (err) {
                console.log(err);
            });
        return true;
    }
</script>
<script>
    function filter() {
        $('#items').empty();
        if (!document.getElementById("check").checked) {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/job4jtodo/filterItem',
                dataType: 'json'
            }).done(function (data) {
                for (var item of data) {
                    var done = item.done ? '&#10004' : '&#10008';
                    $('#items').append(
                                    `<tr>
                                    <th>${item.description}</th>
                                    <th style="text-align:center">${item.created}</th>
                                    <th style="text-align:center">
                                    <button type="submit" class="btn" id="${item.id}" onclick="update(${item.id});">${done}</th>
                                    </tr>`
                    )
                };
            }).fail(function (err) {
                console.log(err);
            })
        } else {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/job4jtodo/list',
                dataType: 'json'
            }).done(function (data) {
                for (var item of data) {
                    var done = item.done ? '&#10004' : '&#10008';
                    $('#items').append(
                                    `<tr>
                                    <th>${item.description}</th>
                                    <th style="text-align:center">${item.created}</th>
                                    <th style="text-align:center">
                                    <button type="submit" class="btn" id="${item.id}" onclick="update(${item.id});">${done}</th>
                                    </tr>`
                    )
                };
            }).fail(function (err) {
                console.log(err);
            })
        }
    }
</script>
<script>
    function update(idItem) {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/job4jtodo/updateStatus?id=' + idItem,
                dataType: 'json'
            }).done(function (data) {
                    document.getElementById(idItem).innerHTML = data.done ? '&#10004' : '&#10008';
            }).fail(function (err) {
                console.log(err);
            });
    }
</script>
<script>
    function validate() {
        var rsl = true;
        $('#form').find('input').each(function() {
            if ($(this).val() === '') {
                alert($(this).attr('title'));
                rsl = false;
            }
        })
        return rsl;
    }
</script>
<body>
<h1 align="center" style="font-style:oblique">Список TODO</h1>
    <details>
        <summary>Добавить задачу</summary>
        <p style="font-size: 130%" align="center">Заполните поле 'Описание' для добавления задачи</p>
            <form id="form" method="post" >
                <div class="form-group" align="center">
                    <input type="text" placeholder="Описание" id="descriptionItem"
                        title="Заполните поле - Описание, если хотите добавить задачу" class="form-control"
                        name="description" style="width: 70%">
                </div>
                <button type="submit" class="bot3" onclick="return sendItem()">Добавить</button>
            </form>
    </details>
    <div class="checkbox" style="position:absolute; right:180px; font-size: 120%; color: black">
        <input type="checkbox" checked="checked" id="check" onclick="filter();">
        <label for="check" class="text">Показать все задачи?</label>
    </div>
    <br><table align="center">
        <thead>
            <tr>
                <th width="50%" style="text-align:center">Описание</th>
                <th width="30%" style="text-align:center">Дата</th>
                <th width="20%" style="text-align:center">Отметка о выполнении</th>
            </tr>
        </thead>
        <tbody id="items">
        </tbody>
    </table>
</body>
</html>

