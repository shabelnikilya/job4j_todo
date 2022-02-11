function filter() {
    $('#items').empty();
    if (!document.getElementById("check").checked) {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/job4jtodo/filterItem',
            dataType: 'json'
        }).done(function (data) {
            for (let item of data) {
                let done = item.done ? '&#10004' : '&#10008';
                $('#items').append(
                    `<tr>
                                    <th>${item.description}</th>
                                    <th style="text-align:center">${item.created}</th>
                                    <th style="text-align:center">
                                    <button type="submit" class="btn" id="${item.id}" onclick="update(${item.id});">${done}</th>
                                    </tr>`
                )
            }
        }).fail(function (err) {
            console.log(err);
        })
    } else {
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
                                    <th style="text-align:center">${item.created}</th>
                                    <th style="text-align:center">
                                    <button type="submit" class="btn" id="${item.id}" onclick="update(${item.id});">${done}</th>
                                    </tr>`
                )
            }
        }).fail(function (err) {
            console.log(err);
        })
    }
}