package com.sbs.example.dkJspCommunity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Reply;
import com.sbs.example.dkJspCommunity.dto.ResultData;
import com.sbs.example.dkJspCommunity.service.ArticleService;
import com.sbs.example.dkJspCommunity.service.LikeService;
import com.sbs.example.dkJspCommunity.service.ReplyService;
import com.sbs.example.util.Util;

public class UsrLikeController extends Controller {

    private LikeService likeService;
    private ArticleService articleService;
    private ReplyService replyService;

    public UsrLikeController() {
	likeService = Container.likeService;
	articleService = Container.articleService;
	replyService = Container.replyService;
    }

    public String doLikeAjax(HttpServletRequest req, HttpServletResponse resp) {

	String relTypeCode = req.getParameter("relTypeCode");

	if (relTypeCode == null) {
	    return msgAndBack(req, "관련데이터코드를 입력해주세요.");
	}

	int relId = Util.getAsInt(req.getParameter("relId"), 0);

	if (relId == 0) {
	    return msgAndBack(req, "관련데이터번호를 입력해주세요.");
	}

	int actorId = (int) req.getAttribute("loginedMemberId");
	boolean isLiked = LikeService.isLikedArticle(relId, actorId, "article");
	boolean isDisliked = LikeService.isDislikedArticle(relId, actorId, "article");

	String resultCode = null;
	
	if (isLiked) {
	    likeService.removePoint(relTypeCode, relId, actorId, 0);
	    resultCode = "F-1";
	} else if (isDisliked) {
	    likeService.modifyPoint(relTypeCode, relId, actorId, 1);
	    resultCode = "S-1";
	} else {
	    likeService.setLikePoint(relTypeCode, relId, actorId, 1);
	    resultCode = "S-1";
	}
	
	Article article = articleService.getForPrintArticleById(relId);

	return json(req, new ResultData(resultCode, "", article));
    }

    public String doDislikeAjax(HttpServletRequest req, HttpServletResponse resp) {
	String relTypeCode = req.getParameter("relTypeCode");

	if (relTypeCode == null) {
	    return msgAndBack(req, "관련데이터코드를 입력해주세요.");
	}

	int relId = Util.getAsInt(req.getParameter("relId"), 0);

	if (relId == 0) {
	    return msgAndBack(req, "관련데이터번호를 입력해주세요.");
	}

	int actorId = (int) req.getAttribute("loginedMemberId");

	boolean isLiked = LikeService.isLikedArticle(relId, actorId, "article");
	boolean isDisliked = LikeService.isDislikedArticle(relId, actorId, "article");

	String resultCode = null;
	
	if (isDisliked) {
	    likeService.removePoint(relTypeCode, relId, actorId, 0);
	    resultCode = "F-1";
	} else if (isLiked) {
	    likeService.modifyPoint(relTypeCode, relId, actorId, -1);
	    resultCode = "S-1";
	} else {
	    likeService.setLikePoint(relTypeCode, relId, actorId, -1);
	    resultCode = "S-1";
	}

	Article article = articleService.getForPrintArticleById(relId);

	return json(req, new ResultData(resultCode, "", article));
    }
    public String doReplyLikeAjax(HttpServletRequest req, HttpServletResponse resp) {

	String relTypeCode = req.getParameter("relTypeCode");

	if (relTypeCode == null) {
	    return msgAndBack(req, "관련데이터코드를 입력해주세요.");
	}

	int relId = Util.getAsInt(req.getParameter("relId"), 0);

	if (relId == 0) {
	    return msgAndBack(req, "관련데이터번호를 입력해주세요.");
	}

	int actorId = (int) req.getAttribute("loginedMemberId");
	boolean isLiked = LikeService.isLikedArticle(relId, actorId, "reply");
	boolean isDisliked = LikeService.isDislikedArticle(relId, actorId, "reply");

	String resultCode = null;
	
	if (isLiked) {
	    likeService.removePoint(relTypeCode, relId, actorId, 0);
	    resultCode = "F-1";
	} else if (isDisliked) {
	    likeService.modifyPoint(relTypeCode, relId, actorId, 1);
	    resultCode = "S-1";
	} else {
	    likeService.setLikePoint(relTypeCode, relId, actorId, 1);
	    resultCode = "S-1";
	}

	Reply reply = replyService.getForPrintReplyById(relId);

	return json(req, new ResultData(resultCode, "", reply));
    }

    public String doReplyDislikeAjax(HttpServletRequest req, HttpServletResponse resp) {
	String relTypeCode = req.getParameter("relTypeCode");

	if (relTypeCode == null) {
	    return msgAndBack(req, "관련데이터코드를 입력해주세요.");
	}

	int relId = Util.getAsInt(req.getParameter("relId"), 0);

	if (relId == 0) {
	    return msgAndBack(req, "관련데이터번호를 입력해주세요.");
	}

	int actorId = (int) req.getAttribute("loginedMemberId");

	boolean isLiked = LikeService.isLikedArticle(relId, actorId, "reply");
	boolean isDisliked = LikeService.isDislikedArticle(relId, actorId, "reply");

	String resultCode = null;
	
	if (isDisliked) {
	    likeService.removePoint(relTypeCode, relId, actorId, 0);
	    resultCode = "F-1";
	} else if (isLiked) {
	    likeService.modifyPoint(relTypeCode, relId, actorId, -1);
	    resultCode = "S-1";
	} else {
	    likeService.setLikePoint(relTypeCode, relId, actorId, -1);
	    resultCode = "S-1";
	}
	Reply reply = replyService.getForPrintReplyById(relId);

	return json(req, new ResultData(resultCode, "", reply));
    }
}
