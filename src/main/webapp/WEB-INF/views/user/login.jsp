<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<section>
	<div class="box-header">
	</div>
	
	<div>
		<h3>Welcome to AAC</h3>
		<form name="frm" action="/user/login" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div class="form-group">
				<label for="email">이메일 주소</label> 
                <input type="text"
						class="form-control" id="email" name="email" placeholder="이메일 주소" required="required">
				<p class="help-block" id="email-error"></p>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label> 
                <input type="password"
						class="form-control" id="password" name="password" placeholder="비밀번호 (영문+숫자혼합 8자리 이상)" required="required">
			</div>
			<div>
				<div id="message" style="width: 300px; position: absolute; top: -60px; border: 1px; border-color: #000;">
					로그인에 실패하셨습니다.
				</div>
			</div>

			<button type="submit" id="btn_submit" class="btn btn-primary btn-block">로그인</button>
			<a href="#" class="btn btn-primary btn-block" role="button">회원가입</a>
		</form>
	</div>
</section>

<c:if test="${error == 'true'}">
	<script>
		jQuery(function() {
			var move = '70px';
			jQuery('#message').animate({
				top : '+=' + move
			}, 'slow', function() {
				jQuery('#message').delay(1000).animate({
					top : '-=' + move
				}, 'slow');
			});
		});
	</script>
</c:if>

<%@ include file="../include/footer.jsp"%>