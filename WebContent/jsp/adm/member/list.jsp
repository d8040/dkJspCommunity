<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>회원 리스트</title>
</head>
<body>
	<h1>회원 리스트</h1>
	<c:forEach items="${members}" var="member">
	번호: 
		${member.id}
		<br />
		이름: 
		${member.name}
		<br />
		닉네임: 
		${member.nickname}
		<hr />
	</c:forEach>
</body>
</html>