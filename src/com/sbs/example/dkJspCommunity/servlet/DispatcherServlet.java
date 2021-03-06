package com.sbs.example.dkJspCommunity.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.dkJspCommunity.App;
import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.mysqlutil.MysqlUtil;
import com.sbs.example.util.Util;

public abstract class DispatcherServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		run(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void run(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> doBeforeActions = doBeforeAction(req, resp);

		if (doBeforeActions == null) {
			return;
		}

		String jspPath = doAction(req, resp, (String) doBeforeActions.get("controllerName"), (String) doBeforeActions.get("actionMethodName"));

		if (jspPath == null) {
			resp.getWriter().append("jsp 정보가 없습니다.");
			return;
		}

		doAfterAction(req, resp, jspPath);
	}

	private Map<String, Object> doBeforeAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String requestUri = req.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		int minBitsCount = 5;

		if (App.isProductMode()) {
			minBitsCount = 4;
		}
		if (requestUriBits.length < minBitsCount) {
			resp.getWriter().append("올바른 요청이 아닙니다.");
			return null;
		}
		if (App.isProductMode()) {
			MysqlUtil.setDBInfo("127.0.0.1", "sbsstLocal", "sbs123414", "dkJspCommunity");
		} else {
			MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "dkJspCommunity");
			MysqlUtil.setDevMode(true);
		}

		int controllerTypeNameIndex = 2;
		int controllerNameIndex = 3;
		int actionMethodNameIndex = 4;

		if (App.isProductMode()) {
			controllerTypeNameIndex = 1;
			controllerNameIndex = 2;
			actionMethodNameIndex = 3;
		}

		String controllerTypeName = requestUriBits[controllerTypeNameIndex];
		String controllerName = requestUriBits[controllerNameIndex];
		String actionMethodName = requestUriBits[actionMethodNameIndex];

		String actionUrl = "/" + controllerTypeName + "/" + controllerName + "/" + actionMethodName;

		// 데이터 추가 인터셉터 시작
		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;
		String isUsingTempPw = null;
		String expireDateOfPw = null;

		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = Container.memberService.getMemberById(loginedMemberId);
			isUsingTempPw = Container.attrService.getValue("member__" + loginedMember.id + "__extra__isUsingTempPassword");
			expireDateOfPw = Container.attrService.getValue("member__" + loginedMember.id + "__extra__expireDateOfPw");
		}

		req.setAttribute("expireDateOfPw", expireDateOfPw);
		req.setAttribute("isUsingTempPw", isUsingTempPw);
		req.setAttribute("isLogined", isLogined);
		req.setAttribute("loginedMemberId", loginedMemberId);
		req.setAttribute("loginedMember", loginedMember);

		String currentUrl = req.getRequestURI();

		if (req.getQueryString() != null) {
			currentUrl += "?" + req.getQueryString();
		}

		String encodedCurrentUrl = Util.getUrlEncoded(currentUrl);
		
		req.setAttribute("currentUrl", currentUrl);
		req.setAttribute("encodedCurrentUrl", encodedCurrentUrl);

		Map<String, Object> param = Util.getParamMap(req);
		String paramJson = Util.getJsonText(param);

		req.setAttribute("paramMap", param);
		req.setAttribute("paramJson", paramJson);
		// 데이터 추가 인터셉터 끝
		
		// 로그인 필요 필터링 인터셉터 시작
		List<String> needToLoginActionUrls = new ArrayList<>();

		needToLoginActionUrls.add("/usr/member/doLogout");
		needToLoginActionUrls.add("/usr/member/memberModify");
		needToLoginActionUrls.add("/usr/member/doMemberModify");
		needToLoginActionUrls.add("/usr/article/write");
		needToLoginActionUrls.add("/usr/article/doWrite");
		needToLoginActionUrls.add("/usr/article/modify");
		needToLoginActionUrls.add("/usr/article/doModify");
		needToLoginActionUrls.add("/usr/article/doDelete");
		needToLoginActionUrls.add("/usr/like/doLike");
		needToLoginActionUrls.add("/usr/like/doLike");
		needToLoginActionUrls.add("/usr/like/doCancelLike");
		needToLoginActionUrls.add("/usr/like/doDislike");
		needToLoginActionUrls.add("/usr/like/doCancelDislike");
		//	needToLoginActionUrls.add("/usr/reply/doReplyWrite");

		if (needToLoginActionUrls.contains(actionUrl)) {
			if ((boolean) req.getAttribute("isLogined") == false) {
				req.setAttribute("alertMsg", "로그인 후 이용해 주세요.");
				req.setAttribute("replaceUrl", "../member/login?afterLoginUrl=" + encodedCurrentUrl);

				RequestDispatcher rd = req.getRequestDispatcher(getJspDirPath() + "/common/redirect.jsp");
				rd.forward(req, resp);
			}
		}
		// 로그인 필요 필터링 인터셉터 끝

		// 로그인 불필요 필터링 인터셉터 시작
		List<String> disableToLoginActionUrls = new ArrayList<>();

		disableToLoginActionUrls.add("/usr/member/login");
		disableToLoginActionUrls.add("/usr/member/doLogin");
		disableToLoginActionUrls.add("/usr/member/join");
		disableToLoginActionUrls.add("/usr/member/doJoin");
		disableToLoginActionUrls.add("/usr/member/findLoginId");
		disableToLoginActionUrls.add("/usr/member/doFindLoginId");
		disableToLoginActionUrls.add("/usr/member/findLoginPw");
		disableToLoginActionUrls.add("/usr/member/doFindLoginPw");

		if (disableToLoginActionUrls.contains(actionUrl)) {
			if ((boolean) req.getAttribute("isLogined") != false) {
				req.setAttribute("alertMsg", "로그아웃 후 이용해주세요.");
				req.setAttribute("historyBack", "../member/login?afterLoginUrl=" + encodedCurrentUrl);

				RequestDispatcher rd = req.getRequestDispatcher(getJspDirPath() + "/common/redirect.jsp");
				rd.forward(req, resp);
			}
		}
		// 로그인 불필요 필터링 인터셉터 끝

		Map<String, Object> rs = new HashMap<>();
		rs.put("controllerName", controllerName);
		rs.put("actionMethodName", actionMethodName);

		return rs;
	}

	protected abstract String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName, String actionMethodName);

	private void doAfterAction(HttpServletRequest req, HttpServletResponse resp, String jspPath) throws ServletException, IOException {
		MysqlUtil.closeConnection();

		RequestDispatcher rd = req.getRequestDispatcher(getJspDirPath() + "/" + jspPath + ".jsp");
		rd.forward(req, resp);
	}
	
	private String getJspDirPath() {
	    return "/WEB-INF/jsp";
	}
}
