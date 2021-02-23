<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sbs.example.util.Util"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="게시물 상세페이지" />
<%@ include file="../../part/head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<main class="flex-g-1 con">
	<div>
		<script>
                let DoReplyModifyForm__submited = false;
                let DoReplyModifyForm__checkedLoginId = "";
                function DoReplyModifyForm__submit(form) {
                	
                  if (DoReplyModifyForm__submited) {
                    alert('처리중입니다.')
                    return
                  }

                  if ( ${loginedMemberId} == 0 ){
                    alert('로그인 후 이용해 주세요.')
                    return
                  }

                  if ( form.replyBody.value.length == 0 ) {
                    alert('내용을 입력해주세요.'),
                      form.replyBody.focus()
                    return
                  }
                  if (DoReplyWriteForm__submited == false){
                    form.submit()
                  }
                  DoReplyModifyForm__submited = true
                }         
              </script>
		<script>

                $(function() {
                  if ( param.focusReplyId ) {
                    const $target = $('.reply-box-list div[data-id="' + param.focusReplyId + '"]');
                    $target.addClass('focus');

                    setTimeout(function() {
                      const targetOffset = $target.offset();

                      $(window).scrollTop(targetOffset.top - 300);

                      setTimeout(function() {
                        $target.removeClass('focus');
                      }, 1000);
                    }, 1000);
                  }
                });		
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
			<script>
                  /* 게시물 좋아요, 싫어요 시작 */
                  function callDoLike(reltyepCode, id) {
                    if ( ${loginedMemberId} == 0 ){
                      alert('로그인 후 이용해 주세요.');
                      return;
                    }
                    $.get('../like/doLikeAjax',
                          {
                      relTypeCode:reltyepCode,
                      relId:id
                    },
                          function(data){
                      $('.article-like-point').text('['+ data.body.extra__likeOnlyPoint+']');
                      $('.article-dislike-point').text('['+ data.body.extra__dislikeOnlyPoint+']');
                      $('.article-detail__articleRcm > div > a > #dislike').attr('class', 'far fa-thumbs-down');
                      if(data.resultCode == 'F-1'){
                        $('#like').replaceWith('<i id="like" class="far fa-thumbs-up"></i>');
                      }else{
                        $('#like').replaceWith('<i id="like" class="fas fa-thumbs-up"></i>');
                      }								
                    },																						
                          'json',						
                         );
                  }	
                  function callDoDislike(reltyepCode, id) {
                    if ( ${loginedMemberId} == 0 ){
	                      alert('로그인 후 이용해 주세요.');
	                      return;
                    }
                    $.get('../like/doDislikeAjax',
                          {
	                      relTypeCode:reltyepCode,
	                      relId:id
                    },
                          function(data){
		                      $('.article-dislike-point').text('['+ data.body.extra__dislikeOnlyPoint+']');
		                      $('.article-like-point').text('['+ data.body.extra__likeOnlyPoint+']');
		                      $('.article-detail__articleRcm > div > a > #like').attr('class', 'far fa-thumbs-up');
		                      if(data.resultCode == 'F-1'){
		                        	$('#dislike').replaceWith('<i id="dislike" class="far fa-thumbs-down"></i>');
		                      }else{
		                        	$('#dislike').replaceWith('<i id="dislike" class="fas fa-thumbs-down"></i>');
		                      }								
                  		  },																						
                          'json',						
                         );
                  }	
                  /* 게시물 좋아요, 싫어요 끝 */

                  /* 댓글 좋아요, 싫어요 시작 */
                  function callDoReplyLike(reltyepCode, id) {
                    if ( ${loginedMemberId} == 0 ){
                      alert('로그인 후 이용해 주세요.');
                      return;
                    }
                    $.get('../like/doReplyLikeAjax',
                          {
                      relTypeCode:reltyepCode,
                      relId:id
                    },
                          function(data){
                      $('.reply-like-point'+data.body.id).text('['+ data.body.extra__likeOnlyPoint+']');
                      $('.reply-dislike-point'+data.body.id).text('['+ data.body.extra__dislikeOnlyPoint+']');
                      $('.reply-box__body__rcm'+data.body.id+' > div > a > #replyDislike'+data.body.id).attr('class', 'far fa-thumbs-down');
                      if(data.resultCode == "F-1"){
                        $('#replyLike'+data.body.id).replaceWith('<i id="replyLike'+data.body.id+'" class="far fa-thumbs-up"></i>');
                      }else {
                        $('#replyLike'+data.body.id).replaceWith('<i id="replyLike'+data.body.id+'" class="fas fa-thumbs-up"></i>');
                      }													
                    },
                          'json',						
                         );
                  }	
                  function callDoReplyDislike(reltyepCode, id) {
                    if ( ${loginedMemberId} == 0 ){
                      alert('로그인 후 이용해 주세요.');
                      return;
                    }
                    async:false,
                      $.get('../like/doReplyDislikeAjax',
                            {
                      relTypeCode:reltyepCode,
                      relId:id,
                      async:false,
                    },
                            function(data){
                      $('.reply-dislike-point'+data.body.id).text('['+ data.body.extra__dislikeOnlyPoint+']');
                      $('.reply-like-point'+data.body.id).text('['+ data.body.extra__likeOnlyPoint+']');
                      $('.reply-box__body__rcm'+data.body.id+' > div > a > #replyLike'+data.body.id).attr('class', 'far fa-thumbs-up');
                      if(data.resultCode == "F-1"){
                        $('#replyDislike'+data.body.id).replaceWith('<i id="replyDislike'+data.body.id+'" class="far fa-thumbs-down"></i>');
                      }else {
                        $('#replyDislike'+data.body.id).replaceWith('<i id="replyDislike'+data.body.id+'" class="fas fa-thumbs-down"></i>');
                      }								
                    },																						
                            'json',						
                           );
                  }	
                  /* 댓글 좋아요, 싫어요 끝 */
                </script>
			<!-- 게시물 좋아요, 싫어요 시작 -->
			<div class="article-detail__articleRcm flex">
				<div>
					<a href="#none" onclick="callDoLike('article', ${article.id});"> <span>좋아요</span> <c:if test="${loginedMemberId != 0}">
							<c:if test="${isLikedArticle == false}">
								<i id="like" class="far fa-thumbs-up"></i>
							</c:if>
							<c:if test="${isLikedArticle == true}">
								<i id="like" class="fas fa-thumbs-up"></i>
							</c:if>
						</c:if> <c:if test="${loginedMemberId == 0}">
							<i id="like" class="far fa-thumbs-up"></i>
						</c:if> <span class="article-like-point">[${article.extra__likeOnlyPoint}]</span>
					</a>
				</div>
				<div>
					<a href="#none" onclick="callDoDislike('article', ${article.id});"> <span>싫어요</span> <c:if test="${loginedMemberId != 0}">
							<c:if test="${isDislikedArticle == false}">
								<i id="dislike" class="far fa-thumbs-down"></i>
							</c:if>
							<c:if test="${isDislikedArticle == true}">
								<i id="dislike" class="fas fa-thumbs-down"></i>
							</c:if>
						</c:if> <c:if test="${loginedMemberId == 0}">
							<i id="dislike" class="far fa-thumbs-down"></i>
						</c:if> <span class="article-dislike-point">[${article.extra__dislikeOnlyPoint}]</span>
					</a>
				</div>
			</div>
			<!-- 게시물 좋아요, 싫어요 끝 -->
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
<!-- 				<script>
					$(function(){
						loadRepliesList(${article.id})
					});					 
				</script> -->
													
				<script>
		                let DoReReplyWriteForm__submited = false;
		                let DoReReplyWriteForm__checkedLoginId = "";
		                function DoReReplyWriteForm__submit(form) {
		                    var form = document.doReReplyWrite;
		                	
		                  if (DoReplyModifyForm__submited) {
		                    alert('처리중입니다.')
		                    return
		                  }
		
		                  if ( ${loginedMemberId} == 0 ){
		                    alert('로그인 후 이용해 주세요.')
		                    return
		                  }
		
		                  if ( form.reReplyBody.value.length == 0 ) {
		                    alert('내용을 입력해주세요.'),
		                    form.reReplyBody.focus()
		                    return
		                  }
		
		                  $.post('../reply/doReplyWriteAjax',
		                         {
				                    articleId: doReReplyWrite.articleId.value,
				                    loginedMemberId: doReReplyWrite.loginedMemberId.value,
				                    replyBody: doReReplyWrite.reReplyBody.value,
				                    parentReplyId: doReReplyWrite.parentReplyId.value,
				                    parentReplyMmeber: doReReplyWrite.parentReplyMmeber.value,
				                    redirectUrl: doReReplyWrite.redirectUrl.value
				                  },
		                         function(data){
				                      loadRepliesList(doReReplyWrite.articleId.value);   
				                  },
		                         'json',
		                        ),
		                        
		                  DoReReplyWriteForm__submited = true;
		                }         
		        </script>
				<script>
		                let DoReReReplyWriteForm__submited = false;
		                let DoReReReplyWriteForm__checkedLoginId = "";
		
		                function DoReReReplyWriteForm__submit(form) {
		                    var form = document.doReReReplyWrite;
		                	
		                  if (DoReReReplyWriteForm__submited) {
		                    alert('처리중입니다.')
		                    return
		                  }
		
		                  if ( ${loginedMemberId} == 0 ){
		                    alert('로그인 후 이용해 주세요.')
		                    return
		                  }
		
		                  if ( form.reReplyBody.value.length == 0 ) {
		                    alert('내용을 입력해주세요.'),
		                      form.reReplyBody.focus()
		                    return
		                  }
		
		                  $.post('../reply/doReplyWriteAjax',
		                         {
				                    articleId: doReReReplyWrite.articleId.value,
				                    loginedMemberId: doReReReplyWrite.loginedMemberId.value,
				                    replyBody: doReReReplyWrite.reReplyBody.value,
				                    parentReplyId: doReReReplyWrite.parentReplyId.value,
				                    parentReplyMmeber: doReReReplyWrite.parentReplyMmeber.value,
				                    redirectUrl: doReReReplyWrite.redirectUrl.value
				                  },
		                         function(data){
				                      loadRepliesList(doReReReplyWrite.articleId.value);   
				                   },
		                         'json',
		                        ),
		                        
		                  DoReReReplyWriteForm__submited = true;
		                }
		                </script>
		                <c:forEach items="${replies}" var="reply">
						<c:if test="${reply.parentReplyId == 0}">
							<div class="reply-box-list">
								<div class="reply-box" data-id="${reply.id}">
									<div class="reply-box__body">
										<div class="reply-box__body__info flex flex-ai-end">
											<div>${reply.extra__writer}</div>
											<div class="reply-box__body__date">${reply.regDate}</div>
											<div class="reply-box__body__rcm${reply.id} flex flex-jc-end flex-g-1">
												<div>
													<c:set var="likePointed" value="false" />
													<a href="#none" onclick="callDoReplyLike('reply', ${reply.id});"> 
														<c:forEach items="${likes}" var="like">
															<c:if test="${like.relId == reply.id && like.memberId == loginedMemberId}">
																<c:if test="${like.point == 1}">
																	<i id="replyLike${reply.id}" class="fas fa-thumbs-up"></i>
																	<c:set var="likePointed" value="true" />
																</c:if>
															</c:if>
														</c:forEach> 
														<c:if test="${likePointed == false}">
															<i id="replyLike${reply.id}" class="far fa-thumbs-up"></i>
															<c:set var="likePointed" value="false" />
														</c:if> 
														<span class="reply-like-point${reply.id}">[${reply.extra__likeOnlyPoint}]</span>
													</a>
												</div>
												<div>
													<c:set var="likePointed" value="false" />
													<a href="#none" onclick="callDoReplyDislike('reply', ${reply.id});"> 
														<c:forEach items="${likes}" var="like" varStatus="status">
															<c:if test="${like.relId == reply.id && like.memberId == loginedMemberId}">
																<c:if test="${like.point == -1}">
																	<i id="replyDislike${reply.id}" class="fas fa-thumbs-down"></i>
																	<c:set var="likePointed" value="true" />
																</c:if>
															</c:if>
														</c:forEach> 
														<c:if test="${likePointed == false}">
															<i id="replyDislike${reply.id}" class="far fa-thumbs-down"></i>
															<c:set var="likePointed" value="false" />
														</c:if> 
														<span class="reply-dislike-point${reply.id}">[${reply.extra__dislikeOnlyPoint}]</span>
													</a>
												</div>
											</div>
										</div>
										<div class="reply-box__body__body">${reply.body}</div>
										<div class="reply-box__function">
											<form name="doReReplyWrite" method="POST" action=""  onsubmit='DoReReplyWriteForm__submit(this); return false;'>
												<input type="hidden" name="redirectUrl" value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" /> 
												<input type="hidden" name="articleId" value="${article.id}" /> 
												<input type="hidden" name="parentReplyId" value="${reply.id}" /> 
												<input type="hidden" name="parentReplyMmeber" value="@${reply.extra__writer}::  " />
												<input type="hidden" name="loginedMemberId" value="${loginedMemberId}" />
												<div class="flex flex-jc-end">
													<c:if test="${loginedMemberId == reply.memberId}">
														<a class="modify input" href="javascript:toggleLayer('${reply.id}modify');">수정</a>
														<a class="del input" onclick="return confirm('정말로 삭제하시겠습니까?')" href="../reply/doReplyDelete?id=${reply.id}&articleId=${reply.articleId}">삭제</a>
													</c:if>
													<a href="javascript:toggleLayer('${reply.id}xx');">답글</a>
												</div>
												<div class="re-reply-box" id="${reply.id}xx" style="display: none;">
													<div>&#11177;${reply.extra__writer}:: ${reply.extra__writer}님께 댓글쓰기</div>
													<div class="reply-box flex flex-ai-c">
														<div class="reply-box__textarea flex-g-1">
															<textarea name="reReplyBody" placeholder="내용을 입력해 주세요."></textarea>
														</div>
														<div class="reply-box__btn">
															<input class="input" type="image" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
														</div>
													</div>
												</div>
											</form>
											<form action="" method="POST">
												<input type="hidden" name="redirectUrl" value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" /> 
												<input type="hidden" name="articleId" value="${article.id}" /> 
												<input type="hidden" name="replyId" value="${reply.id}" />
												<div class="re-reply-box" id="${reply.id}modify" style="display: none;">
													<div>댓글 수정</div>
													<div class="reply-box flex">
														<div class="reply-box__textarea flex-g-1">
															<textarea name="reReplyBody" placeholder="내용을 입력해 주세요.">${reply.body}</textarea>
														</div>
														<div class="reply-box__btn">
															<input class="input" type="image" alt="댓글입력" onsubmit="DoReplyModifyForm__submit(); return false;" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
														</div>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</c:if>
							<c:forEach items="${replies}" var="reReply">
								<c:if test="${reply.id == reReply.parentReplyId}">
									<div class="reply-box-list">
										<div class="reReply-box" data-id="${reReply.id}">
											<div class="reply-box__body">
												<div class="reply-box__body__info flex flex-ai-end">
													<div>${reReply.extra__writer}</div>
													<div class="reply-box__body__date">${reReply.regDate}</div>
													<div class="reply-box__body__rcm${reReply.id} flex flex-jc-end flex-g-1">
														<div>
															<c:set var="likePointed" value="false" />
															<a href="#none" onclick="callDoReplyLike('reply', ${reReply.id});"> 
																<c:forEach items="${likes}" var="like">
																	<c:if test="${like.relId == reReply.id && like.memberId == loginedMemberId}">
																		<c:if test="${like.point == 1}">
																			<i id="replyLike${reReply.id}" class="fas fa-thumbs-up"></i>
																			<c:set var="likePointed" value="true" />
																		</c:if>
																	</c:if>
																</c:forEach> 
																<c:if test="${likePointed == false}">
																	<i id="replyLike${reReply.id}" class="far fa-thumbs-up"></i>
																	<c:set var="likePointed" value="false" />
																</c:if> <span class="reply-like-point${reReply.id}">[${reReply.extra__likeOnlyPoint}]</span>
															</a>
														</div>
														<div>
															<c:set var="likePointed" value="false" />
															<a href="#none" onclick="callDoReplyDislike('reply', ${reReply.id});"> 
																<c:forEach items="${likes}" var="like" varStatus="status">
																	<c:if test="${like.relId == reReply.id && like.memberId == loginedMemberId}">
																		<c:if test="${like.point == -1}">
																			<i id="replyDislike${reReply.id}" class="fas fa-thumbs-down"></i>
																			<c:set var="likePointed" value="true" />
																		</c:if>
																	</c:if>
																</c:forEach> 
																<c:if test="${likePointed == false}">
																	<i id="replyDislike${reReply.id}" class="far fa-thumbs-down"></i>
																	<c:set var="likePointed" value="false" />
																</c:if> <span class="reply-dislike-point${reReply.id}">[${reReply.extra__dislikeOnlyPoint}]</span>
															</a>
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
													<form name="doReReReplyWrite" method="POST" action="" onsubmit='DoReReReplyWriteForm__submit(this); return false;' >
														<input type="hidden" name="redirectUrl" value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" /> 
														<input type="hidden" name="articleId" value="${article.id}" /> 
														<input type="hidden" name="parentReplyId" value="${reply.id}" /> 
														<input type="hidden" name="parentReplyMmeber" value="@${reply.extra__writer}::  " />
														<input type="hidden" name="loginedMemberId" value="${loginedMemberId}" />
														<div class="re-reply-box" id="${reReply.id}reply" style="display: none;">
															<div>&#11177; ${reply.extra__writer}:: ${reReply.extra__writer}님께 댓글쓰기</div>
															<div class="reply-box flex flex-ai-c">
																<div class="reply-box__textarea flex-g-1">
																	<textarea name="reReReplyBody" placeholder="내용을 입력해 주세요."></textarea>
																</div>
																<div class="reply-box__btn">
																	<input class="input" type="image" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
																</div>
															</div>
														</div>
													</form>
													<form action="../reply/doReplyModify" method="POST" onsubmit="DoReplyModifyForm__submit(this); return false;">
														<input type="hidden" name="redirectUrl" value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" /> 
														<input type="hidden" name="articleId" value="${article.id}" /> 
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
									</div>
								</c:if>
							</c:forEach>
					</c:forEach>
				</div>
				<script>
                  let DoReplyWriteForm__submited = false;
                  let DoReplyWriteForm__checkedLoginId = "";

                  // 폼 발송전 체크
                  function DoReplyWriteForm__submit() {
                    let form = document.doReplyWrite;

                    if (DoReplyModifyForm__submited) {
                      alert('처리중입니다.')
                      return false;
                    }

                    if ( ${loginedMemberId} == 0 ){
                      alert('로그인 후 이용해 주세요.')
                      return false;
                    }

                    if ( form.replyBody.value.length == 0 ) {
                      alert('내용을 입력해주세요.'),
                        form.replyBody.focus()
                      return false;
                    }

                    $.post('../reply/doReplyWriteAjax',
                           {
		                    articleId: doReplyWrite.articleId.value,
		                    loginedMemberId: doReplyWrite.loginedMemberId.value,
		                    replyBody: doReplyWrite.replyBody.value,
		                    redirectUrl: doReplyWrite.redirectUrl.value
		                  },
                           function(data){
		                      loadRepliesList(doReplyWrite.articleId.value);   
		                   },
                           'json',
                          ),

                      DoReplyWriteForm__submited = true;
                  }

                  function toggleLayer(layer){
                    var l = document.getElementById(layer);
                    if(l.style.display == "")
                    {
                      l.style.display = "none";
                    }

                    else if(l.style.display == "none")
                    {
                      l.style.display = "";
                    }
                  }   

                  function loadRepliesList(articleId){
                    $.get('../reply/getReplies',
                    		{
                      			articleId: articleId
                   		 	},
                   		 	function(data){
                   		 		if(data.body != null){
                   		 			
                   		 			
                   		 			for ( var i = 0; i < data.body.length; i++){
                       		 			var el = '';
                   		 				var reply = data.body[i];
                   		 				if (reply.parentReplyId == 0){

                           		 			
                           		 			el += '<div class="reply-box-list">';
                           		 			el += '<div class="reply-box" data-id="'+reply.id+'">';
                           		 			el += '<div class="reply-box__body">';
                           		 			el += '<div class="reply-box__body__info flex flex-ai-end">';
                           		 			el += '<div>'+reply.extra__writer+'</div>';
                           		 			el += '<div class="reply-box__body__date">'+reply.regDate+'</div>';
                           		 			el += '<div class="reply-box__body__rcm'+reply.id+' flex flex-jc-end flex-g-1"><div>';
                           		 			el += '<c:set var="likePointed" value="false" />';
                           		 			el += '<a href="#none" onclick="callDoReplyLike(\'reply\', '+reply.id+');">';
                           		 			el += '<c:forEach items="${likes}" var="like">';
                           		 			el += '<c:if test="\${like.relId == '+reply.id+' && like.memberId == loginedMemberId}">';
                           		 			el += '<c:if test="${like.point == 1}">';
                           		 			el += '<i id="replyLike'+reply.id+'" class="fas fa-thumbs-up"></i>';
                           		 			el += '<c:set var="likePointed" value="true" /></c:if></c:if>';
                           		 			el += '</c:forEach>';
                           		 			el += '<c:if test="${likePointed == false}">';
                               		 		el += '<i id="replyLike'+reply.id+'" class="far fa-thumbs-up"></i>';
                               		 		el += '<c:set var="likePointed" value="false" />';   
                           		 			el += '</c:if>';
                           		 			el += '<span class="reply-like-point'+reply.id+'">['+reply.extra__likeOnlyPoint+']</span></a></div><div>';
                           		 			el += '<c:set var="likePointed" value="false" />';
                           		 			el += '<a href="#none" onclick="callDoReplyDislike(\'reply\', '+reply.id+');">';
                           		 			el += '<c:forEach items="${likes}" var="like" varStatus="status">';
                           		 			el += '<c:if test="\${like.relId == '+reply.id+' && like.memberId == loginedMemberId}">';
                           		 			el += '<c:if test="${like.point == -1}">';
                           		 			el += '<i id="replyDislike'+reply.id+'" class="fas fa-thumbs-down"></i>';
                           		 			el += '<c:set var="likePointed" value="true" />';
                           		 			el += '</c:if>';
                           		 			el += '</c:if>';
                           		 			el += '</c:forEach>';
                           		 			el += '<c:if test="${likePointed == false}">';   
	                           		 		el += '<i id="replyDislike'+reply.id+'" class="far fa-thumbs-down"></i>';
	                           		 		el += '<c:set var="likePointed" value="false" />';                        		 			
                           		 			el += '</c:if>';
                           		 			el += '<span class="reply-dislike-point'+reply.id+'">['+reply.extra__dislikeOnlyPoint+']</span></a>';
                           		 			el += '</div></div></div>';
                           		 			el += '<div class="reply-box__body__body">'+reply.body+'</div>';
                           		 			el += '<div class="reply-box__function">';
                           		 			el += '<form name="doReReplyWrite" action="" method="POST">';
                           		 			el += '<input type="hidden" name="redirectUrl" value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />';
                           		 			el += '<input type="hidden" name="articleId" value="'+reply.articleId+'" />';
                           		 			el += '<input type="hidden" name="parentReplyId" value="'+reply.id+'" /> ';
                           		 			el += '<input type="hidden" name="parentReplyMmeber" value="@'+reply.extra__writer+'::  " />';
                           		 			el += '<div class="flex flex-jc-end">';

                   		 				}
                           		 			if (reply.memberId == ${loginedMemberId}){
	                           		 			el += '<a class="modify input" href="javascript:toggleLayer(\''+reply.id+'modify\');">수정</a>';
	                           		 			el += '<a class="del input" onclick="return confirm(\'정말로 삭제하시겠습니까?\')" href="../reply/doReplyDelete?id='+reply.id+'&articleId='+reply.articleId+'">삭제</a>';
                           		 			}
                           		 			
                           		 			el += '<a href="javascript:toggleLayer(\''+reply.id+'xx\');">답글</a></div>';
                           		 			el += '<div class="re-reply-box" id="'+reply.id+'xx" style="display: none;">';
                           		 			el += '<div>&#11177;'+reply.extra__writer+':: '+reply.extra__writer+'님께 댓글쓰기</div>';
                           		 			el += '<div class="reply-box flex flex-ai-c">';
                           		 			el += '<div class="reply-box__textarea flex-g-1">';
                           		 			el += '<textarea name="reReplyBody" placeholder="내용을 입력해 주세요."></textarea>';
                           		 			el += '</div>';
                           		 			el += '<div class="reply-box__btn">';
                           		 			el += '<input class="input" type="image" alt="댓글입력" onsubmit="DoReReplyWriteForm__submit(this); return false;" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />';
                           		 			el += '</div></div></div></form>';
                           		 			el += '<form action="../reply/doReplyModify" method="POST" onsubmit="DoReplyModifyForm__submit(this); return false;">';
                           		 			el += '<input type="hidden" name="redirectUrl" value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" /> ';
                           		 			el += '<input type="hidden" name="articleId" value="'+reply.articleId+'" /> ';
                           		 			el += '<input type="hidden" name="replyId" value="'+reply.id+'" />';
                           		 			el += '<div class="re-reply-box" id="'+reply.id+'modify" style="display: none;">';
                           		 			el += '<div>댓글 수정</div>';
                           		 			el += '<div class="reply-box flex">';
                           		 			el += '<div class="reply-box__textarea flex-g-1">';
                           		 			el += '<textarea name="replyBody" placeholder="내용을 입력해 주세요.">'+reply.body+'</textarea></div>';
                           		 			el += '<div class="reply-box__btn">';
                           		 			el += '<input class="input" type="image" alt="댓글입력" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />';
                           		 			el += '</div></div></div></form></div></div></div></div>';
                           		 			
                           		 			for( var k = 0; k < data.body.length; k++){
                           		 				var el1 = '';
                           		 				var reReply = data.body[k];
                           		 				
                           		 				if (reply.id == reReply.parentReplyId){
                           		 					 el1 += '<div class="reply-box-list">';
                           		 					 el1 += '<div class="reReply-box" data-id="'+reReply.id+'">';
                           		 					 el1 += '<div class="reply-box__body">';
                           		 					 el1 += '<div class="reply-box__body__info flex flex-ai-end">';
                           		 					 el1 += '<div>'+reReply.extra__writer+'</div>';
                           		 					 el1 += '<div class="reply-box__body__date">'+reReply.regDate+'</div>';
                           		 					 el1 += '<div class="reply-box__body__rcm'+reReply.id+' flex flex-jc-end flex-g-1"><div>';
                           		 					 el1 += '<c:set var="likePointed" value="false" />';
                           		 					 el1 += '<a href="#none" onclick="callDoReplyLike(\'reply\', '+reReply.id+');">';
                           		 					 el1 += '<c:forEach items="${likes}" var="like">';
                           		 					 el1 += '<c:if test="\${like.relId == '+reReply.id+' && like.memberId == loginedMemberId}">';
                           		 					 el1 += '<c:if test="${like.point == 1}">';
                           		 					 el1 += '<i id="replyLike'+reReply.id+'" class="fas fa-thumbs-up"></i>';
                           		 					 el1 += '<c:set var="likePointed" value="true" /></c:if></c:if></c:forEach>';
                           		 					 el1 += '<c:if test="${likePointed == false}">';
                           		 					 el1 += '<i id="replyLike'+reReply.id+'" class="far fa-thumbs-up"></i>';
                           		 					 el1 += '<c:set var="likePointed" value="false" /></c:if> ';
                           		 					 el1 += '<span class="reply-like-point'+reReply.id+'">['+reReply.extra__likeOnlyPoint+']</span></a></div><div>';
                           		 					 el1 += '<c:set var="likePointed" value="false" />';
                           		 					 el1 += '<a href="#none" onclick="callDoReplyDislike(\'reply\', '+reReply.id+');"> <c:forEach items="${likes}" var="like" varStatus="status">';
                           		 					 el1 += '<c:if test="\${like.relId == '+reReply.id+' && like.memberId == loginedMemberId}">';
                           		 					 el1 += '<c:if test="${like.point == -1}">';
                           		 					 el1 += '<i id="replyDislike'+reReply.id+'" class="fas fa-thumbs-down"></i>';
                           		 					 el1 += '<c:set var="likePointed" value="true" /></c:if></c:if></c:forEach>';
                           		 					 el1 += '<c:if test="${likePointed == false}">';
                           		 					 el1 += '<i id="replyDislike'+reReply.id+'" class="far fa-thumbs-down"></i>';
                           		 					 el1 += '<c:set var="likePointed" value="false" /></c:if>';
                           		 					 el1 += '<span class="reply-dislike-point'+reReply.id+'">['+reReply.extra__dislikeOnlyPoint+']</span></a></div></div></div>';
                           		 					 el1 += '<div class="reply-box__body__body">'+reReply.body+'</div>';
                           		 					 el1 += '<div class="reply-box__function">';
                           		 					 el1 += '<div class="flex flex-jc-end">';
                           		 					 el1 += '<c:if test="\${loginedMemberId == '+reReply.memberId+'}">';
                           		 					 el1 += '<a class="modify input" href="javascript:toggleLayer(\''+reReply.id+'modify\');">수정</a>';
                           		 					 el1 += '<a class="del input" onclick="return confirm(\'정말로 삭제하시겠습니까?\')" href="../reply/doReplyDelete?id='+reReply.id+'&articleId='+reReply.articleId+'">삭제</a></c:if>';
                           		 					 el1 += '<a href="javascript:toggleLayer(\''+reReply.id+'reply\');">답글</a></div>';
                           		 					 el1 += '<form name="doReReReplyWrite" method="POST" action="">';
                           		 					 el1 += '<input type="hidden" name="redirectUrl" value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" /> <input type="hidden" name="articleId" value="'+reReply.articleId+'" /> ';
                           		 					 el1 += '<input type="hidden" name="parentReplyId" value="'+reply.id+'" /> ';
                           		 					 el1 += '<input type="hidden" name="parentReplyMmeber" value="@'+reply.extra__writer+'::  " />';
                           		 					 el1 += '<div class="re-reply-box" id="'+reReply.id+'reply" style="display: none;">';
                           		 					 el1 += '<div>&#11177; '+reply.extra__writer+':: '+reReply.extra__writer+'님께 댓글쓰기</div>';
                           		 					 el1 += '<div class="reply-box flex flex-ai-c">';
                           		 					 el1 += '<div class="reply-box__textarea flex-g-1">';
                           		 					 el1 += '<textarea name="reReplyBody" placeholder="내용을 입력해 주세요."></textarea></div>';
                           		 					 el1 += '<div class="reply-box__btn">';
                           		 					 el1 += '<input type="image" alt="댓글입력" onsubmit="DoReReReplyWriteForm__submit(this); return false;" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />';
                           		 					 el1 += '</div></div></div></form>';
                           		 					 el1 += '<form action="../reply/doReplyModify" method="POST">';
                           		 					 el1 += '<input type="hidden" name="redirectUrl" value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" />';
                           		 					 el1 += '<input type="hidden" name="articleId" value="'+reReply.articleId+'" />';
                           		 					 el1 += '<input type="hidden" name="replyId" value="'+reReply.id+'" />';
                           		 					 el1 += '<div class="re-reply-box" id="'+reReply.id+'modify" style="display: none;">';
                           		 					 el1 += '<div>댓글 수정</div>';
                           		 					 el1 += '<div class="reply-box flex">';
                           		 					 el1 += '<div class="reply-box__textarea flex-g-1">';
                           		 					 el1 += '<textarea name="reReplyBody" placeholder="내용을 입력해 주세요.">'+reReply.body+'</textarea></div>';
                           		 					 el1 += '<div class="reply-box__btn">';
                           		 					 el1 += '<input type="image" alt="댓글입력" onsubmit="DoReplyModifyForm__submit(this); return false;" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />';
                           		 					 el1 += '</div></div></div></form></div></div></div></div>';
                           		 				}
                           		 			}
                   		 			}

                        /* for ( var i = 0; i < data.body.length; i++){
                          var reply = data.body[i];

                          if (reply.parentReplyId == 0){                    
                         
                        	  

                          }
                          $('#reply-box').append(el);
                          var el = '';
                        } */
                      			}
               		 			$('#reply-box').append(el);
               		 			$('#reply-box').append(el1);
                    		},
                    		'json',      
                      	);
                  }
                </script>
			<form name="doReplyWrite" method="POST" action="">
				<input type="hidden" name="redirectUrl" value="${Util.getNewUrl(currentUrl, 'focusReplyId', '[NEW_REPLY_ID]')}" /> 
				<input type="hidden" name="articleId" value="${article.id}" /> 
				<input type="hidden" name="loginedMemberId" value="${loginedMemberId}" />
				<div class="reply-box con-max-width">
					<div class="con flex-ai-c flex">
						<div class="reply-box__textarea flex-g-1">
							<textarea name="replyBody" placeholder="내용을 입력해 주세요." style="white-space: pre"></textarea>
						</div>
						<div class="reply-box__btn">
							<!-- <input type="button" onclick='DoReplyWriteForm__submit(); return false;' class="" /> -->
							<input class="input" type="image" alt="댓글입력" onclick='DoReplyWriteForm__submit(); return false;' src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FqAMVb%2FbtqVvO5fSVW%2F06ehRWdnfhWfs06Z4rkbsk%2Fimg.gif" />
						</div>
					</div>
				</div>
			</form>
		</section>
	</div>
</main>
<%@ include file="../../part/foot.jspf"%>