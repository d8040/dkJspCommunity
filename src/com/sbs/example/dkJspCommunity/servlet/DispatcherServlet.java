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

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.mysqlutil.MysqlUtil;

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

	String jspPath = doAction(req, resp, (String) doBeforeActions.get("controllerName"),
		(String) doBeforeActions.get("actionMethodName"));

	if (jspPath == null) {
	    resp.getWriter().append("올바른 요청이 아닙니다.");
	    return;
	}

	doAfterAction(req, resp, jspPath);
    }

    private Map<String, Object> doBeforeAction(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	resp.setContentType("text/html; charset=UTF-8");

	String requestUri = req.getRequestURI();
	String[] requestUriBits = requestUri.split("/");

	if (requestUriBits.length < 5) {
	    resp.getWriter().append("올바른 요청이 아닙니다.");
	    return null;
	}

	MysqlUtil.setDBInfo("127.0.0.1", "sbsst", "sbs123414", "dkJspCommunity");

	String controllerTypeName = requestUriBits[2]; // usr
	String controllerName = requestUriBits[3]; // article
	String actionMethodName = requestUriBits[4]; // list

	String actionUrl = "/" + controllerTypeName + "/" + controllerName + "/" + actionMethodName;

	// 데이터 추가 인터셉터 시작
	boolean isLogined = false;
	int loginedMemberId = 0;
	Member loginedMember = null;

	HttpSession session = req.getSession();

	if (session.getAttribute("loginedMemberId") != null) {
	    isLogined = true;
	    loginedMemberId = (int) session.getAttribute("loginedMemberId");
	    loginedMember = Container.memberService.getMemberById(loginedMemberId);
	}

	req.setAttribute("isLogined", isLogined);
	req.setAttribute("loginedMemberId", loginedMemberId);
	req.setAttribute("lgoinedMember", loginedMember);

	// 데이터 추가 인터셉터 끝

	// 로그인 필요 필터링 인터셉터 시작

	List<String> needToLoginActionUrls = new ArrayList<>();

	needToLoginActionUrls.add("/usr/member/doLogout");
	needToLoginActionUrls.add("/usr/article/write");
	needToLoginActionUrls.add("/usr/article/doWrite");
	needToLoginActionUrls.add("/usr/article/modify");
	needToLoginActionUrls.add("/usr/article/doModify");
	needToLoginActionUrls.add("/usr/article/doDelete");

	if (needToLoginActionUrls.contains(actionUrl)) {
	    if ((boolean) req.getAttribute("isLogined") == false) {
		req.setAttribute("alertMsg", "로그인 후 이용해 주세요.");
		req.setAttribute("replace", "../member/login");

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/common/redirect.jsp");
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

	if (disableToLoginActionUrls.contains(actionUrl)) {
	    if ((boolean) req.getAttribute("isLogined") != false) {
		req.setAttribute("alertMsg", "로그아웃 후 이용해주세요.");
		req.setAttribute("historyBack", true);

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/common/redirect.jsp");
		rd.forward(req, resp);
	    }
	}
	// 로그인 필요 필터링 인터셉터 시작

	Map<String, Object> rs = new HashMap<>();
	rs.put("controllerName", controllerName);
	rs.put("actionMethodName", actionMethodName);

	return rs;
    }

    protected abstract String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName,
	    String actionMethodName);

    private void doAfterAction(HttpServletRequest req, HttpServletResponse resp, String jspPath)
	    throws ServletException, IOException {
	MysqlUtil.closeConnection();

	RequestDispatcher rd = req.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
	rd.forward(req, resp);
    }
}
