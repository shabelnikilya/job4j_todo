function validate() {
    let rsl = true;
    $('#form').find('input').each(function() {
        if ($(this).val() === '') {
            alert($(this).attr('title'));
            rsl = false;
        }
    })
    return rsl;
}