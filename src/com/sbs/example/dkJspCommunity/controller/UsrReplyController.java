package com.sbs.example.dkJspCommunity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.service.ReplyService;

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
		
	System.out.println("replyBody::"+replyBody);
	System.out.println("reReplyBody::"+reReplyBody);	
	System.out.println("parentReplyId::"+parentReplyId);

	Map<String, Object> writeArgs = new HashMap<>();
	writeArgs.put("articleId", articleId);
	writeArgs.put("memberId", memberId);
	if (replyBody != null) {
	writeArgs.put("replyBody", replyBody);	
	}
	else {
	    writeArgs.put("replyBody", reReplyBody);	
	}
	if (parentReplyId != null) {
	    writeArgs.put("parentReplyId", parentReplyId);
	}
	
	int newArticleId = replyService.write(writeArgs);
	
	return msgAndReplace(req, newArticleId + "번 게시물이 생성되었습니다.", String.format("../article/detail?id=%d", articleId));
    }

    public String doReplyDelete(HttpServletRequest req, HttpServletResponse resp) {
	int replyId = Integer.parseInt(req.getParameter("id"));	
	int memberId = (int) req.getAttribute("loginedMemberId");
	
	System.out.println(replyId);
	System.out.println(memberId);
	
	Map<String, Object> delArgs = new HashMap<>();
	delArgs.put("memberId", memberId);
	delArgs.put("id", replyId);
	
	replyService.delete(delArgs);
	
	return msgAndBack(req, replyId + "번 댓글이 삭제되었습니다");
    }
}
