function validateTitle(title) {
    var regex = /^[A-Za-z0-9.,!@#$%^&*()-_=+]+$/;
    if (regex.test(title)){
        return true;
    }
    
    return false;
}

function validateMessage(message) {
    var regex = /^[A-Za-z0-9.,!@#$%^&*()-_=+\n]+$/;
    if (regex.test(message)){
        return true;
    }
    
    return false;
    
}

function validateEmail(email) {
    var regex = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (regex.test(email)){
        return true;
    }
    
    return false;
}

function validateUsername(username) {
    var regex = /^[A-Za-z]\w+$/;
    if (regex.test(username)){
        return true;
    }
    
    return false;
}