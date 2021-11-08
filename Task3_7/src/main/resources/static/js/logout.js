$(document).ready(function () {
    $('#logOut').click(function () {
        localStorage.removeItem("token");
        localStorage.removeItem("email");
        $(location).attr('href', 'http://localhost:8080/');
    });

    var token = localStorage.getItem('token');
    if (token) {
        $('#log_out').show();
        $('#login').hide();
        $.ajaxSetup({
            headers: {'Authorization': 'Bearer_' + token}
        })
    } else {
        $('#log_out').hide();
        $('#login').show();
    }
});