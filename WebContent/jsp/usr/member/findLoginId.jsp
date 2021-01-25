<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value= "아이디 찾기"/> 
<%@ include file="../../part/head.jspf"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js" integrity="sha512-szJ5FSo9hEmXXe7b5AUVtn/WnL8a5VofnFeYC2i2z03uS2LhAch7ewNLbl5flsEmTTimMN0enBZg/3sQ+YOSzQ=="
	crossorigin="anonymous"></script>
	
	<h1>${pageTitle}</h1>
	<div>
		<script>
		let DoFindLoginIdForm__submited = false;
		function DoFindLoginIdForm__submit(form){
			if (DoFindLoginIdForm__submited){
				alert('처리 중 입니다.');
				return;
			}
		
			form.name.value = form.name.value.trim();
			
			if(form.name.value.length == 0){
				alert('이름을 입력해주세요.');
				form.name.focus();
				
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
			DoFindLoginIdForm__submited = true;			
		}
		</script>
		<form action="doFindLoginId" method="POST" onsubmit="DoFindLoginIdForm__submited(this); return false;">
		<input type="hidden" name ="loginPwReal" />
			<hr />
			<div>이름</div>
			<div>
				<div>
					<input name="name" type="text" maxlength="50"
						placeholder="이름을 입력해주세요."> 
				</div>
			</div>

			<hr />
			<div>이메일</div>
			<div>
				<div>
					<input name="email" type="email" maxlength="50"
						placeholder="가입 시 등록한 이메일을 입력해주세요." />
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