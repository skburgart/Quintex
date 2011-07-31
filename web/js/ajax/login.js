function login() {
    var url = "login";
    var data = {
        "username": $('input[name=username]').val(),
        "password": $('input[name=password]').val()
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