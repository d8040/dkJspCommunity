package com.sbs.example.dkJspCommunity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Reply;
import com.sbs.example.dkJspCommunity.service.ArticleService;
import com.sbs.example.dkJspCommunity.service.ReplyService;

public class UsrReplyController {

    private ReplyService replyService;

    public UsrReplyController() {
	replyService = Container.replyService;
    }

    public String doReplyWrite(HttpServletRequest req, HttpServletResponse resp) {
	int articleId = Integer.parseInt(req.getParameter("articleId"));	
	int memberId = (int) req.getAttribute("loginedMemberId");
	String replyBody = req.getParameter("replyBody");
		
	System.out.println(replyBody);

	Map<String, Object> writeArgs = new HashMap<>();
	writeArgs.put("articleId", articleId);
	writeArgs.put("memberId", memberId);
	writeArgs.put("replyBody", replyBody);	

	int newArticleId = replyService.write(writeArgs);
	
	req.setAttribute("alertMsg", newArticleId + "번 댓글이 생성되었습니다.");
	req.setAttribute("replaceUrl", String.format("../article/detail?id=%d", articleId));
	return "common/redirect";
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
	
	req.setAttribute("alertMsg", replyId + "번 댓글이 삭제되었습니다");
	req.setAttribute("historyBack", true);
	return "common/redirect";
    }
}
