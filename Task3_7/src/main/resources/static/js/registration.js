function addUser() {
    var user = {
        firstName: $('#input_first_name').val(),
        lastName: $('#input_last_name').val(),
        email: $('#input_email').val(),
        password: $('#input_password').val()
    };

    fetch('/api/v1/signup', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(user)
    }).then(
        function (response) {
            if (response.status >= 200 && response.status <= 300) {
                location.href = "/login";
            }
            if (response.status === 400) {
                response.json().then(function (data) {
                    if (data.errors.firstName) {
                        $('#invalid_first_name').show();
                        document.getElementById('invalid_first_name').innerHTML = data.errors.firstName;
                    } else {
                        $('#invalid_first_name').hide();
                    }
                    if (data.errors.lastName) {
                        $('#invalid_last_name').show();
                        document.getElementById('invalid_last_name').innerHTML = data.errors.lastName;
                    } else {
                        $('#invalid_last_name').hide();
                    }
                    if (data.errors.email) {
                        $('#invalid_email').show();
                        document.getElementById('invalid_email').innerHTML = data.errors.email;
                    } else {
                        $('#invalid_email').hide();
                    }
                    if (data.errors.password) {
                        $('#invalid_password').show();
                        document.getElementById('invalid_password').innerHTML = data.errors.password;
                    } else {
                        $('#invalid_password').hide();
                    }
                })
            }
        }
    )
}