package com.sbs.example.dkJspCommunity.servlet.usr;

import java.util.List;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.ArticleDao;
import com.sbs.example.dkJspCommunity.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;

	
	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public List<Article> getForPrintArticles(int boardId) {
		return articleDao.getForPrintArticles(boardId);
	}

}
