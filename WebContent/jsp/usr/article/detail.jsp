<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="게시물 상세페이지" />
<%@ include file="../../part/head.jspf"%>

<h1>${pageTitle}</h1>
<div>
	번호 : ${article.id} 
	<br /> 
	작성날짜 : ${article.regDate} 
	<br /> 
	갱신날짜 : ${article.updateDate} 
	<br /> 
	작성자 : ${article.extra_writer} 
	<br /> 
	제목 : ${article.title} 
	<br /> 
	제목 : ${article.body}
	<hr />
	${article.body}
</div>
<div>
	<a href="list?boardId=${article.boardId}">리스트로 이동</a> <a href="modify?memberId=${article.memberId}&id=${article.id}">게시물 수정</a>
</div>
<div>
	<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="doDelete?memberId=${article.memberId}&id=${article.id}">게시물 삭제</a>
</div>
<%@ include file="../../part/foot.jspf"%>