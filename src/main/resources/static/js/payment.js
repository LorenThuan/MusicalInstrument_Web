/* Check data when change value in select option */
var city_chooese = document.getElementById('city');
var select_tphcm = document.getElementById('district-tphcm');
var select_hn = document.getElementById('district-hn');
function checkValue() {
    var value = city_chooese.options[city_chooese.selectedIndex].value;
    console.log(value);
    if(value == 1) {
        select_tphcm.style.display = 'block';
        select_hn.style.display = 'none';
    }

    if(value == 2) {
        select_tphcm.style.display = 'none';
        select_hn.style.display = 'block';
    }
}

/* Check validation */
var userName = document.getElementById('userName');
var email = document.getElementById('email');
var phone = document.getElementById('phone');
var address = document.getElementById('address');
var note = document.getElementById('note');
var select_payment = document.getElementById('payment-select');
var select_delivery = document.getElementById('delivery-select');

function checkSubmitData() {
    if(checkUserName() && checkPhone() && checkEmail() && checkAddress() && checkNote() && checkSelect()) {
        return true;
    }
    else {
        alert('Vui lòng kiểm tra lại thông tin đã nhập !');
        return false;
    }
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

function checkAddress() {
    var display = document.getElementById('addressError');
    if(address.value.length == 0) {
        display.innerHTML = 'Địa chỉ không được rỗng !';
        return false;    
    }
    else if(address.value.length < 10) {
        display.innerHTML = 'Địa chỉ không được bé hơn 10 kí tự !';
        return false;    
    }
    else {
        display.innerHTML = '';
        return true;   
    }
    return true;
}

function checkNote() {
    var display = document.getElementById('noteError');
    if(note.value.length == 0) {
        display.innerHTML = 'Ghi chú không được rỗng !';
        return false;    
    }
    else if(note.value.length < 10) {
        display.innerHTML = 'Ghi chú không được bé hơn 10 kí tự !';
        return false;    
    }
    else {
        display.innerHTML = '';
        return true;   
    }
    return true;
}

function checkSelect() {
    var displayPayment = document.getElementById('paymentError');
    var displayDelivery = document.getElementById('deliveryError');
    if(select_payment.options[select_payment.selectedIndex].value == 0) {
        displayPayment.innerHTML = "Vui lòng chọn phương thức thanh toán !";
        return false;
    }
    else {
        displayPayment.innerHTML = "";
    }

    if(select_delivery.options[select_delivery.selectedIndex].value == 0) {
        displayDelivery.innerHTML = "Vui lòng chọn đơn vị vận chuyển";
        return false;
    }
    else {
        displayDelivery.innerHTML = "";
    }
    return true;
}