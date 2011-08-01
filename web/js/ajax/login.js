function login() {
    var url = "login";
    var data = {
        "username": $('input[name=login-username]').val(),
        "password": $('input[name=login-password]').val()
    }

    if (validateLogin(data)) {
        $('.login-input').attr("disabled", "true");
        ajaxRequest(url, data, parseLogin)
    }
}

function validateLogin(data) {
    var valid = false;

    if (!data.username) {
        loginMessage("Enter username");
        $('input[name=login-username]').focus();
    } else if (!data.password) {
        loginMessage("Enter password");
        $('input[name=login-password]').focus();
    } else {
        valid = true;
    }

    return valid;
}

function parseLogin (xmlResponse) {
    var response = $(xmlResponse).find("loginResponse").text();

    if (response == 1) { // login valid
        window.location = "user/";
    } else {
        $('.login-input').removeAttr('disabled');
        if (response == 0) { // invalid username
            loginMessage("Username not found");
            $("input[name=login-username]").focus();
        } else if (response == 2) { // incorrect password
            loginMessage("Password incorrect");
            $("input[name=login-password]").val("").focus();
        }
    }

}

function loginMessage(message) {
    $('#login-msg').text(message);
    $('#login-msg').fadeIn("fast");
    $('#login-msg').effect("highlight", {}, 1000);
}