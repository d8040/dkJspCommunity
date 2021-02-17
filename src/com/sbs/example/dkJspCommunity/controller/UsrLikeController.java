package com.sbs.example.dkJspCommunity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.service.LikeService;
import com.sbs.example.util.Util;

public class UsrLikeController extends Controller {

	private LikeService likeService;

	public UsrLikeController() {
		likeService = Container.likeService;
	}

	public String doLike(HttpServletRequest req, HttpServletResponse resp) {
		String relTypeCode = req.getParameter("relTypeCode");
		System.out.println(relTypeCode);
		if (relTypeCode == null) {
			return msgAndBack(req, "관련데이터코드를 입력해주세요.");
		}

		int relId = Util.getAsInt(req.getParameter("relId"), 0);

		if (relId == 0) {
			return msgAndBack(req, "관련데이터번호를 입력해주세요.");
		}

		int actorId = (int) req.getAttribute("loginedMemberId");

		likeService.setLikePoint(relTypeCode, relId, actorId, 1);

		return msgAndReplace(req, "`좋아요` 처리되었습니다.", req.getParameter("redirectUrl"));
	}

	public String doCancelLike(HttpServletRequest req, HttpServletResponse resp) {
		String relTypeCode = req.getParameter("relTypeCode");

		if (relTypeCode == null) {
			return msgAndBack(req, "관련데이터코드를 입력해주세요.");
		}

		int relId = Util.getAsInt(req.getParameter("relId"), 0);

		if (relId == 0) {
			return msgAndBack(req, "관련데이터번호를 입력해주세요.");
		}

		int actorId = (int) req.getAttribute("loginedMemberId");

		likeService.setLikePoint(relTypeCode, relId, actorId, 0);

		return msgAndReplace(req, "`좋아요`가 취소 처리되었습니다.", req.getParameter("redirectUrl"));
	}

	public String doDislike(HttpServletRequest req, HttpServletResponse resp) {
		String relTypeCode = req.getParameter("relTypeCode");

		if (relTypeCode == null) {
			return msgAndBack(req, "관련데이터코드를 입력해주세요.");
		}

		int relId = Util.getAsInt(req.getParameter("relId"), 0);

		if (relId == 0) {
			return msgAndBack(req, "관련데이터번호를 입력해주세요.");
		}

		int actorId = (int) req.getAttribute("loginedMemberId");

		likeService.setLikePoint(relTypeCode, relId, actorId, -1);

		return msgAndReplace(req, "`싫어요` 처리되었습니다.", req.getParameter("redirectUrl"));
	}

	public String doCancelDislike(HttpServletRequest req, HttpServletResponse resp) {
		String relTypeCode = req.getParameter("relTypeCode");

		if (relTypeCode == null) {
			return msgAndBack(req, "관련데이터코드를 입력해주세요.");
		}

		int relId = Util.getAsInt(req.getParameter("relId"), 0);

		if (relId == 0) {
			return msgAndBack(req, "관련데이터번호를 입력해주세요.");
		}

		int actorId = (int) req.getAttribute("loginedMemberId");

		likeService.setLikePoint(relTypeCode, relId, actorId, 0);

		return msgAndReplace(req, "`싫어요`가 취소 처리되었습니다.", req.getParameter("redirectUrl"));
	}

	public String doLikeAjax(HttpServletRequest req, HttpServletResponse resp) {
		
		Map<String, Object> rs = new HashMap<>();
		
		String relTypeCode = req.getParameter("relTypeCode");
		
		if (relTypeCode == null) {
			return msgAndBack(req, "관련데이터코드를 입력해주세요.");
		}

		int relId = Util.getAsInt(req.getParameter("relId"), 0);
		
		if (relId == 0) {
			return msgAndBack(req, "관련데이터번호를 입력해주세요.");
		}

		int actorId = (int) req.getAttribute("loginedMemberId");

		return likeService.setLikePoint(relTypeCode, relId, actorId, 1);
	}

	public String doCancelLikeAjax(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	public String doDislikeAjax(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	public String doCancelDislikeAjax(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}
}
