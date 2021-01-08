package com.sbs.example.dkJspCommunity.container;

import com.sbs.example.dkJspCommunity.dao.ArticleDao;
import com.sbs.example.dkJspCommunity.dao.MemberDao;
import com.sbs.example.dkJspCommunity.service.ArticleService;
import com.sbs.example.dkJspCommunity.service.MemberService;

public class Container {

	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static MemberService memberService;
	public static MemberDao memberDao;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		articleService = new ArticleService();
		memberService = new MemberService();
	}

}
