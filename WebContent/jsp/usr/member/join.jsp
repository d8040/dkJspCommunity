<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>회원가입</title>
</head>
<body>
	<h1>회원가입작성</h1>
	<div>
		<form action="doJoin" method="POST">
			
			<hr />
			<div>아이디</div>
			<div>
				<div>
					<input name="loginId" type="text" maxlength="10"
						placeholder="사용할 아이디를 입력해주세요." />
				</div>
			</div>
			
			<hr />
			<div>비밀번호</div>
			<div>
				<div>
					<input name="loginPw" type="text" maxlength="10"
						placeholder="사용할 비밀번호 입력해주세요." />
				</div>
			</div>
			
			<hr />
			<div>이름</div>
			<div>
				<div>
					<input name="name" type="text" maxlength="10"
						placeholder="본인 이름을 입력해주세요." />
				</div>
			</div>
			
			<hr />
			<div>닉네임</div>
			<div>
				<div>
					<input name="nickname" type="text" maxlength="10"
						placeholder="닉네임을 입력해주세요." />
				</div>
			</div>
			
			<hr />
			<div>이메일</div>
			<div>
				<div>
					<input name="email" type="text" maxlength="50"
						placeholder="이메일 주소를 입력해주세요." />
				</div>
			</div>
			
			<hr />
			<div>전화번호</div>
			<div>
				<div>
					<input name="cellphoneNo" type="text" maxlength="50"
						placeholder="전화번호를 입력해 주세요." />
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
</body>
</html>