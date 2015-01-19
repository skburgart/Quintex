function login() {
    var url = "login";
    var data = {
        "username": $('input[name=login-username]'),
        "password": $('input[name=login-password]')
    }

    if (validateLogin(data)) {
        $('.login-input').attr("disabled", "true");
        ajaxRequest(url, {
            "username": data.username.val(), 
            "password": data.password.val()
        }, parseLogin)
    }
}

function validateLogin(data) {
    var valid = false;

    if (!data.username.val()) {
        loginMessage("Enter username");
        data.username.focus();
    } else if (!data.password.val()) {
        loginMessage("Enter password");
        data.password.focus();
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
        if (response == 2) { // invalid username
            loginMessage("Username not found");
            $("input[name=login-username]").focus();
        } else if (response == 3) { // incorrect password
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