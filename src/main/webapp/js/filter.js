function filter() {
    $('#items').empty();
    if (!document.getElementById("check").checked) {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/job4jtodo/filterItem',
            dataType: 'json'
        }).done(function (data) {
            let result = '';
            for (let item of data) {
                for (let categ of item.categories.values()) {
                    result = result + categ.name + '<br>';
                }
                let done = item.done ? '&#10004' : '&#10008';
                $('#items').append(
                    `<tr>
                                    <th>${item.description}</th>
                                    <th style="text-align:center">${item.user.email}</th>
                                     <th style="text-align:center">${result}</th>
                                    <th style="text-align:center">${item.created}</th>
                                    <th style="text-align:center" id="${item.id}">
                                    <button type="submit" class="btn" onclick="update(${item.id}, ${item.done});">${done}</th>
                                    </tr>`
                )
                result = '';
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
            let result = '';
            for (let item of data) {
                for (let categ of item.categories.values()) {
                    result = result + categ.name + '<br>';
                }
                let done = item.done ? '&#10004' : '&#10008';
                $('#items').append(
                    `<tr>
                                    <th>${item.description}</th>
                                    <th style="text-align:center">${item.user.email}</th>
                                    <th style="text-align:center">${result}</th>
                                    <th style="text-align:center">${item.created}</th>
                                    <th style="text-align:center" id="${item.id}">
                                    <button type="submit" class="btn" onclick="update(${item.id}, ${item.done});">${done}</th>
                                    </tr>`
                )
                result = '';
            }
        }).fail(function (err) {
            console.log(err);
        })
    }
}