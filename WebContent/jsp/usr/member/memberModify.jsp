<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${member.nickname}님 정보 수정" />
<%@ include file="../../part/head.jspf"%>
<h1>${pageTitle}</h1>
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
				alert('로그인 비밀번호 확인을 입력해주세요.');
				form.loginPwConfirm.focus();
				
				return;
			}
			
			if ( form.loginPw.value != form.loginPwConfirm.value ) {
				alert('로그인 비밀번호가 일치하지 않습니다.');
				form.loginPwConfirm.focus();
				
				return;
			}
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
		
		if ( form.loginPw.value.length > 0 ) {
			form.loginPwReal.value = sha256(form.loginPw.value);
			form.loginPw.value = "";
			form.loginPwConfirm.value = "";
		}
		
		form.submit();
		DoModifyForm__submited = true;
	}
	</script>
	<form action="doMemberModify" method="POST" outsubmit = "DoMemberModify__sumit(this); return false;">
		<input type="hidden" name="id" value="loginPwReal" />

		<hr />
		<div>아이디: ${member.loginId}</div>
		<hr />
		<div>이름: ${member.name}</div>
		<hr />
		<div>
			<div>로그인 비번</div>
			<div>
				<input name="loginPw" type="password" maxlength="50"
					placeholder="로그인 비밀버호를 입력해주세요." />
			</div>
		</div>

		<hr />

		<div>
			<div>로그인 비밀번호 확인</div>
			<div>
				<input name="loginPwConfirm" type="password" maxlength="50"
					placeholder="로그인 비밀버호 확인을 입력해주세요." />
			</div>
		</div>
		<hr />
		<div>닉네임:</div>
		<div>
			<div>
				<input name="nickname" type="text" maxlength="50" value="${member.nickname}" />
			</div>
		</div>
		<hr />
		<div>이메일</div>
		<div>
			<div>
				<input name="email" type="email" maxlength="100" value="${member.email}" />
			</div>
		</div>

		<hr />
		<div>전화번호</div>
		<div>
			<div>
				<input name="cellphoneNo" type="tel" maxlength="100" value="${member.cellphoneNo}" />
			</div>
		</div>
		<hr />
		<div>
			<div>수정</div>
			<div>
				<input type="submit" value="회원정보수정" />
				<button type="button" onclick="history.back();">취소</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>