package com.sbs.example.dkJspCommunity.container;

import com.sbs.example.dkJspCommunity.dao.ArticleDao;
import com.sbs.example.dkJspCommunity.servlet.usr.ArticleService;

public class Container {

	public static ArticleService articleService;
	public static ArticleDao articleDao;
	
	static {
		articleDao = new ArticleDao();
		articleService = new ArticleService();
	}

}
