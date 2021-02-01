<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="회원가입" />
<%@ include file="../../part/head.jspf"%>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<div>
	<script>
	let DoModifyForm__submited = false;
	let DoModifyForm__checkedLoginId = "";
	
	// 폼 발송전 체크
	function DoModifyForm__submit(form) {
		if ( DoModifyForm__submited ) {
			alert('처리중입니다.');
			return;
		}
		
		form.loginPw.value = form.loginPw.value.trim();
	
		if ( form.loginPw.value.length > 0 ) {
			form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		
			if ( form.loginPwConfirm.value.length == 0 ) {
				alert('비밀번호 확인을 입력해주세요.');
				form.loginPwConfirm.focus();
				
				return;
			}
			
			if ( form.loginPw.value != form.loginPwConfirm.value ) {
				alert('비밀번호가 일치하지 않습니다.');
				form.loginPwConfirm.focus();
				
				return;
			}					
			form.loginPwReal.value = sha256(form.loginPw.value);
			form.loginPw.value = "";
			form.loginPwConfirm.value = "";
		}
		
		form.name.value = form.name.value.trim();
	
		if ( form.name.value.length == 0 ) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			
			return;
		}
		
		form.nickname.value = form.nickname.value.trim();
	
		if ( form.nickname.value.length == 0 ) {
			alert('별명을 입력해주세요.');
			form.nickname.focus();
			
			return;
		}
		
		form.email.value = form.email.value.trim();
	
		if ( form.email.value.length == 0 ) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			
			return;
		}
		
		form.cellphoneNo.value = form.cellphoneNo.value.trim();
	
		if ( form.cellphoneNo.value.length == 0 ) {
			alert('전화번호를 입력해주세요.');
			form.cellphoneNo.focus();
			
			return;
		}

		
		form.submit();
		DoModifyForm__submited = true;
	}
	</script>
	<form action="doMemberModify" method="POST" onsubmit="DoModifyForm__submit(this); return false;">
		<input type="hidden" name="loginPwReal" />
		<section class="memberModify-box">
			<div class="login-box__title">회원정보 수정</div>
			<table class="memberModify-box__table">
				<tr>
					<th>아이디</th>
					<td>${loginedMember.loginId}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${loginedMember.name}</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input name="loginPw" type="password" maxlength="50" placeholder="비밀번호 입력해주세요." /></td>
				</tr>
				<tr>
					<th>비번 확인</th>
					<td><input name="loginPwConfirm" type="password" maxlength="50" placeholder="비밀번호 입력해주세요." /></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input name="nickname" type="text" maxlength="50" value="${loginedMember.nickname}" placeholder="닉네임을 입력해주세요."/></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input name="email" type="email" maxlength="100" value="${loginedMember.email}" placeholder="이메일 주소를 입력해주세요."/></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input name="cellphoneNo" type="tel" maxlength="100" value="${loginedMember.cellphoneNo}" placeholder="전화번호를 입력해 주세요."/></td>
				</tr>
			</table>
			<div>
				<div class="login-box__button">
					<button type="submit" class="btn btn-success">수정</button>
					<button type="button" class="btn btn-info" onclick="history.back();">취소</button>
				</div>
			</div>
		</section>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>
