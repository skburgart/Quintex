/*
    Document   : login-box
    Created on : Jul 25, 2011, 7:41:55 PM
    Author     : Steven Burgart
    Email      : skburgart@gmail.com
*/

function showLogin() {
    $('#login').slideToggle('fast', function() {
        $('input[name=login-username]').focus()
    });

}
