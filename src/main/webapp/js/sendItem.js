function sendItem(user) {
    if (!validate()) {
        return false;
    } else if (user) {
        alert("Вы не прошли авторизацию. Войдите в профиль!")
        return false;
    }
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/job4jtodo/addItem',
        data: {
            'description': $('#descriptionItem').val(),
             'ids' : $('#catIds').val()
        },
        dataType: 'json'
    }).done(function (data) {
        let resultData = '';
        for (let categ of data.categories.values()) {
            resultData = resultData + categ.name + '<br>';
        }
        $('#items tr:last').append(`<tr>
                                    <th>${data.description}</th>
                                    <th style="text-align:center">${data.user.email}</th>
                                    <th style="text-align:center">${resultData}</th>
                                    <th style="text-align:center">${data.created}</th>
                                    <th style="text-align:center" id="${data.id}">
                                    <button type="submit" class="btn" onclick="update(${data.id}, ${data.done});">&#10008</th>
                                    </tr>`)
    }).fail(function (err) {
        console.log(err);
    });
    resultData = '';
    return true;
}