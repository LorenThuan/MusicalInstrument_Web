var userName = document.getElementById('userName');
var passWord = document.getElementById('passWord');
var email = document.getElementById('email');
var phone = document.getElementById('phone');
var date = document.getElementById('date');

function checkSubmitData() {
    if(checkUserName() && checkPassword() && checkEmail() && checkPhone() && checkDate()) {
        return true;
    }
    else {
        alert('Vui lòng kiểm tra lại dữ liệu !');
        return false;
    }
    return true;
}

function checkDate() {
    var d = new Date();
    var today = d.getFullYear() + '-' + (d.getMonth()+1) + '-' + d.getDate();
    var dateCurrent = new Date(today);
    var dateValue = new Date(date.value);

    var display = document.getElementById('dateError');

    if (date.value == '') {
        display.innerHTML = 'Vui lòng chọn ngày sinh của bạn !';
        return false;
    }

    if(dateCurrent < dateValue) {
        display.innerHTML = 'Ngày sinh không được lớn hơn ngày hiện tại !';
        return false;
    }
    else {
        display.innerHTML = '';
        return true;
    }

    return true;
}

function checkEmail() {
    var display = document.getElementById('emailError');
    var regex2 = /^[a-z+0-9+]+(@gmail.com|@email.com)$/;

    if(email.value.length == 0 ){
        display.innerHTML = 'Email không được rỗng !';
        return false;
    }

    if (!regex2.test(email.value)) {
        display.innerHTML = 'Sai định dạng vui lòng nhập lại !'
        return false;
    }

    else {
        display.innerHTML = '';
        return true;   
    }
    return true;
}

function checkPhone() {
    var display = document.getElementById('phoneError');
    var regex1 = /^(086|096|097|098|032|033|034|035|036|037|038|039|089|090|093|070|079|077|076|078|088|091|094|083|084|085|081|082|092|056|058|099|059)([0-9]{7}|[0-9]{8})$/;

    if(phone.value.length == 0) {
        display.innerHTML = 'Số điện thoại không được rỗng !';
        return false;
    }

    if(!regex1.test(phone.value)) {
        display.innerHTML = 'Vui lòng nhập số điện thoại thực và phải đúng định dạng !';
        return false;
    }

    else {
        display.innerHTML = '';
        return true;   
    }
    return true;

} 

function checkUserName() {
    var display = document.getElementById('userError');
    if(userName.value.length == 0) {
        display.innerHTML = 'Tên tài khoản không được rỗng !';
        return false;
    }

    else if (userName.value.length < 6) {
        display.innerHTML = 'Tên tài khoản không được nhỏ hơn 6 kí tự !';
        return false;  
    }

    else if (userName.value.length > 16) {
        display.innerHTML = 'Tên tài khoản không được lớn hơn 16 kí tự !';
        return false;  
    }

    else {
        display.innerHTML = '';
        return true;   
    }
    return true;
}

function checkPassword() {
    var display = document.getElementById('passError');
    if(passWord.value.length == 0) {
        display.innerHTML = 'Mật khẩu không được rỗng !';
        return false;
    }

    else if (passWord.value.length < 6) {
        display.innerHTML = 'Mật khẩu không được nhỏ hơn 6 kí tự !';
        return false;  
    }

    else if (passWord.value.length > 16) {
        display.innerHTML = 'Mật khẩu không được lớn hơn 16 kí tự !';
        return false;  
    }

    else {
        display.innerHTML = '';
        return true;   
    }
    return true;
}
