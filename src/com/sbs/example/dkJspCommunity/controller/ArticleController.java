package com.sbs.example.dkJspCommunity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.service.ArticleService;

public class ArticleController {

	private ArticleService articleService;
	
	public ArticleController() {
		articleService = Container.articleService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		
		List<Article> articles = articleService.getForPrintArticles(boardId);
		
		req.setAttribute("articles", articles);
		
		return "usr/article/list";
	}
	public String showDatail(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Article article = articleService.getArticle(id);
		
		req.setAttribute("article", article);
		
		return "usr/article/detail";
	}

}
