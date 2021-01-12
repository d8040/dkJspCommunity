package com.sbs.example.dkJspCommunity.service;

import java.util.List;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.ArticleDao;
import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Board;

public class ArticleService {
	
	private ArticleDao articleDao;

	
	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticles(int boardId) {
		return articleDao.getForPrintArticles(boardId);
	}

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public Board getBoardById(int id) {
		return articleDao.getBoardById(id);
	}

}
