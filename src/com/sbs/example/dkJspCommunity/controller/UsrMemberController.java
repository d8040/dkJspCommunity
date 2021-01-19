package com.sbs.example.dkJspCommunity.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.service.MemberService;

public class UsrMemberController {

    private MemberService memberService;

    public UsrMemberController() {
	memberService = Container.memberService;
    }

    public String join(HttpServletRequest req, HttpServletResponse resp) {

	return "usr/member/join";
    }

    public String doJoin(HttpServletRequest req, HttpServletResponse resp) {

	String loginId = req.getParameter("loginId");
	String loginPw = req.getParameter("loginPw");
	String name = req.getParameter("name");
	String nickname = req.getParameter("nickname");
	String email = req.getParameter("email");
	String cellphoneNo = req.getParameter("cellphoneNo");

	Member oldMember = memberService.getMemberByLoginId(loginId);

	if (oldMember != null) {
	    req.setAttribute("alertMsg", "동일 아이디가 존재합니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	Map<String, Object> joinArgs = new HashMap<>();
	joinArgs.put("loginId", loginId);
	joinArgs.put("loginPw", loginPw);
	joinArgs.put("name", name);
	joinArgs.put("nickname", nickname);
	joinArgs.put("email", email);
	joinArgs.put("cellphoneNo", cellphoneNo);

	int newMemberId = memberService.join(joinArgs);

	req.setAttribute("alertMsg", nickname + "님 회원가입이 완료되었습니다.");
	req.setAttribute("replaceUrl", String.format("list?boardId=1"));
	return "common/redirect";
    }

    public String showLogin(HttpServletRequest req, HttpServletResponse resp) {
	return "usr/member/login";
    }

    public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
	String loginId = req.getParameter("loginId");
	String loginPw = req.getParameter("loginPw");

	Member member = memberService.getMemberByLoginId(loginId);

	if (member == null) {
	    req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	if (member.getLoginPw().equals(loginPw) == false) {
	    req.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	HttpSession session = req.getSession();
	session.setAttribute("loginedMemberId", member.getId());

	req.setAttribute("alertMsg", member.nickname + "님 로그인을 환영합니다.");
	req.setAttribute("replaceUrl", "../home/main");
	return "common/redirect";
    }

    public String doLogout(HttpServletRequest req, HttpServletResponse resp) {
	HttpSession session = req.getSession();
	session.removeAttribute("loginedMemberId");

	req.setAttribute("alertMsg", "로그아웃 되었습니다.");
	req.setAttribute("replaceUrl", "../home/main");

	return "common/redirect";
    }

}
