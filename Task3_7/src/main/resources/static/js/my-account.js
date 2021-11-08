var global_userData = null;
$(document).ready(function () {

    toggleElements(true);
    var token = localStorage.getItem('token');
    if (token) {
        $.ajaxSetup({
            headers: {'Authorization': 'Bearer_' + token}
        });
    }

    openUserProfilePage();

    $("#editUserProfile").on({
        mouseenter: function () {
            $(this).css("background-color", "lightgray");
        },
        mouseleave: function () {
            $(this).css("background-color", "lightblue");
        },
        click: function () {
            toggleElements(false);
        }
    });

    $("#saveUserProfile").on({
        click: function () {
            saveUserProfile();
            toggleElements(true);
            $("#editUserProfile").css("background-color", "lightgray");
        }
    });

    $("#cancelUserProfile").on({
        click: function () {
            toggleElements(false);
            openUserProfilePage();
        }
    });

});

function toggleElements(flag) {

    $('#userName').attr("readOnly", flag);
    $('#userSurname').attr("readOnly", flag);

    if (flag) {
        $('#editUserProfile').show();
        $('#cancelUserProfile, #saveUserProfile').hide();
    } else {
        $('#editUserProfile').hide();
        $('#cancelUserProfile, #saveUserProfile').show();
    }
}


function saveUserProfile() {

    var email = localStorage.getItem('email');

    var user = {
        id: $('#userId').val(),
        firstName: $('#userName').val(),
        lastName: $('#userSurname').val(),
    };

    $.ajax({

        url: '/api/v1/profile/' + email,
        type: 'PUT',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(user),

        success: function (data) {
            console.log("update success: " + data.companyName);
            console.log("success: " + data.firstName);
        },

        error: function (jqXHR) {
            if (jqXHR.status === 200) {
                $('#invalid_userName').hide();
                $('#invalid_userSurname').hide();

            } else if (jqXHR.status === 400) {

                var resultText = jqXHR.responseText;
                var jsonResponse = JSON.parse(resultText);
                var obj = jsonResponse["errors"];
                if (obj.firstName) {
                    $('#invalid_userName').show();
                    document.getElementById('invalid_userName').innerHTML = obj.firstName[0];
                } else {
                    $('#invalid_userName').hide();
                }
                if (obj.lastName) {
                    $('#invalid_userSurname').show();
                    document.getElementById('invalid_userSurname').innerHTML = obj.lastName[0];
                }
            }
        }
    });
}


function openUserProfilePage() {
    var email = localStorage.getItem('email');
    console.log("openUserProfilePage email: " + email);
    $.ajax({
        type: 'GET',
        url: '/api/v1/profile/' + email,
        data: {},
        dataType: 'json',
        success: function (data) {
            global_userData = data;
            fillForm(global_userData);
        },
        error: function (jqXHR, textStatus, errorThrown) {
        }
    });
}

function fillForm(data) {

    if (data) {

        $('.userInput.id').val(data.id);
        $('.userInput.name').val(data.firstName);
        $('.userInput.surname').val(data.lastName);
    }
}

function getCreatedEventsList() {
    var token = localStorage.getItem('token');
    var email = localStorage.getItem('email');
    $.ajax({
        type: 'GET',
        beforeSend: function (request) {
            request.setRequestHeader("Authority", 'Bearer_' + token);
        },
        url: '/api/v1/events/author/' + email,
        data: {},
        dataType: 'json',
        success: function (data) {
            var eventsList = document.querySelector("#eventsList");
            eventsList.innerHTML = "";
            data.forEach(function (element) {
                eventsList.innerHTML += '' +
                    '        <div class="col-md-4">\n' +
                    '            <div class="card mb-4">\n' +
                    '                <div class="card-body">\n' +
                    '                    <h5 class="card-title">' + element.name + '</h5>\n' +
                    '                    <p class="card-text">' + element.tags + '&emsp;&emsp;Date: ' + element.startDateTime + '</p>\n' +
                    '                    <p class="card-text">Level: ' + element.level + '&emsp;&emsp;Type: ' + element.type + '</p>\n' +
                    '                    <img style="width: 90%; height: 70%;" src="/uploads/' + element.eventImageName + '" alt="main page picture"/>\n' +
                    '                    <div style="display: inline;">\n' +
                    '                        <div >\n' +
                    '                       <form method="post" enctype="multipart/form-data">\n' +
                    '                            <div id="fileImage" class="form-row">\n' +
                    '                                <input style="width: 100%;" id="my-input" type="file" name="file" class="btn btn-primary"/></br></br>\n' +
                    '                                <button type="button" onclick=\'addUploadedFile(' + JSON.stringify({id: element.id}) + ', this)\' class="btn btn-outline-success mb-4">Save</button>' +
                    '                                <div class="mx-auto">' + showLinkToAttachmentFile(element.attachmentFilename) + '</div>\n' +
                    '                            </div>' +
                    '                        </form>\n' +
                    '                       </div>' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>';
            });
        },
        error: function (data, textStatus, errorThrown) {
        }
    });
}

function showLinkToAttachmentFile(data) {
    if (data == null) {
        return '';
    } else {
        return '<a href="/uploads/' + data + '" download><img height="70" width="100" src="images/download.jpg" alt=""/></a>';
    }
}

function getUserEvents() {
    var token = localStorage.getItem('token');
    var email = localStorage.getItem('email');
    $.ajax({
        type: 'GET',
        beforeSend: function (request) {
            request.setRequestHeader("Authority", 'Bearer_' + token);
        },
        url: '/api/v1/events/user/' + email,
        data: {},
        dataType: 'json',
        success: function (data) {
            var eventsList = document.querySelector("#userEventsList");
            eventsList.innerHTML = "";
            data.forEach(function (element) {
                eventsList.innerHTML += '' +
                    '        <div class="col-md-4">\n' +
                    '            <div class="card mb-4">\n' +
                    '                <div class="card-body">\n' +
                    '                    <h5 class="card-title">' + element.name + '</h5>\n' +
                    '                    <p class="card-text">' + element.tags + '&emsp;&emsp;Date: ' + element.startDateTime + '</p>\n' +
                    '                    <p class="card-text">Level: ' + element.level + '&emsp;&emsp;Type: ' + element.type + '</p>\n' +
                    '                    <div class="card-footer bg-transparent border-success">\n' +
                    '                       <button type="submit" onclick=\'deleteUserEvent(' + JSON.stringify({id: element.id, email: email}) + ')\' class="btn btn btn-warning">Delete</button>' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '            </div>\n' +
                    '        </div>';
            });
        },
        error: function (data, textStatus, errorThrown) {
        }
    });
}

function addUploadedFile({id}, el) {

    var file = $(el).parents('form').find('input[type="file"]').get(0).files[0];

    var formData = new FormData();
    formData.append("file", file);
    console.log('id = ' + id);
    $.ajax({
        url: '/api/v1/events/event/' + id,
        type: "POST",
        processData: false,
        contentType: false,
        async: false,
        cache: false,
        data: formData,
        success: function () {
            console.log("successfully");
            $(location).attr('href', 'http://localhost:8080/profile');
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}


function openProfileSettingsPage() {
    $.ajax({
        type: 'GET',
        url: '/api/v1/profile/settings',
        data: {},
        success: function (data) {
        },
        error: function (jqXHR, textStatus, errorThrown) {
        },
        dataType: "json"
    });
}



