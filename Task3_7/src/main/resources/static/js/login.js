function signIn() {
    var user = {
        email: $('#input_email').val(),
        password: $('#input_password').val()
    };
    $.ajax({
        type: "POST",
        url: '/api/v1/auth/login',
        data: JSON.stringify(user),  //Data sent to server
        contentType: 'application/json'
    })
        .done(function (response) {
            var token = response.token;
            var username = response.username;
            var roles=response.roles;

            localStorage.setItem("token", token);
            localStorage.setItem("email", username);
            localStorage.setItem("roles",JSON.stringify(roles));
            console.log(localStorage.getItem("token"));
            $(location).attr('href', 'http://localhost:8080/profile');
        })
        .fail(function (err) {
            document.getElementById('invalid_credentials').innerHTML = "Oops! Username or password are incorrect.";
            $('#input_email').val(null);
            $('#input_password').val(null);
            console.log(err)
        });
}