function ajaxRequest(url, data, callback) {
      jQuery.ajax({
          url: url,
          type: "GET",
          data: data,
          dataType: "xml",
          success: callback,
          error: function(jqXHR, text, error) {
              alert("Ajax error: " + text + "\nReason: " + error);
          }
      })
}

function postAjaxRequest(url, data, callback) {
      jQuery.ajax({
          url: url,
          type: "POST",
          data: data,
          dataType: "xml",
          success: callback,
          error: function(jqXHR, text, error) {
              alert("Ajax error: " + text + "\nReason: " + error);
          }
      })
}