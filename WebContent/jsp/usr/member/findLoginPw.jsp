<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="비밀번호 찾기" />
<%@ include file="../../part/head.jspf"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js" integrity="sha512-szJ5FSo9hEmXXe7b5AUVtn/WnL8a5VofnFeYC2i2z03uS2LhAch7ewNLbl5flsEmTTimMN0enBZg/3sQ+YOSzQ==" crossorigin="anonymous"></script>

<h1>${pageTitle}</h1>
<div>
	<script>
		let DoFindLoginPwForm__submited = false;
		function DoFindLoginPwForm__submit(form){
			if (DoFindLoginPwForm__submited){
				alert('처리 중 입니다.');
				return;
			}
		
			form.loginId.value = form.loginId.value.trim();
			
			if(form.loginId.value.length == 0){
				alert('아이디를 정확하게 입력해주세요.');
				form.loginId.focus();
				
				return;
			}
			
			form.email.value = form.email.value.trim();
			
			if(form.email.value.length == 0){
				alert('가입 시 등록한 이메일을 입력해주세요.');
				form.email.focus();
				
				return;
			}
			
			form.loginPwReal.value = sha256(form.loginPw.value);
			form.loginPw.value = "";
						
			form.submit();
			DoFindLoginPwForm__submited = true;			
		}
		</script>
	<form action="doFindLoginPw" method="POST" onsubmit="DoFindLoginPwForm__submited(this); return false;">
		<input type="hidden" name="loginPwReal" />
		<hr />
		<div>아이디</div>
		<div>
			<div>
				<input name="loginId" type="text" maxlength="50" placeholder="이름을 입력해주세요.">
			</div>
		</div>

		<hr />
		<div>이메일</div>
		<div>
			<div>
				<input name="email" type="email" maxlength="50" placeholder="가입 시 등록한 이메일을 입력해주세요." />
			</div>
		</div>

		<hr />
		<div>
			<div>
				<input type="submit" value="아이디 찾기" />
				<button type="button" onclick="history.back();">취소</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>