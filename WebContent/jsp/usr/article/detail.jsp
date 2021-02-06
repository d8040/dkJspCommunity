<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="게시물 상세페이지" />
<%@ include file="../../part/head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<main class="flex-g-1 con">
	<div>
		<script>
	let DoReplyWriteForm__submited = false;
	let DoReplyWriteForm__checkedLoginId = "";
	
	// 폼 발송전 체크
	function DoReplyWriteForm__submit(form) {
		if ( DoReplyWriteForm__submited ) {
			alert('처리중입니다.');
			return;
		}
		
		if ( form.replyBody.value.length == 0 ) {
			alert('내용을 입력해주세요.');
			form.replyBody.value.focus();
			
			return;
		}
		
		form.submit();
		DoReplyWriteForm__submited = true;
	}
	function toggleLayer(layer){
    var l = document.getElementById(layer);
    if(l.style.display == "")
      l.style.display = "none";
    else if(l.style.display == "none")
      l.style.display = "";
  }	
	</script>
		<form action="../reply/doReplyWrite" method="POST" onsubmit="DoReplyWriteForm__submit(this); return false;">
			<input type="hidden" name="articleId" value="${article.id}" /> 
			<input type="hidden" name="boardId" value="${board.id}" />

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
						<c:if test="${isLiked == -1}">
							<a href="doLike?memberId=${loginedMemberId}&id=${article.id}">좋아요<i class="far fa-thumbs-up"></i>[${likeCount}]
							</a>
						</c:if>
						<c:if test="${isLiked == 1}">
							<a href="doLikeCancel?memberId=${loginedMemberId}&id=${article.id}">좋아요<i class="fas fa-thumbs-up"></i>[${likeCount}]
							</a>
						</c:if>
					</div>
					<div>
						<c:if test="${isHated == -1}">
							<a href="doHate?memberId=${loginedMemberId}&id=${article.id}">싫어요<i class="far fa-thumbs-down"></i>[${hateCount}]
							</a>
						</c:if>
						<c:if test="${isHated == 1}">
							<a href="doLikeCancel?memberId=${loginedMemberId}&id=${article.id}">싫어요<i class="fas fa-thumbs-down"></i>[${hateCount}]
							</a>
						</c:if>
					</div>
				</div>
				<hr>
				<div class="article-detail__bottom flex jc-space-between flex-ai-c">
					<div class="flex">
						<div>
							<a class="" href="detail?id=${article.id-1}">&lt; 이전글</a>
						</div>
						<div>
							<a href="list?boardId=${article.boardId}">목록</a>
						</div>
						<div>
							<a class="" href="detail?id=${article.id+1}"> 다음글 &gt;</a>
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
				<div>
					<c:forEach items="${replies}" var="reply">
						<hr />
						<div class="reply-box">
							<div class="reply-box__body">
								<div class="flex">
									<div>${reply.extra_writer}</div>
									<div>${reply.regDate}</div>
								</div>
								<div class="">${reply.body}</div>
							</div>
							<div class="reply-box__function flex">
								<div>
									<c:if test="${loginedMemberId == reply.memberId}">
										<a href="../reply/doReplyDelete?id=${reply.id}">삭제</a>
									</c:if>
									<a href="javascript:toggleLayer('xx');">답글</a>
									<div id="xx" style="display: none;">
										<div class="reply-box con-max-width">
											<div class="con flex-ai-c flex">
												<div class="reply-box__textarea flex-g-1">
												<!-- <input type="hidden" name="replyId" value="${reply.id}"/> -->
													<textarea name="reReplyBody" placeholder="내용을 입력해 주세요."></textarea>
												</div>
												<div class="reply-box__btn">
													<input type="image" alt="댓글입력" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<div class="reply-box con-max-width">
					<div class="con flex-ai-c flex">
						<div class="reply-box__textarea flex-g-1">
							<textarea name="replyBody" placeholder="내용을 입력해 주세요."></textarea>
						</div>
						<div class="reply-box__btn">
							<input type="image" alt="댓글입력" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
						</div>
					</div>
				</div>
			</section>
		</form>
	</div>
	<%@ include file="../../part/foot.jspf"%>