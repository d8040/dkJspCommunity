package com.sbs.example.dkJspCommunity.container;

import com.sbs.example.dkJspCommunity.controller.AdmMemberController;
import com.sbs.example.dkJspCommunity.controller.UsrArticleController;
import com.sbs.example.dkJspCommunity.controller.UsrMemberController;
import com.sbs.example.dkJspCommunity.dao.ArticleDao;
import com.sbs.example.dkJspCommunity.dao.MemberDao;
import com.sbs.example.dkJspCommunity.service.ArticleService;
import com.sbs.example.dkJspCommunity.service.MemberService;

public class Container {

	public static ArticleService articleService;
	public static ArticleDao articleDao;
	public static MemberService memberService;
	public static MemberDao memberDao;
	public static AdmMemberController admMemberController;
	public static UsrMemberController usrMemberController;
	public static UsrArticleController articleController;
	
	static {
		memberDao = new MemberDao();
		articleDao = new ArticleDao();
		articleService = new ArticleService();
		memberService = new MemberService();
		admMemberController = new AdmMemberController();
		usrMemberController = new UsrMemberController();
		articleController = new UsrArticleController();
	}

}
