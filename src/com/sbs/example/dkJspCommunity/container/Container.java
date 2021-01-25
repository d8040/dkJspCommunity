package com.sbs.example.dkJspCommunity.container;

import com.sbs.example.dkJspCommunity.controller.AdmMemberController;
import com.sbs.example.dkJspCommunity.controller.UsrArticleController;
import com.sbs.example.dkJspCommunity.controller.UsrHomeController;
import com.sbs.example.dkJspCommunity.controller.UsrMemberController;
import com.sbs.example.dkJspCommunity.dao.ArticleDao;
import com.sbs.example.dkJspCommunity.dao.MemberDao;
import com.sbs.example.dkJspCommunity.service.ArticleService;
import com.sbs.example.dkJspCommunity.service.EmailService;
import com.sbs.example.dkJspCommunity.service.MemberService;

public class Container {
    public static MemberDao memberDao;
    public static ArticleDao articleDao;

    public static EmailService emailService;
    public static MemberService memberService;
    public static ArticleService articleService;

    public static AdmMemberController admMemberController;
    public static UsrMemberController usrMemberController;
    public static UsrArticleController articleController;
    public static UsrHomeController usrHomeController;

    static {
	memberDao = new MemberDao();
	articleDao = new ArticleDao();

	emailService = new EmailService();
	memberService = new MemberService();
	articleService = new ArticleService();

	admMemberController = new AdmMemberController();
	usrMemberController = new UsrMemberController();
	articleController = new UsrArticleController();
	usrHomeController = new UsrHomeController();
    }

}
