<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<section>
	<div class="box-header">
	</div>
	
	<div>
		<h3>Join into AAC</h3>
		<form name="frm" action="/user/join" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div class="form-group">
				<label for="email">이메일 주소</label> 
                <input type="text"
						class="form-control" id="email" name="email" placeholder="이메일 주소" required="required" 
						oninput="checkId()"/>
				<p class="help-block" id="email-error"></p>
			</div>
			<div class="form-group">
				<label for="nick">닉네임</label> 
                <input type="text"
						class="form-control" id="nick" name="nick" placeholder="닉네임" required="required" />
				<p class="help-block" id="nick-error"></p>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label> 
                <input type="password"
						class="form-control" id="password" name="password" placeholder="비밀번호 (영문+숫자혼합 8자리 이상)" required="required"
						oninput="checkPwd()" />
				<p class="help-block" id="password-error"></p>
			</div>
			<div class="form-group">
				<label for="Confirm">비밀번호 확인</label> 
                <input type="password"
						class="form-control" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인" required="required"
						oninput="checkPwd()" />
						<p class="help-block" id="passwordConfirm-error"></p>
			</div>

			<button type="submit" id="btn_submit" class="btn btn-primary btn-block">회원가입</button>
			<a href="#" class="btn btn-primary btn-block" role="button">로그인</a>
		</form>
	</div>
</section>

<script>
var $email = $('#email');
var $password = $('#password');
var $passwordConfirm = $('#confirmPassword');

function checkId() {
	var email = $email.val();
	
	if(!email_check(email)) {
	  	$("#email-error").text('*이메일 주소가 올바르지 않습니다.');
	    return false;
	  } else {
		 $.ajax({
			url : '/user/join/' + email,
			success : function(data) {
				if(data == 'duplicated') {
					$("#email-error").text('*중복된 이메일 주소입니다.');
				} else {
					$("#email-error").text('');
				}
			}
		});
	 }
}

function checkPwd() {
	var password = $password.val();
	var passwordConfirm = $passwordConfirm.val();
	
	if($('#nick').val() == '') {
		$("#nick-error").text('*닉네임이 올바르지 않습니다.');
		return false;
	} else {
		$("#nick-error").text('');
		if(!password_check(password)) {
			$("#password-error").text('*비밀번호가 올바르지 않습니다.');
			return false;
		} else {
			$("#password-error").text('');
			if(!password_check(passwordConfirm)) {
			$("#passwordConfirm-error").text('*비밀번호 확인이 올바르지 않습니다..');
				return false;
			} else {
				$("#passwordConfirm-error").text('');
				if(password!=passwordConfirm) {
					$("#passwordConfirm-error").text('*비밀번호와 확인이 일치하지 않습니다.');
					return false;
				} else {
					$("#passwordConfirm-error").text('');
					return true;
				}
			}
		}
	}
}

$('#btn_submit').on('click', function(event) {
	event.preventDefault();
	
	checkPwd();
	checkId();
	
	var form = $('form');
	
	var errors = form.children().find('p').text();
	
	if(errors != '') {
		alert('입력창을 확인해주세요');
	} else {
		form.submit();		
	}
	
});

function email_check(email) { 
	var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return regex.test(email);
}

function password_check(password) {
	var regex = /^.*(?=.{8,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	return regex.test(password);
}
</script>

<%@ include file="../include/footer.jsp"%>