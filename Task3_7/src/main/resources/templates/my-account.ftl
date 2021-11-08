<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/profile-page-style.css">
    <title>My account</title>
</head>
<body>

<#include "macros/navbar.ftl"/>

<div class="tab">
    <button class="tablinks" onclick="openMenuItem(event, 'homeMenuItem')" id="defaultOpen">Home</button>
    <button class="tablinks" onclick="openMenuItem(event, 'profileMenuItem')">Profile</button>
    <button class="tablinks" onclick="openMenuItem(event, 'accountSettingsMenuItem')">Account settings</button>
</div>

<div id="homeMenuItem" class="tabcontent">
    <h3>Home</h3>
</div>

<div id="profileMenuItem" class="tabcontent">
    <h3>Profile</h3>

    <input type="hidden" class="userInput id" id="userId">

    <label for="userName" class="userInput">Name</label>
    <input type="text" class="userInput name" id="userName" readOnly>
    <small id="invalid_userName" class="text-danger"></small>
    <br/>

    <label for="userSurname" class="userInput">Surname</label>
    <input type="text" class="userInput surname" id="userSurname" readOnly>
    <small id="invalid_userSurname" class="text-danger"></small>
    <br/>

    <input type="submit" class="userProfileButton" id="cancelUserProfile" value="Cancel">
    <input type="submit" class="userProfileButton" id="saveUserProfile" value="Save">
    <input type="submit" class="userProfileButton" id="editUserProfile" value="Edit">

</div>

<div id="accountSettingsMenuItem" class="tabcontent">
    <h3>Account settings</h3>
</div>

<div id="calendar" class="tabcontent">
    <h3>My calendar</h3>
</div>

<div id="myCreatedEvents" class="tabcontent">
    <h3>My created events</h3>
    <div class="card-deck" id="eventsList">
    </div>
</div>

<div id="userEvents" class="tabcontent">
    <h3>My events</h3>
    <div class="card-deck" id="userEventsList">
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="/js/logout.js"></script>
<script src="/js/uploadFiles.js"></script>
<script>
    function openMenuItem(evt, tabName) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(tabName).style.display = "block";
        evt.currentTarget.className += " active";
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();


</script>
<script src="/js/login-with-google.js" type="text/javascript"></script>
<script src="/js/my-account.js" type="text/javascript"></script>

</body>
</html>