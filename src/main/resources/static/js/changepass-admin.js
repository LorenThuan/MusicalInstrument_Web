/* Check validation */
var oldPassword = document.getElementById('oldpass');
var newPassword = document.getElementById('newpass');
var reNewPassword = document.getElementById('renewpass');

function checkSubmitData() {
    var display = document.getElementById('renewpassError');
    if(!(checkOldPassword() && checkNewPassword() && checkReNewPassword())) {
        alert('Vui lòng kiểm tra thông tin đã nhập !');
        return false;
    }
    else {
        if(newPassword.value != reNewPassword.value) {
            display.innerHTML = 'Mật khẩu nhập lại không giống nhau !';
            reNewPassword.focus();
            return false;
        }
    }
    return true;
}

function checkOldPassword() {
    var display = document.getElementById('oldpassError');
    if(oldPassword.value.length == 0) {
        display.innerHTML = 'Mật khẩu cũ không được rỗng !';
        return false;
    }
    else if(oldPassword.value.length < 6) {
        display.innerHTML = 'Mật khẩu cũ không được bé hơn 6 kí tự !';
        return false; 
    }
    else if(oldPassword.value.length > 16) {
        display.innerHTML = 'Mật khẩu cũ không được lớn hơn 16 kí tự !';
        return false; 
    }
    else {
        display.innerHTML = '';
    }
    return true;
}

function checkNewPassword() {
    var display = document.getElementById('newpassError');
    if(newPassword.value.length == 0) {
        display.innerHTML = 'Mật khẩu mới không được rỗng !';
        return false;
    }
    else if(newPassword.value.length < 6) {
        display.innerHTML = 'Mật khẩu mới không được bé hơn 6 kí tự !';
        return false; 
    }
    else if(newPassword.value.length > 16) {
        display.innerHTML = 'Mật khẩu mới không được lớn hơn 16 kí tự !';
        return false; 
    }
    else {
        display.innerHTML = '';
    }
    return true;
}

function checkReNewPassword() {
    var display = document.getElementById('renewpassError');
    if(reNewPassword.value.length == 0) {
        display.innerHTML = 'Nhập lại mật khẩu không được rỗng !';
        return false;
    }
    else if(reNewPassword.value.length < 6) {
        display.innerHTML = 'Nhập lại mật khẩu không được bé hơn 6 kí tự !';
        return false; 
    }
    else if(reNewPassword.value.length > 16) {
        display.innerHTML = 'Nhập lại mật khẩu không được lớn hơn 16 kí tự !';
        return false; 
    }
    else {
        display.innerHTML = '';
    }
    return true;
}