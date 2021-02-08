package com.sbs.example.dkJspCommunity.container;

import com.sbs.example.dkJspCommunity.controller.AdmMemberController;
import com.sbs.example.dkJspCommunity.controller.UsrArticleController;
import com.sbs.example.dkJspCommunity.controller.UsrHomeController;
import com.sbs.example.dkJspCommunity.controller.UsrLikeController;
import com.sbs.example.dkJspCommunity.controller.UsrMemberController;
import com.sbs.example.dkJspCommunity.controller.UsrReplyController;
import com.sbs.example.dkJspCommunity.dao.ArticleDao;
import com.sbs.example.dkJspCommunity.dao.AttrDao;
import com.sbs.example.dkJspCommunity.dao.LikeDao;
import com.sbs.example.dkJspCommunity.dao.MemberDao;
import com.sbs.example.dkJspCommunity.dao.ReplyDao;
import com.sbs.example.dkJspCommunity.service.ArticleService;
import com.sbs.example.dkJspCommunity.service.AttrService;
import com.sbs.example.dkJspCommunity.service.EmailService;
import com.sbs.example.dkJspCommunity.service.LikeService;
import com.sbs.example.dkJspCommunity.service.MemberService;
import com.sbs.example.dkJspCommunity.service.ReplyService;

public class Container {
    public static AttrDao attrDao;
    public static MemberDao memberDao;
    public static ArticleDao articleDao;
    public static LikeDao likeDao;
    public static ReplyDao replyDao;

    public static AttrService attrService;
    public static EmailService emailService;
    public static MemberService memberService;
    public static ArticleService articleService;
    public static LikeService likeService;
    public static ReplyService replyService;

	public static UsrLikeController usrLikeController;
    public static AdmMemberController admMemberController;
    public static UsrMemberController usrMemberController;
    public static UsrArticleController articleController;
    public static UsrHomeController usrHomeController;
    public static UsrReplyController usrReplyController;

    static {
	attrDao = new AttrDao();
	memberDao = new MemberDao();
	articleDao = new ArticleDao();
	likeDao = new LikeDao();
	replyDao = new ReplyDao();

	attrService = new AttrService();
	emailService = new EmailService();
	memberService = new MemberService();
	articleService = new ArticleService();
	likeService = new LikeService();
	replyService = new ReplyService();

	usrLikeController = new UsrLikeController();
	admMemberController = new AdmMemberController();
	usrMemberController = new UsrMemberController();
	articleController = new UsrArticleController();
	usrHomeController = new UsrHomeController();
	usrReplyController = new UsrReplyController();
    }

}
