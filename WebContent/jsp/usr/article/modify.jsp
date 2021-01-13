<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.sbs.example.dkJspCommunity.dto.Board"%>
<%@ page import="com.sbs.example.dkJspCommunity.dto.Article"%>
<%
Article article = (Article) request.getAttribute("article");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title><%=article.id%>번 게시물 수정</title>
</head>
<body>
	<h1>
		<%=article.id%>번 게시물 수정
	</h1>
	<div>
		<form action="doModify" method="POST">
			<input type="text" name="id" value="<%=article.id%>" />
			<input type="hidden" name="boardId" value="<%=article.boardId%>" />
			<input type="hidden" name="memberId" value="<%=article.memberId%>" />

			<hr />
				<div>제목</div>
				<div>
					<div>
						<input name="title" type="text" maxlength="50"
							placeholder="제목을 입력해주세요." />
					</div>
				</div>

				<hr />

				<div>
					<div>내용</div>
					<div>
						<textarea placeholder="내용을 입력해주세요." name="body" maxlength="5000"></textarea>
					</div>
				</div>
				<hr />
				<div>
					<div>작성</div>
					<div>
						<input type="submit" value="작성" />
						<button type="button" onclick="history.back();">뒤로가기</button>
					</div>
				</div>
		</form>
	</div>
</body>
</html>