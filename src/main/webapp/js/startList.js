$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4jtodo/list',
        dataType: 'json'
    }).done(function (data) {
        for (let item of data) {
            let done = item.done ? '&#10004' : '&#10008';
            $('#items').append(
                `<tr>
                                    <th>${item.description}</th>
                                    <th style="text-align:center">${item.user.email}</th>
                                    <th style="text-align:center">${item.created}</th>
                                    <th style="text-align:center" id="${item.id}">
                                    <button type="submit" class="btn" onclick="update(${item.id}, ${item.done});">${done}</th>
                                    </tr>`
            )
        }
    }).fail(function (err) {
        console.log(err);
    })
})




