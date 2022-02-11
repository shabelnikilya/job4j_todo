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