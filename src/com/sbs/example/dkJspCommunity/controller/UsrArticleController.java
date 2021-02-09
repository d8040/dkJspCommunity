package com.sbs.example.dkJspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Board;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.dto.Reply;
import com.sbs.example.dkJspCommunity.service.ArticleService;
import com.sbs.example.dkJspCommunity.service.ReplyService;
import com.sbs.example.util.Util;

public class UsrArticleController extends Controller {

    private ArticleService articleService;
    private ReplyService replyService;

    public UsrArticleController() {
	articleService = Container.articleService;
	replyService = Container.replyService;
    }

    public String showList(HttpServletRequest req, HttpServletResponse resp) {
	String searchKeyword = req.getParameter("searchKeyword");
	String searchKeywordType = req.getParameter("searchKeywordType");

	int itemsInAPage = 15;
	int page = Util.getAsInt(req.getParameter("page"), 1);
	int limitStart = (page - 1) * itemsInAPage;

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
	int id = Util.getAsInt(req.getParameter("id"), 0);
	int replyId = Util.getAsInt(req.getParameter("replyId"), 0);
	
	System.out.println(replyId);

	
	if (id == 0) {
	    return msgAndBack(req, "게시물 번호를 입력해주세요.");
	}
	Member loginedMember = (Member)req.getAttribute("loginedMember");
		
	Article article = articleService.getForPrintArticleById(id, loginedMember);	
	if (article == null) {
	    return msgAndBack(req, id + "번 게시물은 존재하지 않습니다.");
	}

	List<Reply> replies = replyService.getForPrintRepliesByArticleId(article.id);
	
	req.setAttribute("article", article);
	req.setAttribute("replies", replies);
	return "usr/article/detail";
    }

    public String showWrite(HttpServletRequest req, HttpServletResponse resp) {
	int boardId = Util.getAsInt(req.getParameter("boardId"), 0);

	if (boardId == 0) {
	    return msgAndBack(req, "게시판 번호를 입력해주세요.");
	}

	Board board = articleService.getBoardById(boardId);

	if (board == null) {
	    return msgAndBack(req, "게시판은 존재하지 않습니다.");
	}

	req.setAttribute("board", board);
	return "usr/article/write";
    }

    public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
	int memberId = (int) req.getAttribute("loginedMemberId");
	int boardId = Util.getAsInt(req.getParameter("boardId"), 0);

	if (boardId == 0) {
	    return msgAndBack(req, "게시판 번호를 입력해주세요.");
	}

	String title = req.getParameter("title");

	if (Util.isEmpty(title)) {
	    return msgAndBack(req, "제목을 입력해 주세요");
	}

	String body = req.getParameter("body");

	if (Util.isEmpty(body)) {
	    return msgAndBack(req, "내용을 입력해 주세요");
	}

	Map<String, Object> writeArgs = new HashMap<>();
	writeArgs.put("boardId", boardId);
	writeArgs.put("memberId", memberId);
	writeArgs.put("title", title);
	writeArgs.put("body", body);

	int newArticleId = articleService.write(writeArgs);

	return msgAndReplace(req, newArticleId + "번 게시물이 생성되었습니다.", String.format("detail?id=%d", newArticleId));
    }

    public String showModify(HttpServletRequest req, HttpServletResponse resp) {
	int memberId = (int) req.getAttribute("loginedMemberId");
	int id = Util.getAsInt(req.getParameter("id"), 0);
	
	if (id == 0) {
	    return msgAndBack(req, "게시물 번호를 입력해주세요.");
	}

	Article article = articleService.getForPrintArticleById(id);
	if (article == null) {
	    return msgAndBack(req, id + "번 게시물은 존재하지 않습니다.");
	}

	if (memberId != article.getMemberId()) {
	    return msgAndBack(req, id + "번 게시물에 대한 권한이 없습니다.");
	}

	req.setAttribute("article", article);
	return "usr/article/modify";
    }

    public String doModify(HttpServletRequest req, HttpServletResponse resp) {
	int memberId = (int) req.getAttribute("loginedMemberId");
	int boardId = Util.getAsInt(req.getParameter("boardId"), 0);

	if (boardId == 0) {
	    return msgAndBack(req, "게시판 번호를 입력해주세요.");
	}

	int id = Util.getAsInt(req.getParameter("id"), 0);

	if (id == 0) {
	    return msgAndBack(req, "게시물 번호를 입력해주세요.");
	}

	String title = req.getParameter("title");

	if (Util.isEmpty(title)) {
	    return msgAndBack(req, "제목을 입력해 주세요");
	}

	String body = req.getParameter("body");

	if (Util.isEmpty(body)) {
	    return msgAndBack(req, "내용을 입력해 주세요");
	}

	Article article = articleService.getForPrintArticleById(id);
	
	if (article == null) {
	    return msgAndBack(req, id + "번 게시물은 존재하지 않습니다.");
	}
	
	if (memberId != article.getMemberId()) {
	    return msgAndBack(req, id + "번 게시물에 대한 권한이 없습니다.");
	}
	
	Map<String, Object> modifyArgs = new HashMap<>();
	modifyArgs.put("boardId", boardId);
	modifyArgs.put("memberId", memberId);
	modifyArgs.put("id", id);
	modifyArgs.put("title", title);
	modifyArgs.put("body", body);

	int newArticleId = articleService.modify(modifyArgs);

	return msgAndReplace(req, newArticleId + "번 게시물이 수정되었습니다.", String.format("detail?id=%d", id));
    }

    public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
	int memberId = (int) req.getAttribute("loginedMemberId");
	int id = Util.getAsInt(req.getParameter("id"), 0);

	if (id == 0) {
	    return msgAndBack(req, "게시물 번호를 입력해주세요.");
	}
	
	Article article = articleService.getForPrintArticleById(id);

	if (article == null) {
	    return msgAndBack(req, id + "번 게시물이 존재하지 않습니다.");
	}

	if (memberId != article.getMemberId()) {
	    return msgAndBack(req, id + "번 게시물에 대한 권한이 없습니다.");
	}

	Map<String, Object> delArgs = new HashMap<>();
	delArgs.put("memberId", memberId);
	delArgs.put("id", id);

	int newArticleId = articleService.delete(delArgs);
	
	return msgAndReplace(req, newArticleId + "번 게시물이 삭제되었습니다.", String.format("list?boardId=%d", article.boardId));
    }

    public String doLike(HttpServletRequest req, HttpServletResponse resp) {
	int memberId = (int) req.getAttribute("loginedMemberId");
	int id = Integer.parseInt(req.getParameter("id"));	
	String relTypeCode = req.getParameter("relTypeCode");
	
	System.out.println("id::"+id);
	System.out.println("relTypeCode: "+relTypeCode);
	if (id == 0) {
	    return msgAndBack(req, "게시물 번호를 입력해주세요.");
	}
	
	return msgAndReplace(req, "좋아요가 추가되었습니다.", String.format("detail?id=%d", id));
    }

    public String doHate(HttpServletRequest req, HttpServletResponse resp) {
	int memberId = (int) req.getAttribute("loginedMemberId");
	int id = Integer.parseInt(req.getParameter("id"));	
	String relTypeCode = req.getParameter("relTypeCode");

	System.out.println("id::"+id);
	System.out.println("relTypeCode: "+relTypeCode);
	if (id == 0) {
	    return msgAndBack(req, "게시물 번호를 입력해주세요.");
	}
	

	return msgAndReplace(req, "싫어요가 추가되었습니다.", String.format("detail?id=%d", id));
    }

    public String doLikeCancel(HttpServletRequest req, HttpServletResponse resp) {
	int memberId = (int) req.getAttribute("loginedMemberId");
	int id = Integer.parseInt(req.getParameter("id"));	
	String relTypeCode = req.getParameter("relTypeCode");

	System.out.println("id::"+id);
	System.out.println("relTypeCode: "+relTypeCode);
	if (id == 0) {
	    return msgAndBack(req, "게시물 번호를 입력해주세요.");
	}

	return msgAndReplace(req, "좋아요가 취소되었습니다.", String.format("detail?id=%d", id));
    }

    public String doHateCancel(HttpServletRequest req, HttpServletResponse resp) {
	int memberId = (int) req.getAttribute("loginedMemberId");
//	int id = Util.getAsInt(req.getParameter("id"), 0);
	int id = Integer.parseInt(req.getParameter("id"));	
	String relTypeCode = req.getParameter("relTypeCode");

	System.out.println("id::"+id);
	System.out.println("relTypeCode: "+relTypeCode);
	if (id == 0) {
	    return msgAndBack(req, "게시물 번호를 입력해주세요.");
	}
	
	return msgAndReplace(req, "싫어요가 최소되었습니다.", String.format("detail?id=%d", id));
    }
}
