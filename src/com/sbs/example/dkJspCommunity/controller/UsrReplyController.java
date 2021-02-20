package com.sbs.example.dkJspCommunity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.dto.Reply;
import com.sbs.example.dkJspCommunity.service.ReplyService;
import com.sbs.example.util.Util;

public class UsrReplyController extends Controller {

    private ReplyService replyService;

    public UsrReplyController() {
	replyService = Container.replyService;
    }

    public String doReplyWrite(HttpServletRequest req, HttpServletResponse resp) {
	int articleId = Integer.parseInt(req.getParameter("articleId"));	
	int memberId = (int) req.getAttribute("loginedMemberId");
	String replyBody = req.getParameter("replyBody");
	String reReplyBody = req.getParameter("reReplyBody");
	String parentReplyId = req.getParameter("parentReplyId");
	String redirectUrl = req.getParameter("redirectUrl");
	String parentReplyMmeber = req.getParameter("parentReplyMmeber");
	
	
		
	System.out.println("replyBody::"+replyBody);
	System.out.println("reReplyBody::"+reReplyBody);	
	System.out.println("parentReplyId::"+parentReplyId);
	System.out.println("parentReplyId::"+parentReplyMmeber);

//	if (Util.isEmpty(replyBody) && Util.isEmpty(reReplyBody)) {
//	    return msgAndBack(req, "내용을 입력해 주세요");
//	}
	
	Map<String, Object> writeArgs = new HashMap<>();
	writeArgs.put("articleId", articleId);
	writeArgs.put("memberId", memberId);
	if (replyBody != null) {
	writeArgs.put("replyBody", replyBody);	
	}
	else {
	    writeArgs.put("replyBody", parentReplyMmeber+reReplyBody);	
	}
	if (parentReplyId != null) {
	    writeArgs.put("parentReplyId", parentReplyId);
	}
	
	int newArticleId = replyService.write(writeArgs);
	
	redirectUrl = redirectUrl.replace("[NEW_REPLY_ID]", newArticleId + "");
		
	return msgAndReplace(req, "댓글이 추가되었습니다.", redirectUrl);
    }

    public String doReplyDelete(HttpServletRequest req, HttpServletResponse resp) {
	int articleId = Integer.parseInt(req.getParameter("articleId"));	
	int replyId = Integer.parseInt(req.getParameter("id"));	
	int memberId = (int) req.getAttribute("loginedMemberId");
	
	System.out.println(replyId);
	System.out.println(memberId);
	
	Map<String, Object> delArgs = new HashMap<>();
	delArgs.put("memberId", memberId);
	delArgs.put("id", replyId);
	
	replyService.delete(delArgs);
	
	return msgAndReplace(req, "댓글이 삭제되었습니다.", String.format("../article/detail?id=%d", articleId));
    }

    public String doReplyModify(HttpServletRequest req, HttpServletResponse resp) {
	int articleId = Integer.parseInt(req.getParameter("articleId"));
	int replyId = Integer.parseInt(req.getParameter("replyId"));
	String replyBody = req.getParameter("replyBody");
	String reReplyBody = req.getParameter("reReplyBody");
	String redirectUrl = req.getParameter("redirectUrl");
	

	System.out.println("replyIdmodify::"+replyId);
	System.out.println("replyBody::"+replyBody);
	System.out.println("reReplyBody::"+reReplyBody);	
	
	Map<String, Object> modifyArgs = new HashMap<>();
	modifyArgs.put("replyId", replyId);
	if (replyBody != null) {
	    modifyArgs.put("replyBody", replyBody);	
	}
	else {
	    modifyArgs.put("replyBody", reReplyBody);	
	}
	
	replyService.doModify(modifyArgs);
	
	redirectUrl = redirectUrl.replace("[NEW_REPLY_ID]", replyId + "");
	
	return msgAndReplace(req, "댓글이 수정되었습니다.", redirectUrl);
    }
}
