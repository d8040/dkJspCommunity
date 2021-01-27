<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${member.nickname}님 정보 수정" />
<%@ include file="../../part/head.jspf"%>
<h1>${pageTitle}</h1>
<div>
	<form action="doMemberModify" method="POST">
		<input type="hidden" name="id" value="${member.id}" /> 
		
		<hr />
		<div>아이디: ${member.loginId}</div>
		<hr />
		<div>이름: ${member.name}</div>
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