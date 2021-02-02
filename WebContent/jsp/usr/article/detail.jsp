<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="게시물 상세페이지" />
<%@ include file="../../part/head.jspf"%>
<main class="flex-g-1 con">
	<section class="article-detail con-min-width">
		<div class="article_detail__board-onclick">
			<a href="article_list_source code_1.html">게시판 > ${pageTitle} > </a>
		</div>
		<div class="article-detail__title">${article.title}</div>
		<div class="article-detail__info">
			<div class="flex jc-space-between">
				<div>작성자: ${article.extra_writer}</div>
				<div>조회수: ${article.hitsCount}</div>
			</div>
			<div class="flex jc-space-between">
				<div>작성일: ${article.regDate}</div>
				<div>추천수: 0</div>
			</div>
		</div>
		<hr>
		<div class="article-detail__body">
			<script type="text/x-template">${article.body}</script>
			<div class="toast-ui-viewer"></div>
		</div>
		<div class="article-detail__articleRcm flex">
			<div>
				<a href="doLike?memberId=${loginedMemberId}&id=${article.id}" >좋아요</a>[${likeCount}]
			</div>
			<div>
				<a href="doHate?memberId=${loginedMemberId}&id=${article.id}">싫어요</a>[${hateCount}]
			</div>
		</div>
		<hr>
		<div class="article-detail__bottom flex jc-space-between flex-ai-c">
			<div class="flex">
				<div>
					<a class="" href="">&lt; 이전글</a>
				</div>
				<div>
					<a href="list?boardId=${article.boardId}">목록</a>
				</div>
				<div>
					<a class="" href=""> 다음글 &gt;</a>
				</div>
			</div>
			<div class="flex">
				<div>
					<a class="btn btn-danger" href="doDelete?memberId=${article.memberId}&id=${article.id}" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
				</div>
				<div>
					<a class="btn btn-success" href="modify?memberId=${article.memberId}&id=${article.id}" onclick="return confirm('정말로 수정하시겠습니까?')">수정</a>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="../../part/foot.jspf"%>