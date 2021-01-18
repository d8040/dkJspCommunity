<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>${board.name} 게시물 리스트
</title>
</head>
<body>
	<h1>${board.name}
		게시물 리스트
	</h1>
	<div>
		<a href="write?boardId=${param.boardId}">게시물
			작성</a>
	</div>
	<c:forEach items="${articles}" var="article">
	번호 :
		${article.id}
		<br />
		작성날짜 :
		${article.regDate}
		<br />
		갱신날짜 :
		${article.updateDate}
		<br />
		작성자 :
		${article.extra_writer}
		<br />
		제목 :
		<a href="detail?id=${article.id}">${article.title}</a>
		<hr />
	</c:forEach>
</body>
</html>