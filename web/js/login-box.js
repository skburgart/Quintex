/*
    Document   : login-box
    Created on : Jul 25, 2011, 7:41:55 PM
    Author     : Steven Burgart
*/

function showLogin() {
    $('#login').slideToggle('fast');
}

jQuery(document).mouseup(function(event) {
    if(event.target.id != 'login') {
        $("#login").slideUp('fast');
    }
});