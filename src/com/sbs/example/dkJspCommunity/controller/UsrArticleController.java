package com.sbs.example.dkJspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Board;
import com.sbs.example.dkJspCommunity.service.ArticleService;
import com.sbs.example.util.Util;

public class UsrArticleController {

    private ArticleService articleService;

    public UsrArticleController() {
	articleService = Container.articleService;
    }

    public String showList(HttpServletRequest req, HttpServletResponse resp) {
	String searchKeyword = req.getParameter("searchKeyword");
	String searchKeywordType = req.getParameter("searchKeywordType");
	
	int itemsInAPage = 15;	
	int page = Util.getAsInt(req.getParameter("page"), 1);
	int limitStart = (page -1)* itemsInAPage;
	
	int boardId = Integer.parseInt(req.getParameter("boardId"));

	Board board = articleService.getBoardById(boardId);
	req.setAttribute("board", board);

	int totalCount = articleService.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);
	List<Article> articles = articleService.getForPrintArticlesByBoardId(boardId, limitStart, itemsInAPage, searchKeywordType, searchKeyword);

	int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);

	int pageBoxSize = 10;

	// 현재 페이지 박스 시작, 끝 계산

	int previousPageBoxesCount = (page - 1) / pageBoxSize;
	int pageBoxStartPage = pageBoxSize * previousPageBoxesCount + 1;
	int pageBoxEndPage = pageBoxStartPage + pageBoxSize - 1;

	if (pageBoxEndPage > totalPage) {
	    pageBoxEndPage = totalPage;
	}

	// 이전버튼 페이지 계산
	int pageBoxStartBeforePage = pageBoxStartPage - 1;
	if (pageBoxStartBeforePage < 1) {
	    pageBoxStartBeforePage = 1;
	}

	// 다음버튼 페이지 계산
	int pageBoxEndAfterPage = pageBoxEndPage + 1;

	if (pageBoxEndAfterPage > totalPage) {
	    pageBoxEndAfterPage = totalPage;
	}

	// 이전버튼 노출여부 계산
	boolean pageBoxStartBeforeBtnNeedToShow = pageBoxStartBeforePage != pageBoxStartPage;
	// 다음버튼 노출여부 계산
	boolean pageBoxEndAfterBtnNeedToShow = pageBoxEndAfterPage != pageBoxEndPage;

	req.setAttribute("totalCount", totalCount);
	req.setAttribute("articles", articles);
	req.setAttribute("totalPage", totalPage);
	req.setAttribute("page", page);

	req.setAttribute("pageBoxStartBeforeBtnNeedToShow", pageBoxStartBeforeBtnNeedToShow);
	req.setAttribute("pageBoxEndAfterBtnNeedToShow", pageBoxEndAfterBtnNeedToShow);
	req.setAttribute("pageBoxStartBeforePage", pageBoxStartBeforePage);
	req.setAttribute("pageBoxEndAfterPage", pageBoxEndAfterPage);
	req.setAttribute("pageBoxStartPage", pageBoxStartPage);
	req.setAttribute("pageBoxEndPage", pageBoxEndPage);
	
	return "usr/article/list";
    }

    public String showDetail(HttpServletRequest req, HttpServletResponse resp) {
	int id = Integer.parseInt(req.getParameter("id"));

	Article article = articleService.getForPrintArticleById(id);
	
	if (article == null) {
	    req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	Container.articleService.hitCount(id);
	int likeCount = Container.articleService.likeCount(id);
	int hateCount = Container.articleService.hateCount(id);
	
	
	req.setAttribute("likeCount", likeCount);
	req.setAttribute("hateCount", hateCount);
	req.setAttribute("article", article);
	return "usr/article/detail";
    }

    public String showWrite(HttpServletRequest req, HttpServletResponse resp) {
	int boardId = Integer.parseInt(req.getParameter("boardId"));

	Board board = articleService.getBoardById(boardId);
	req.setAttribute("board", board);
	return "usr/article/write";
    }

    public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
	int boardId = Integer.parseInt(req.getParameter("boardId"));
	int memberId = (int) req.getAttribute("loginedMemberId");
	String title = req.getParameter("title");
	String body = req.getParameter("body");

	Map<String, Object> writeArgs = new HashMap<>();
	writeArgs.put("boardId", boardId);
	writeArgs.put("memberId", memberId);
	writeArgs.put("title", title);
	writeArgs.put("body", body);

	int newArticleId = articleService.write(writeArgs);

	req.setAttribute("alertMsg", newArticleId + "번 게시물이 생성되었습니다.");
	req.setAttribute("replaceUrl", String.format("detail?id=%d", newArticleId));
	return "common/redirect";
    }

    public String showModify(HttpServletRequest req, HttpServletResponse resp) {
	int memberId = (int) req.getAttribute("loginedMemberId");
	int id = Integer.parseInt(req.getParameter("id"));

	Article article = articleService.getForPrintArticleById(id);
	if (article == null) {
	    req.setAttribute("alertMsg", id + "번 게시물이 존재하지 않습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	if (memberId != article.getMemberId()) {
	    req.setAttribute("alertMsg", "게시물에 대한 권한이 없습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	req.setAttribute("article", article);
	return "usr/article/modify";
    }

    public String doModify(HttpServletRequest req, HttpServletResponse resp) {
	int boardId = Integer.parseInt(req.getParameter("boardId"));
	int memberId = (int) req.getAttribute("loginedMemberId");
	int id = Integer.parseInt(req.getParameter("id"));
	String title = req.getParameter("title");
	String body = req.getParameter("body");

	Article article = articleService.getForPrintArticleById(id);

	if (memberId != article.getMemberId()) {
	    req.setAttribute("alertMsg", "게시물에 대한 권한이 없습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}
	Map<String, Object> modifyArgs = new HashMap<>();
	modifyArgs.put("boardId", boardId);
	modifyArgs.put("memberId", memberId);
	modifyArgs.put("id", id);
	modifyArgs.put("title", title);
	modifyArgs.put("body", body);
	
	System.out.println(body);

	int newArticleId = articleService.modify(modifyArgs);

	req.setAttribute("alertMsg", id + "번 게시물이 수정되었습니다.");
	req.setAttribute("replaceUrl", String.format("detail?id=%d", id));
	return "common/redirect";
    }

    public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
	int id = Integer.parseInt(req.getParameter("id"));
	int memberId = (int) req.getAttribute("loginedMemberId");
	Article article = articleService.getForPrintArticleById(id);

	if (article == null) {
	    req.setAttribute("alterMsg", id + "번 게시물은 존재하지 않습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	if (memberId != article.getMemberId()) {
	    req.setAttribute("alertMsg", "게시물에 대한 권한이 없습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	Map<String, Object> delArgs = new HashMap<>();
	delArgs.put("memberId", memberId);
	delArgs.put("id", id);

	int newArticleId = articleService.delete(delArgs);

	req.setAttribute("alertMsg", id + "번 게시물이 삭제되었습니다");
	req.setAttribute("replaceUrl", String.format("list?boardId=%d", article.boardId));

	return "common/redirect";
    }

	public String doLike(HttpServletRequest req, HttpServletResponse resp) {
		int memberId = (int) req.getAttribute("loginedMemberId");
		int articleId = Integer.parseInt(req.getParameter("id"));
				
		Container.likeService.doLike(memberId, articleId, 1, 0);		
		
		req.setAttribute("replaceUrl", String.format("detail?id=%d", articleId));		
		return "common/redirect";
	}

	public String doHate(HttpServletRequest req, HttpServletResponse resp) {
		int memberId = (int) req.getAttribute("loginedMemberId");
		int articleId = Integer.parseInt(req.getParameter("id"));
		
		Container.likeService.doLike(memberId, articleId, 0, 1);
		
		req.setAttribute("replaceUrl", String.format("detail?id=%d", articleId));		
		return "common/redirect";
	}
	public String doLikeCancel(HttpServletRequest req, HttpServletResponse resp) {
		int memberId = (int) req.getAttribute("loginedMemberId");
		int articleId = Integer.parseInt(req.getParameter("id"));
		
		Container.likeService.doLike(memberId, articleId, 0, 0);
		
		req.setAttribute("replaceUrl", String.format("detail?id=%d", articleId));		
		return "common/redirect";
	}

	public String doHateCancel(HttpServletRequest req, HttpServletResponse resp) {
		int memberId = (int) req.getAttribute("loginedMemberId");
		int articleId = Integer.parseInt(req.getParameter("id"));
				
		Container.likeService.doLike(memberId, articleId, 0, 0);
		
		req.setAttribute("replaceUrl", String.format("detail?id=%d", articleId));
		return "common/redirect";
	}
}
