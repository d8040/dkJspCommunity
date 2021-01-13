package com.sbs.example.dkJspCommunity.service;

import java.util.List;
import java.util.Map;

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

	public Article getForPrintArticleById(int id) {
		return articleDao.getForPrintArticleById(id);
	}

	public Board getBoardById(int id) {
		return articleDao.getBoardById(id);
	}

	public int write(Map<String, Object> args) {
		return articleDao.write(args);
	}

	public int modify(Map<String, Object> modifyArgs) {
		return articleDao.modify(modifyArgs);
	}

	public Article getForPrintArticleByMemberIdAndId(int memberId, int id) {
		return articleDao.getForPrintArticleByMemberIdAndId(memberId, id);
	}

	public int delete(Map<String, Object> delArgs) {
		return articleDao.delete(delArgs);
	}

}
