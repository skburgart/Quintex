function login() {
    var username = $('input[name=username]');
    var password = $('input[name=password]');
    var data = 'username=' + username.val() + '&password=' + password.val();

    if (!username.val()) {
        loginMessage("Enter username");
        username.focus();
    } else if (!password.val()) {
        loginMessage("Enter password");
        password.focus();
    } else {
        $('.text').attr('disabled', 'true');
        ajaxRequest("login", data, parseLogin)
    }
}

function parseLogin (xmlResponse) {
    var response = $(xmlResponse).find("response").text();


    $('.text').removeAttr('disabled');

    if(response == 0) {
        loginMessage("Username doesn't exist");
        $("#username").focus();
    } else if (response == 1) {
        loginMessage("Password incorrect");
        $("#password").focus();
    }

}

function loginMessage(message) {
    $('#login-msg').text(message);
    $('#login-msg').fadeIn("fast");
    $('#login-msg').effect("highlight", {}, 1000);
}