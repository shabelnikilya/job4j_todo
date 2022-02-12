function update(idItem, doneItem) {
    let out = !doneItem ? '&#10004' : '&#10008';
    document.getElementById(idItem).innerHTML =
        '<button type="submit" class="btn" ' +
        'onclick="update(' + idItem + ', ' + !doneItem + ');">' + out + '</button>';
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/job4jtodo/updateStatus',
        data: {
            id: idItem,
            done: doneItem
        },
    }).fail(function (err) {
        console.log(err);
    });
}