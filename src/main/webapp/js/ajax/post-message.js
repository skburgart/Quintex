$(document).ready(function() {
    $('#message-box').focus();
})

function newMessage() {
    var url = "post-message";
    var data = {
        "topicid": $('input[name=topic-id]'),
        "message": $('#message-box')
    }

    if (validateMessage(data)) {
        $('.message-input').attr("disabled", "true");
        postAjaxRequest(url, {
            "topicid": data.topicid.val(),
            "message": data.message.val()
        }, parseNewMessage)
    }
}

function validateMessage(data) {
    var valid = false;

    if (data.message.val().length < 5) {
        msgMessage("Message must be at least 5 characters");
        data.message.focus();
    } else if (data.message.val().length > 2048) {
        msgMessage("Message must be at most 2048 characters");
        data.message.focus();
    } else {
        valid = true;
    }

    return valid;
}

function parseNewMessage(xmlResponse) {
    var response = $(xmlResponse).find("messageResponse").text();

    if (response != 0) {
        window.location = "topic.jsp?topicid=" + response;
    } else {
        $('.message-input').removeAttr('disabled');
        msgMessage("Message post failed");
    }

}

function msgMessage(message) {
    $('#post-msg').text(message);
    $('#post-msg').fadeIn("fast");
    $('#post-msg').effect("highlight", {}, 1000);
}