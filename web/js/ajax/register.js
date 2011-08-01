// TODO: Register JS

function register() {
    var url = "register";
    var data = {
        "username": $('input[name=login-username]').val(),
        "password": $('input[name=login-password]').val()
    }

    if (validateLogin(data)) {
        ajaxRequest(url, data, parseLogin)
    }
}

function validateLogin(data) {
    var valid = false;

    if (!data.username) {
        loginMessage("Enter username");
        $("username").focus();
    } else if (!data.password) {
        loginMessage("Enter password");
        $("password").focus();
    } else {
        valid = true;
    }

    return valid;
}

function parseLogin (xmlResponse) {
    var response = $(xmlResponse).find("loginResponse").text();

    $('.text').removeAttr('disabled');

    if (response == 1) {
        loginMessage("Success! Redirecting...");
        window.location = "user/";
    } else {
        loginMessage("Login failed");
        $("#username").val("").focus();
        $("#password").val("");
    }

}

function loginMessage(message) {
    $('#login-msg').text(message);
    $('#login-msg').fadeIn("fast");
    $('#login-msg').effect("highlight", {}, 1000);
}