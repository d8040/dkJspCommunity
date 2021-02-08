<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="회원가입" />
<%@ include file="../../part/head.jspf"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js" integrity="sha512-szJ5FSo9hEmXXe7b5AUVtn/WnL8a5VofnFeYC2i2z03uS2LhAch7ewNLbl5flsEmTTimMN0enBZg/3sQ+YOSzQ==" crossorigin="anonymous"></script>
<main class="flex-g-1 con">
	<div>
		<script type="text/javascript">
		let DoJoinForm__submited = false;
		let DoJoinForm__checkedLoginId = "";
		
		function DoJoinForm__checkLoginIdDup(el) {
			const from = $(el).closest('form').get(0);
			const loginId = from.loginId.value;
			
			$.get(
				"getLoginIdDup",
				{
					loginId
				},
				function(data){
					if (data.msg){
						alert(data.msg);
					}
					if (data.success){
						DoJoinForm__checkedLoginId = data.body.loginId;
					}
				},
				"json"
			);
		}
		
		function DoJoinForm__submit(form){
			if (DoJoinForm__submited){
				alert('처리 중 입니다.');
				return;
			}
		
			form.loginId.value = form.loginId.value.trim();
			
			if(form.loginId.value.length == 0){
				alert('로그인 아이디를 입력해주세요.');
				form.loginId.focus();
				
				return;
			}
			
			if(form.loginId.value != DoJoinForm__checkedLoginId){
				alert('로그인 아이디 중복검사를 해주세요'); 
				form.btnLoginIdDupCheck.focus();
				return false;
			}
			
			form.loginPw.value = form.loginPw.value.trim();
			
			if(form.loginPw.value.length == 0){
				alert('비밀번호를 입력해주세요.');
				form.loginPw.focus();
				
				return;
			}
			
			form.loginPwCheck.value = form.loginPwCheck.value.trim();
			
			if(form.loginPwCheck.value.length == 0){
				alert('비밀번호를 다시 한번 입력해주세요.');
				form.loginPwCheck.focus();
				
				return;
			}
			
			if(form.loginPwCheck.value != form.loginPw.value){
				alert('비밀번호가 일치하지 않습니다.');
				form.loginPwCheck.focus();
				
				return;
			}
			
			form.name.value = form.name.value.trim();
			
			if(form.name.value.length == 0){
				alert('이름을 입력해주세요.');
				form.name.focus();
				
				return;
			}
			
			form.nickname.value = form.nickname.value.trim();
			
			if(form.nickname.value.length == 0){
				alert('닉네임을 입력해주세요.');
				form.nickname.focus();
				
				return;
			}
			
			form.email.value = form.email.value.trim();
			
			if(form.email.value.length == 0){
				alert('이메일을 입력해주세요.');
				form.email.focus();
				
				return;
			}
				
			form.cellphoneNo.value = form.cellphoneNo.value.trim();
			
			if(form.cellphoneNo.value.length == 0){
				alert('전화번호를 입력해주세요.');
				form.cellphoneNo.focus();
				
				return;
			}
			
			form.loginPwReal.value = sha256(form.loginPw.value);
			form.loginPw.value = "";
			form.loginPwConfirm.value = "";
			
			form.submit();
			DoJoinForm__submited = true;			
		}
		</script>
		<form action="doJoin" method="POST" onsubmit="DoJoinForm__submit(this); return false;">
			<input type="hidden" name="loginPwReal" />
			<section class="join-box">
				<div class="login-box__title">회원 가입</div>
				<table class="join-box__table">
					<tr>
						<th>아이디</th>
						<td><input name="loginId" type="text" maxlength="50" placeholder="아이디를 입력해주세요.">
							<button onclick="DoJoinForm__checkLoginIdDup(this);" name="btnLoginIdDupCheck" type="button">중복확인</button></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input name="loginPw" type="password" maxlength="50" placeholder="비밀번호 입력해주세요." /></td>
					</tr>
					<tr>
						<th>비번 확인</th>
						<td><input name="loginPwCheck" type="password" maxlength="50" placeholder="비밀번호 입력해주세요." /></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input name="name" type="text" maxlength="50" placeholder="본인 이름을 입력해주세요." /></td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td><input name="nickname" type="text" maxlength="50" placeholder="닉네임을 입력해주세요." /></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input name="email" type="email" maxlength="100" placeholder="이메일 주소를 입력해주세요." /></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><input name="cellphoneNo" type="tel" maxlength="100" placeholder="전화번호를 입력해 주세요." /></td>
					</tr>
				</table>
				<div>
					<div class="login-box__button">
						<button type="submit" class="btn btn-success">가입신청</button>
						<button type="submit" class="btn btn-info" onclick="history.back();">취소</button>
					</div>
				</div>
			</section>
		</form>
	</div>
	<%@ include file="../../part/foot.jspf"%>