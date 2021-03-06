$(document).ready(function() {
    $('input[name=topic-title]').focus();
})

function newTopic() {
    var url = "post-topic";
    var data = {
        "boardid": $('input[name=board-id]'),
        "title": $('input[name=topic-title]'),
        "message": $('#message-box')
    }

    if (validateTopic(data)) {
        $('.topic-input').attr("disabled", "true");
        postAjaxRequest(url, {
            "boardid": data.boardid.val(),
            "title": data.title.val(),
            "message": data.message.val()
        }, parseNewTopic)
    }
}

function validateTopic(data) {
    var valid = false;

    if (data.title.val().length < 6) {
        topicMessage("Title must be at least 6 characters");
        data.title.focus();
    } else if (data.title.val().length > 64) {
        topicMessage("Title must be at most 64 characters");
        data.title.focus();
    } else if (data.message.val().length < 5) {
        topicMessage("Message must be at least 5 characters");
        data.message.focus();
    } else if (data.message.val().length > 2048) {
        topicMessage("Message must be at most 2048 characters");
        data.message.focus();
    } else {
        valid = true;
    }

    return valid;
}

function parseNewTopic (xmlResponse) {
    var response = $(xmlResponse).find("topicResponse").text();

    if (response != 0) {
        window.location = "topic.jsp?topicid=" + response;
    } else {
        $('.topic-input').removeAttr('disabled');
        topicMessage("Topic creation failed");
    }

}

function topicMessage(message) {
    $('#post-msg').text(message);
    $('#post-msg').fadeIn("fast");
    $('#post-msg').effect("highlight", {}, 1000);
}