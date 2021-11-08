var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};
var token = getUrlParameter('token');
var username = getUrlParameter('username');

if (token != null && username != null) {
    localStorage.setItem("token", token);
    localStorage.setItem("email", username);
    console.log(localStorage.getItem("token"));
}
