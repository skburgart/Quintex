function ajaxRequest(servlet, data, callback) {
    $.ajax({
        url: servlet,
        type: "GET",
        data: data,
        cache: false,
        dataType: "xml",
        success: callback
    });
}