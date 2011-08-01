$(document).ready(function() {
    $('input[name=register-username]').focus();
})

function register() {
    var url = "register";
    var data = {
        "username": $('input[name=register-username]'),
        "password1": $('input[name=register-password1]'),
        "password2": $('input[name=register-password2]')
    }

    if (validateRegistration(data)) {
        $('.register-input').attr("disabled", "true");
        ajaxRequest(url, {
            "username": data.username.val(),
            "password": data.password1.val()
        }, parseRegistration)
    }
}

function validateRegistration(data) {
    var valid = false;

    if (!data.username.val()) {
        registerMessage("Enter username");
        data.username.focus();
    } else if (!data.password1.val()) {
        registerMessage("Enter password");
        data.password1.focus();
    } else if (!data.password2.val()) {
        registerMessage("Enter password confirmation");
        data.password2.focus();
    } else if (data.password1.val() != data.password2.val()) {
        registerMessage("Passwords do not match");
        data.password1.focus();
    } else {
        valid = true;
    }

    return valid;
}

function parseRegistration (xmlResponse) {
    var response = $(xmlResponse).find("registerResponse").text();


    if (response == 1) {
        registerMessage("Success! You may now log in");
    } else {
        $('.register-input').removeAttr('disabled');
        if (response == 2) {
            registerMessage("That username already exists. Please choose another.");
            $('input[name=register-username]').val("").focus();
        } else {
            registerMessage("Registration failed");
        }
    }

}

function registerMessage(message) {
    $('#register-msg').text(message);
    $('#register-msg').fadeIn("fast");
    $('#register-msg').effect("highlight", {}, 1000);
}