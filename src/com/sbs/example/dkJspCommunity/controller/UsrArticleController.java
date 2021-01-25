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

public class UsrArticleController {

	private ArticleService articleService;

	public UsrArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));

		Board board = articleService.getBoardById(boardId);
		req.setAttribute("board", board);

		List<Article> articles = articleService.getForPrintArticles(boardId);

		req.setAttribute("articles", articles);

		return "usr/article/list";
	}

	public String showDatail(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));

		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

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
		int memberId = (int)req.getAttribute("loginedMemberId");
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
		int memberId = (int)req.getAttribute("loginedMemberId");
		int id = Integer.parseInt(req.getParameter("id"));		
		

		Article article = articleService.getForPrintArticleById(id);
		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물이 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		if(memberId != article.getMemberId()) {
		    req.setAttribute("alertMsg", "게시물에 대한 권한이 없습니다.");
		    req.setAttribute("historyBack", true);
		    return "common/redirect";
		}

		req.setAttribute("article", article);
		return "usr/article/modify";
	}
	
	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		int memberId = (int)req.getAttribute("loginedMemberId");
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		

		Article article = articleService.getForPrintArticleById(id);
		
		if(memberId != article.getMemberId()) {
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
		
		int newArticleId = articleService.modify(modifyArgs);
		
		req.setAttribute("alertMsg", id + "번 게시물이 수정되었습니다.");
		req.setAttribute("replaceUrl", String.format("detail?id=%d", id));
		return "common/redirect";
	}

	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		int memberId = (int)req.getAttribute("loginedMemberId");
		Article article = articleService.getForPrintArticleById(id);
		
		if (article == null) {
			req.setAttribute("alterMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		
		if(memberId != article.getMemberId()) {
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

}
