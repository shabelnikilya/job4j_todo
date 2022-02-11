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
                                    <button type="submit" class="btn" id="${data.id}" onclick="update(${data.id});">&#10008</th>
                                    </tr>`)
    }).fail(function (err) {
        console.log(err);
    });
    return true;
}