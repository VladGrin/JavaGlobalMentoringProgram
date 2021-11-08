<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>

<div class="container" style="margin-top: 30px;">
    <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto">
            <div class="card card-signin flex-row my-5">
                <div class="card-img-left d-none d-md-flex">
                </div>
                <div class="card-body">
                    <h5 class="card-title text-center">Registration</h5>
                    <div class="form-signin">
                        <div class="form-label-group">
                            <input type="text" id="input_first_name" class="form-control" placeholder="Email address"
                                   required>
                            <label for="input_first_name">Name</label>
                            <small id="invalid_first_name" class="text-danger"></small>

                        </div>
                        <div class="form-label-group">
                            <input type="text" id="input_last_name" class="form-control" placeholder="Password"
                                   required>
                            <label for="input_last_name">Surname</label>
                            <small id="invalid_last_name" class="text-danger"></small>
                        </div>
                        <hr>
                        <div class="form-label-group">
                            <input type="email" id="input_email" class="form-control" placeholder="Email"
                                   required>
                            <label for="input_email">Email address</label>
                            <small id="invalid_email" class="text-danger"></small>
                        </div>
                        <div class="form-label-group">
                            <input type="password" id="input_password" class="form-control" placeholder="Password"
                                   required>
                            <label for="input_password">Password</label>
                            <small id="invalid_password" class="text-danger"></small>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit"
                                onclick="addUser()">Register
                        </button>
                        <a class="d-block text-center mt-2 small" href="/login">Have account</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/registration.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>