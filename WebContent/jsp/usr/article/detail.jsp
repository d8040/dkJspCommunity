<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sbs.example.dkJspCommunity.dto.Article" %>
<%
Article article = (Article)request.getAttribute("article");
%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>게시물 상세</title>
</head>
<body>
	<h1>
		게시물 상세
	</h1>
	<div>
		번호 :
		<%=article.id%>
		<br />
		작성날짜 :
		<%=article.regDate%>
		<br />
		갱신날짜 :
		<%=article.updateDate%>
		<br />
		작성자 :
		<%=article.extra_writer%>
		<br />
		제목 :
		<%=article.title%>
		<br />
		제목 :
		<%=article.body%>
		<hr />
	</div>
	<%
	%>
</body>
</html>