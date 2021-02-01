<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${article.id}번 게시물 수정" />
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
	
		form.title.value = form.title.value.trim();
	
		if ( form.title.value.length == 0 ) {
			alert('제목을 입력해주세요.');
			form.title.focus();
			
			return;
		}
		
		const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
		const body = editor.getMarkdown().trim();
		
		if ( body.length == 0 ) {
			alert('내용을 입력해주세요.');
			editor.focus();
			
			return;
		}
		form.body.value = body;
		
		form.submit();
		DoModifyForm__submited = true;
	}
	</script>
	<form action="doModify" method="POST" onsubmit="DoModifyForm__submit(this); return false;">
		<input type="hidden" name="boardId" value="${article.boardId}" /> <input type="hidden" name="id" value="${article.id}" /> <input type="hidden" name="body" />

		<section class="article-modify con-min-width">
			<div class="article_modify__board-onclick">
				<a href="article_list_source code_1.html">게시판 > ${pageTitle} > </a>
			</div>
			<div class="article-modify__title">
				<input name="title" type="text" maxlength="50" value="${article.title}" />
			</div>
			<div class="article-modify__info">
				<div class="flex jc-space-between">
					<div>작성자: ${article.extra_writer}</div>
					<div>조회수: ${article.hitsCount}</div>
				</div>
				<div class="flex jc-space-between">
					<div>작성일: ${article.regDate}</div>
					<div>추천수: ${article.updateDate}</div>
				</div>
			</div>
			<hr>
			<div class="article-modify__body">
				<script type="text/x-template">${article.body}</script>
				<div class="toast-ui-editor"></div>
			</div>
			<hr>
			<div class="article-modify__bottom flex flex-ai-c">
				<div class="flex">
					<div>
						<input class="btn" type="submit" value="작성" />
					</div>
					<div>
						<button class="btn btn-success" type="button" onclick="history.back();">뒤로가기</button>
					</div>
				</div>
			</div>
		</section>
	</form>
</div>
<%@ include file="../../part/foot.jspf"%>