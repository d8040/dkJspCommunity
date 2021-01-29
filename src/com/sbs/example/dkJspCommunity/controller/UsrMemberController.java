package com.sbs.example.dkJspCommunity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.dto.ResultData;
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
	String loginPw = req.getParameter("loginPwReal");
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
	req.setAttribute("replaceUrl", String.format("../home/main"));
	return "common/redirect";
    }

    public String showLogin(HttpServletRequest req, HttpServletResponse resp) {

	return "usr/member/login";
    }

    public String doLogin(HttpServletRequest req, HttpServletResponse resp) {

	String loginId = req.getParameter("loginId");
	String loginPw = req.getParameter("loginPwReal");

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

	req.setAttribute("alertMsg", member.getNickname() + "님 로그인을 환영합니다.");
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

    public String getLoginIdDup(HttpServletRequest req, HttpServletResponse resp) {
	String loginId = req.getParameter("loginId");

	Member member = memberService.getMemberByLoginId(loginId);

	String resultCode = null;
	String msg = null;

	if (member != null) {
	    resultCode = "F-1";
	    msg = String.format("이미 사용중인 아이디 입니다.");
	} else {
	    resultCode = "S-1";
	    msg = String.format("사용 가능한 아이디 입니다.");
	}

	req.setAttribute("data", new ResultData(resultCode, msg, "loginId", loginId));
	return "common/json";
    }

    public String showFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
	return "usr/member/findLoginId";
    }

    public String doFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
	String name = req.getParameter("name");
	String email = req.getParameter("email");

	Member member = memberService.getMemberByNameAndEmail(name, email);

	if (member == null) {
	    req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	req.setAttribute("alertMsg", String.format("로그인 아이디는 %s 입니다.", member.getLoginId()));
	req.setAttribute("replaceUrl", "../member/login");
	return "common/redirect";
    }

    public String showFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
	return "usr/member/findLoginPw";
    }

    public String doFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
	String loginId = req.getParameter("loginId");
	String email = req.getParameter("email");

	Member member = memberService.getMemberByLoginIdAndEmail(loginId);

	if (member == null) {
	    req.setAttribute("alertMsg", "일치하는 회원이 존재하지 않습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	if (member.getEmail().equals(email) == false) {
	    req.setAttribute("alertMsg", "이메일주소가 일치하지 않습니다.");
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}

	ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

	if (sendTempLoginPwToEmailRs.isFail()) {
	    req.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
	    req.setAttribute("historyBack", true);
	    return "common/redirect";
	}
	Container.attrService.setValue("member__"+member.id+"__extra__isUsingTempPassword", "1", null);

	req.setAttribute("alertMsg", sendTempLoginPwToEmailRs.getMsg());
	req.setAttribute("replaceUrl", "../member/login");
	return "common/redirect";
    }

    public String showMemberModify(HttpServletRequest req, HttpServletResponse resp) {

	return "usr/member/memberModify";
    }

    public String doMemberModify(HttpServletRequest req, HttpServletResponse resp) {
	int loginedMemberId = (int) req.getAttribute("loginedMemberId");

	String loginPw = (String) req.getParameter("loginPwReal");

	if (loginPw != null && loginPw.length() == 0) {
	    loginPw = null;
	}

	String nickname = req.getParameter("nickname");
	String email = req.getParameter("email");
	String cellphoneNo = req.getParameter("cellphoneNo");

	Map<String, Object> modifyArgs = new HashMap<>();
	modifyArgs.put("loginPw", loginPw);
	modifyArgs.put("nickname", nickname);
	modifyArgs.put("email", email);
	modifyArgs.put("cellphoneNo", cellphoneNo);
	modifyArgs.put("id", loginedMemberId);

	memberService.modify(modifyArgs);

	HttpSession session = req.getSession();

	if (loginPw != null) {
	    Container.attrService.setValue("member__"+loginedMemberId+"__extra__isUsingTempPassword", "0" , null);
	}	

	req.setAttribute("alertMsg", nickname + "님 회원정보가 수정되었습니다.");
	req.setAttribute("replaceUrl", String.format("memberModify"));
	return "common/redirect";
    }
}
