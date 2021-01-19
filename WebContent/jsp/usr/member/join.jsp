<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="회원가입" />
<%@ include file="../../part/head.jspf"%>
<h1>회원가입작성</h1>
<div>
	<script type="text/javascript">
		let DoJoinForm__submited = false;
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
			
			form.submit();			
		}
		</script>
	<form action="doJoin" method="POST" onsubmit="DoJoinForm__submit(this); return false;">
		<hr />
		<div>아이디</div>
		<div>
			<div>
				<input name="loginId" type="text" maxlength="50" placeholder="사용할 아이디를 입력해주세요.">
			</div>
		</div>

		<hr />
		<div>비밀번호</div>
		<div>
			<div>
				<input name="loginPw" type="password" maxlength="50" placeholder="사용할 비밀번호 입력해주세요." />
			</div>
		</div>

		<hr />
		<div>비밀번호확인</div>
		<div>
			<div>
				<input name="loginPwCheck" type="password" maxlength="50" placeholder="사용할 비밀번호 입력해주세요." />
			</div>
		</div>

		<hr />
		<div>이름</div>
		<div>
			<div>
				<input name="name" type="text" maxlength="50" placeholder="본인 이름을 입력해주세요." />
			</div>
		</div>

		<hr />
		<div>닉네임</div>
		<div>
			<div>
				<input name="nickname" type="text" maxlength="50" placeholder="닉네임을 입력해주세요." />
			</div>
		</div>

		<hr />
		<div>이메일</div>
		<div>
			<div>
				<input name="email" type="email" maxlength="100" placeholder="이메일 주소를 입력해주세요." />
			</div>
		</div>

		<hr />
		<div>전화번호</div>
		<div>
			<div>
				<input name="cellphoneNo" type="number" maxlength="100" placeholder="전화번호를 입력해 주세요." />
			</div>
		</div>
		<hr />
		<div>
			<div>작성</div>
			<div>
				<input type="submit" value="가입신청" />
				<button type="button" onclick="history.back();">취소</button>
			</div>
		</div>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>