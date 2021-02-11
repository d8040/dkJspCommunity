<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="게시물 상세페이지" />
<%@ include file="../../part/head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<main class="flex-g-1 con">
	<div>
		<script>
          let Reply__submited = false;
          let Reply__checkedLoginId = "";

          function Reply__submit(form) {
            if (Reply__submited) {
              alert('처리중입니다.');
              return;
            }
            
            if ( ${loginedMemberId} == 0 ){
              alert('로그인 후 이용해 주세요.');
              return;
            }
            

            form.submit();
            Reply__submited = true;
          }         
        </script>
		<script>
          let DoReplyWriteForm__submited = false;
          let DoReplyWriteForm__checkedLoginId = "";

          // 폼 발송전 체크
          function DoReplyWriteForm__submit(form) {
            if ( DoReplyWriteForm__submited ) {
              alert('처리중입니다.');
              return;
            }
            if ( ${loginedMemberId} == 0 ){
              alert('로그인 후 이용해 주세요.');
              return;
            }
            if ( form.replyBody.value.length == 0 ) {
              alert('내용을 입력해주세요.');
              form.replyBody.value.focus();

              return;
            }
3
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
		<script>
          let DoReReplyWriteForm__submited = false;
          let DoReReplyWriteForm__checkedLoginId = "";

          function DoReReplyWriteForm__submit(form) {
            if ( DoReReplyWriteForm__submited ) {
              alert('처리중입니다.');
              return;
            }

            if ( ${loginedMemberId} == 0 ){
              alert('로그인 후 이용해 주세요.');
              return;
            }
            form.submit();
            DoReReplyWriteForm__submited = true;
          }         
        </script>
		<script>
          let DoReReReplyWriteForm__submited = false;
          let DoReReReplyWriteForm__checkedLoginId = "";

          function DoReReReplyWriteForm__submit(form) {
            if ( DoReReReplyWriteForm__submited ) {
              alert('처리중입니다.');
              return;
            }
            
            if ( ${loginedMemberId} == 0 ){
              alert('로그인 후 이용해 주세요.');
              return;
            }
            

            form.submit();
            DoReReReplyWriteForm__submited = true;
          }         
        </script>

		<section class="article-detail con-min-width">
			<div class="article_detail__board-onclick">
				<a href="#" onclick="goBack()">게시판 > ${pageTitle} > </a>
			</div>
			<div class="article-detail__title">${article.title}</div>
			<div class="article-detail__info">
				<div class="flex jc-space-between">
					<div>작성자: ${article.extra__writer}</div>
					<div>조회수: ${article.hitsCount}</div>
				</div>
				<div class="flex jc-space-between">
					<div>작성일: ${article.regDate}</div>
					<div>추천수: ${article.extra__likeOnlyPoint}</div>
				</div>
			</div>
			<hr>
			<div class="article-detail__body">
				<script type="text/x-template">${article.body}</script>
				<div class="toast-ui-viewer"></div>
			</div>
			<div class="article-detail__articleRcm flex">
				<div>
					<c:choose>
						<c:when test="${article.extra.actorCanLike}">
							<a onclick="history.go(0)" href="../like/doLike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}">좋아요<i class="far fa-thumbs-up"></i>[${article.extra__likeOnlyPoint}]
							</a>
						</c:when>
						<c:when test="${article.extra.actorCanCancelLike}">
							<a onclick="history.go(0)" href="../like/doCancelLike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}">좋아요<i class="fas fa-thumbs-up"></i>[${article.extra__likeOnlyPoint}]
							</a>
						</c:when>
						<c:otherwise>
							<a onclick="history.go(0)" href="../like/doLike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}">좋아요<i class="far fa-thumbs-up"></i>[${article.extra__likeOnlyPoint}]
							</a>
						</c:otherwise>
					</c:choose>
				</div>
				<div>
					<c:choose>
						<c:when test="${article.extra.actorCanDislike}">
							<a onclick="history.go(0)" href="../like/doDislike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}">싫어요<i class="far fa-thumbs-down"></i>[${article.extra__dislikeOnlyPoint}]
							</a>
						</c:when>
						<c:when test="${article.extra.actorCanCancelDislike}">
							<a onclick="history.go(0)" href="../like/doCancelDislike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}">싫어요<i class="fas fa-thumbs-down"></i>[${article.extra__dislikeOnlyPoint}]
							</a>
						</c:when>
						<c:otherwise>
							<a onclick="history.go(0)" href="../like/doDislike?relTypeCode=article&relId=${article.id}&redirectUrl=${encodedCurrentUrl}">싫어요<i class="far fa-thumbs-down"></i>[${article.extra__dislikeOnlyPoint}]
							</a>
						</c:otherwise>
					</c:choose>
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
			<div id="reply-box">
				<hr />
				<c:forEach items="${replies}" var="reply">
					<c:if test="${reply.parentReplyId == 0}">
						<div class="reply-box">
							<div class="reply-box__body">
								<div class="reply-box__body__info flex flex-ai-end">
									<div>${reply.extra__writer}</div>
									<div class="reply-box__body__date">${reply.regDate}</div>
									<div class="reply-box__body__rcm flex flex-jc-end flex-g-1">
										<div>
											<c:choose>
												<c:when test="${reply.extra.actorCanLike}">
													<a onclick="history.go(0)" href="../like/doLike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}"><i class="far fa-thumbs-up"></i>[${reply.extra__likeOnlyPoint}] </a>
												</c:when>
												<c:when test="${reply.extra.actorCanCancelLike}">
													<a onclick="history.go(0)" href="../like/doCancelLike?relTypeCode=replye&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}"><i class="fas fa-thumbs-up"></i>[${reply.extra__likeOnlyPoint}] </a>
												</c:when>
												<c:otherwise>
													<a onclick="history.go(0)" href="../like/doLike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}"><i class="far fa-thumbs-up"></i>[${reply.extra__likeOnlyPoint}] </a>
												</c:otherwise>
											</c:choose>
										</div>
										<div>
											<c:choose>
												<c:when test="${reply.extra.actorCanDislike}">
													<a onclick="history.go(0)" href="../like/doDislike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}"><i class="far fa-thumbs-down"></i>[${reply.extra__dislikeOnlyPoint}] </a>
												</c:when>
												<c:when test="${replyLike.extra.actorCanCancelDislike}">
													<a onclick="history.go(0)" href="../like/doCancelDislike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}"><i class="fas fa-thumbs-down"></i>[${reply.extra__dislikeOnlyPoint}] </a>
												</c:when>
												<c:otherwise>
													<a onclick="history.go(0)" href="../like/doDislike?relTypeCode=reply&relId=${reply.id}&redirectUrl=${encodedCurrentUrl}"><i class="far fa-thumbs-down"></i>[${reply.extra__dislikeOnlyPoint}] </a>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="reply-box__body__body">${reply.body}</div>
								<div class="reply-box__function">
									<form action="../reply/doReplyWrite" method="POST" onsubmit="DoReReplyWriteForm__submit(this); return false;">
										<input type="hidden" name="articleId" value="${reply.articleId}" /> <input type="hidden" name="parentReplyId" value="${reply.id}" />
										<div class="flex flex-jc-end">
											<c:if test="${loginedMemberId == reply.memberId}">
												<a class="modify input" href="javascript:toggleLayer('${reply.id}modify');">수정</a>
												<a class="del input" onclick="return confirm('정말로 삭제하시겠습니까?')" href="../reply/doReplyDelete?id=${reply.id}&articleId=${reply.articleId}">삭제</a>
											</c:if>
											<a href="javascript:toggleLayer('${reply.id}xx');">답글</a>
										</div>
										<div class="re-reply-box" id="${reply.id}xx" style="display: none;">
											<div>&#11177;${reply.extra__writer}:: ${reply.extra__writer}님께 댓글쓰기</div>
											<div class="reply-box flex">
												<div class="reply-box__textarea flex-g-1">
													<textarea name="reReplyBody" placeholder="내용을 입력해 주세요.">@${reply.extra__writer}::</textarea>
												</div>
												<div class="reply-box__btn">
													<input class="input" type="image" alt="댓글입력" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
												</div>
											</div>
										</div>
									</form>
									<form action="../reply/doReplyModify" method="POST" onsubmit="Reply__submit(this); return false;">
										<input type="hidden" name="articleId" value="${reply.articleId}" /> <input type="hidden" name="replyId" value="${reply.id}" />
										<div class="re-reply-box" id="${reply.id}modify" style="display: none;">
											<div>댓글 수정</div>
											<div class="reply-box flex">
												<div class="reply-box__textarea flex-g-1">
													<textarea name="replyBody" placeholder="내용을 입력해 주세요.">${reply.body}</textarea>
												</div>
												<div class="reply-box__btn">
													<input class="input" type="image" alt="댓글입력" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						<c:forEach items="${replies}" var="reReply">
							<c:if test="${reply.id == reReply.parentReplyId}">
								<div class="reReply-box">
									<div class="reply-box__body">
										<div class="reply-box__body__info flex flex-ai-end">
											<div>&#11177; ${reReply.extra__writer}<br><br></div>
											<div class="reply-box__body__rcm flex flex-jc-end flex-g-1">
												<div>
													<c:if test="${reReply.extra.actorCanLike}">
														<a href="doLike?memberId=${loginedMemberId}&id=${reReply.id}&relTypeCode=reply"><i class="far fa-thumbs-up"></i>[${reReply.extra_likeCount}] </a>
													</c:if>
													<c:if test="${reReply.extra.actorCanCancelLike}">
														<a href="doLikeCancel?memberId=${loginedMemberId}&id=${reReply.id}&relTypeCode=reply"><i class="fas fa-thumbs-up"></i>[${reReply.extra_unlikeCount}] </a>
													</c:if>
												</div>
												<div>
													<c:if test="${reReply.extra.actorCanDislike}">
														<a href="doHate?memberId=${loginedMemberId}&id=${reReply.id}&relTypeCode=reply"><i class="far fa-thumbs-down"></i>[${reReply.extra_unlikeCount}] </a>
													</c:if>
													<c:if test="${reReply.extra.actorCanCancelDislike}">
														<a href="doLikeCancel?memberId=${loginedMemberId}&id=${reReply.id}&relTypeCode=reply"><i class="fas fa-thumbs-down"></i>[${reReply.extra_likeCount}] </a>
													</c:if>
												</div>
											</div>
										</div>
										<div class="reply-box__body__info flex flex-ai-end">
											<div>${reply.extra__writer}</div>
											<div class="reply-box__body__date">${reply.regDate}</div>
											<div class="reply-box__body__rcm flex flex-jc-end flex-g-1">
												<div>
													<c:choose>
														<c:when test="${reply.extra.actorCanLike}">
															<a onclick="history.go(0)" href="../like/doLike?relTypeCode=reply&relId=${reReply.id}&redirectUrl=${encodedCurrentUrl}"><i class="far fa-thumbs-up"></i>[${reReply.extra__likeOnlyPoint}] </a>
														</c:when>
														<c:when test="${reply.extra.actorCanCancelLike}">
															<a onclick="history.go(0)" href="../like/doCancelLike?relTypeCode=replye&relId=${reReply.id}&redirectUrl=${encodedCurrentUrl}"><i class="fas fa-thumbs-up"></i>[${reReply.extra__likeOnlyPoint}] </a>
														</c:when>
														<c:otherwise>
															<a onclick="history.go(0)" href="../like/doLike?relTypeCode=reply&relId=${reReply.id}&redirectUrl=${encodedCurrentUrl}"><i class="far fa-thumbs-up"></i>[${reReply.extra__likeOnlyPoint}] </a>
														</c:otherwise>
													</c:choose>
												</div>
												<div>
													<c:choose>
														<c:when test="${reply.extra.actorCanDislike}">
															<a onclick="history.go(0)" href="../like/doDislike?relTypeCode=reply&relId=${reReply.id}&redirectUrl=${encodedCurrentUrl}"><i class="far fa-thumbs-down"></i>[${reReply.extra__dislikeOnlyPoint}] </a>
														</c:when>
														<c:when test="${replyLike.extra.actorCanCancelDislike}">
															<a onclick="history.go(0)" href="../like/doCancelDislike?relTypeCode=reply&relId=${reReply.id}&redirectUrl=${encodedCurrentUrl}"><i class="fas fa-thumbs-down"></i>[${reReply.extra__dislikeOnlyPoint}] </a>
														</c:when>
														<c:otherwise>
															<a onclick="history.go(0)" href="../like/doDislike?relTypeCode=reply&relId=${reReply.id}&redirectUrl=${encodedCurrentUrl}"><i class="far fa-thumbs-down"></i>[${reReply.extra__dislikeOnlyPoint}] </a>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>
										<div class="reply-box__body__body">${reReply.body}</div>
										<div class="reply-box__function">
											<div class="flex flex-jc-end">
												<c:if test="${loginedMemberId == reReply.memberId}">
													<a class="modify input" href="javascript:toggleLayer('${reReply.id}modify');">수정</a>
													<a class="del input" onclick="return confirm('정말로 삭제하시겠습니까?')" href="../reply/doReplyDelete?id=${reReply.id}&articleId=${reReply.articleId}">삭제</a>
												</c:if>
												<a href="javascript:toggleLayer('${reReply.id}reply');">답글</a>
											</div>

											<form action="../reply/doReplyWrite" method="POST" onsubmit="DoReReReplyWriteForm__submit(this); return false;">
												<input type="hidden" name="articleId" value="${reReply.articleId}" /> <input type="hidden" name="parentReplyId" value="${reply.id}" />
												<div class="re-reply-box" id="${reReply.id}reply" style="display: none;">
													<div>&#11177; ${reply.extra__writer}:: ${reReply.extra__writer}님께 댓글쓰기</div>
													<div class="reply-box flex">
														<div class="reply-box__textarea flex-g-1">
															<textarea name="reReplyBody" placeholder="내용을 입력해 주세요.">@${reReply.extra__writer}::</textarea>
														</div>
														<div class="reply-box__btn">
															<input type="image" alt="댓글입력" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
														</div>
													</div>
												</div>
											</form>
											<form action="../reply/doReplyModify" method="POST" onsubmit="Reply__submit(this); return false;">
												<input type="hidden" name="articleId" value="${reReply.articleId}" /> 
												<input type="hidden" name="replyId" value="${reReply.id}" />
												<div class="re-reply-box" id="${reReply.id}modify" style="display: none;">
													<div>댓글 수정</div>
													<div class="reply-box flex">
														<div class="reply-box__textarea flex-g-1">
															<textarea name="reReplyBody" placeholder="내용을 입력해 주세요.">${reReply.body}</textarea>
														</div>
														<div class="reply-box__btn">
															<input type="image" alt="댓글입력" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
														</div>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</div>
			<form action="../reply/doReplyWrite" method="POST" onsubmit="DoReplyWriteForm__submit(this); return false;">
				<input type="hidden" name="articleId" value="${article.id}" />
				<div class="reply-box con-max-width">
					<div class="con flex-ai-c flex">
						<div class="reply-box__textarea flex-g-1">
							<textarea name="replyBody" placeholder="내용을 입력해 주세요." style="white-space: pre"></textarea>
						</div>
						<div class="reply-box__btn">
							<input class="input" type="image" alt="댓글입력" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
						</div>
					</div>
				</div>
			</form>
		</section>
	</div>
</main>
<%@ include file="../../part/foot.jspf"%>