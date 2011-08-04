$(document).ready(function() {
    $('input[name=reset-username]').focus();
})

function resetPassword() {
    var url = "reset-password";
    var data = {
        "username": $('input[name=reset-username]')
    }

    if (validateReset(data)) {
        resetMessage("Resetting password...");
        $('.reset-input').attr("disabled", "true");
        
        ajaxRequest(url, {
            "username": data.username.val()
        }, parseReset)
    }
}

function validateReset(data) {
    var valid = false;
    
    if (!data.username.val()){
        resetMessage("Enter username");
        data.username.focus();
    } else if (!validateUsername(data.username.val())) {
        resetMessage("Username must start with a letter and not contain special characters");
        data.username.focus();
    } else {
        valid = true;
    }

    return valid;
}

function parseReset (xmlResponse) {
    var response = $(xmlResponse).find("resetResponse").text();

    if (response == 1) {
        resetMessage("Password reset! A new password as been emailed to you.");
    } else {
        $('.reset-input').removeAttr('disabled');
        if (response == 2) {
            resetMessage("Username doesn't exist");
            $('input[name=reset-username]').val("").focus();
        } else {
            resetMessage("Password reset failed");
        }
    }

}

function resetMessage(message) {
    $('#reset-msg').text(message);
    $('#reset-msg').fadeIn("fast");
    $('#reset-msg').effect("highlight", {}, 1000);
}