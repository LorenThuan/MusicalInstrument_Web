function kiemtraTenSP() {
	var ten = document.getElementById("tenSP").value;

	var regexTenSP = "/[A-Z][a-zA-Z ]*/";

	if (ten.trim() === '') {
		document.getElementById("tbTenSP").innerHTML = "(Tên sản phẩm bắt buộc nhập!)";
		return false;
	}

	if (!regexTenSP.test(ten)) {
		alert("hay");
		document.getElementById("tbTenSP").innerHTML = "(Tên bắt đầu bằng chữ hoa!)";
		return false;
	} else {
		alert("ok");
	}

	document.getElementById("tbTenSP").innerHTML = "(*)";
	return true;
}