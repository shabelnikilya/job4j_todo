$(document).ready(function() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/job4jtodo/allCategories',
        dataType: 'json'
    }).done(function (data) {
        for (let category of data) {
            $('#catIds').append(
                `<option value="${category.id}">${category.name}</option>`
            )
        }
    }).fail(function (err) {
        console.log(err);
    })
})




