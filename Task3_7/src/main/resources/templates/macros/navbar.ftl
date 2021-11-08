<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">User Management</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav ml-auto ml-5">
            <li class="form-inline mt-2 mt-md-0">
                <a id="login" class="btn btn-outline-light my-2 my-sm-0" href="/login" role="button">Login</a>
            </li>
            <li id="log_out" class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Account</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="/profile">My Profile</a>
                    <a class="dropdown-item" id="logOut">Log out</a>
                </div>
            </li>
        </ul>
    </div>
</nav>