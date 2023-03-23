

function emailFormCheck(){
	var emailRegex = /^[0-9a-zA-Z]([-_]?[0-9a-zA-z])*@[0-9a-zA-Z]([-_]?[0-9a-zA-z])*(\.[a-zA-z]{2,3}){1,2}$/i;
	
	if(confirm_form.reservationEmail.value === ''){
		alert("이메일을 입력해주세요.");
		return false;
	}
	else if(confirm_form.reservationEmail.value.match(emailRegex) === null){
		alert("이메일 형식이 올바르지 않습니다.");
		return false;
	}
	
	return true;
}