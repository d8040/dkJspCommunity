<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" value="${board.name}게시물 리스트" />
<%@ include file="../../part/head.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<main class="flex-g-1 con">
 <h1>${pageTitle}</h1>

        <hr />
        <div class="flex flex-jc-c">
          <div class="article-list con-min-width">
            <div class="search-box">
              <script>
                let DoSearchForm__submited = false;
                function DoSearchForm__submit(form) {
                  if ( DoSearchForm__submited ) {
                    alert('처리중입니다');
                    return;
                  }
                  form.searchKeyword.value = form.searchKeyword.value.trim();

                  if ( form.searchKeyword.value.length == 0 ) {
                    alert('검색어를 입력해주세요.');
                    form.searchKeyword.focus();
                    return;
                  }

                  form.submit();
                  DoSearchForm__submited = true;
                }
              </script>
              <form onsubmit="DoSearchForm__submit(this); return false;">
                <input type="hidden" name="boardId" value="${param.boardId}" /> <select name="searchKeywordType">
                <option value="titleAndBody">제목+본문</option>
                <option value="title">제목</option>
                <option value="body">본문</option>
                </select>
                <script>
                  const param__searchKeywordType = '${param.searchKeywordType}';

                  if ( param__searchKeywordType ) {
                    $('select[name="searchKeywordType"]').val(param__searchKeywordType);
                  }
                </script>
                <input value="${param.searchKeyword}" type="text" name="searchKeyword" placeholder="검색어를 입력해주세요." /> <input type="submit" value="검색" />
              </form>
            </div>
            <div>총 게시물 수: ${totalCount}</div>
            <hr />
            <div class="header">
              <div class="article-list__title flex flex">
                <div class="article-list__cell-id">번호</div>
                <div class="article-list__cell-writer">작성자</div>
                <div class="article-list__cell-title">제목</div>
                <div class="article-list__cell-reg-date">등록일</div>
                <div class="article-list__cell-rcm">추천</div>
                <div class="article-list__cell-hit">조회</div>
              </div>
            </div>
            <c:forEach items="${articles}" var="article">
              <div class="content article-box">
                <div class="article-list__content__web flex">
                  <div class=article-list__cell-id>${article.id}</div>
                  <div class=article-list__cell-writer>${article.extra_writer}</div>
                  <div class=article-list__cell-title>
                    <a href="detail?id=${article.id}" class=hover-underline>${article.title}  <i class="far fa-comment-dots"></i>${article.extra_replyCount}</a>
                  </div>
                  <fmt:parseDate var="parseRegDate" value="${article.regDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                  <fmt:formatDate var="formatRegDate" value="${parseRegDate}" pattern="yyyy-MM-dd" />
                  <div class=article-list__cell-reg-date>${formatRegDate}</div>
                  <div class=article-list__cell-rcm>${article.extra_likeCount}</div>
                  <div class=article-list__cell-hit>${article.hitsCount}</div>
                </div>
                <div class="article-list__content__mobile">
                <a href="detail?id=${article.id}" class=hover-underline>
                  <div class="flex jc-space-between">
                    <div class=article-list__cell-title1>
                      ${article.title} 
                    </div>
                    <div class=article-list__cell-hit1>${article.hitsCount}</div>
                  </div>
                  <div class="flex jc-space-between">
                    <div class=article-list__cell-writer1>${article.extra_writer}</div>
                    <div class=article-list__cell-reg-date1>${formatRegDate}</div>
                  </div> 
                  </a>                 
                </div>
              </div>
            </c:forEach>
            <style>
              .red {
                color: red;
              }
            </style>
            <div class="main paging">
              <div class="page-box con-min-witdh">
                <div class="page flex flex-jc-c con">
                  <div class="page-no selected">
                    <c:if test="${pageBoxStartBeforeBtnNeedToShow}">
                      <c:set var="aUrl" value="?page=${pageBoxStartBeforePage}&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
                      <a href="${aUrl}">◀</a>
                    </c:if>
                    <c:forEach var="i" begin="${pageBoxStartPage}" end="${pageBoxEndPage}" step="1">
                      <c:set var="aClass" value="${page == i ? 'red' : ''}" />
                      <c:set var="aUrl" value="?page=${i}&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
                      <a class="${aClass}" href="${aUrl}">${i}</a>
                    </c:forEach>

                    <c:if test="${pageBoxEndAfterBtnNeedToShow}">
                      <c:set var="aUrl" value="?page=${pageBoxEndAfterPage}&boardId=${param.boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
                      <a href="${aUrl}">▶</a>
                    </c:if>
                  </div>
                </div>
              </div>
            </div>
            <div class="flex flex-jc-end">
              <a class="btn" href="write?boardId=${param.boardId}">게시물 작성</a>
            </div>
          </div>
	<%@ include file="../../part/foot.jspf"%>