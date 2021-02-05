package com.sbs.example.dkJspCommunity.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.dto.ResultData;
import com.sbs.example.dkJspCommunity.service.MemberService;
import com.sbs.example.util.Util;

public class UsrMemberController extends Controller{

    private MemberService memberService;

    public UsrMemberController() {
	memberService = Container.memberService;
    }

    public String join(HttpServletRequest req, HttpServletResponse resp) {

	return "usr/member/join";
    }

    public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
	String loginId = req.getParameter("loginId");

	if (Util.isEmpty(loginId)) {
		return msgAndBack(req, "아이디을 입력해주세요.");
	}

	String loginPw = req.getParameter("loginPwReal");
	
	if (Util.isEmpty(loginId)) {
		return msgAndBack(req, "비밀번호를 입력해주세요.");
	}
	
	String name = req.getParameter("name");

	if (Util.isEmpty(loginId)) {
		return msgAndBack(req, "사용자이름을 입력해주세요.");
	}
	
	String nickname = req.getParameter("nickname");

	if (Util.isEmpty(loginId)) {
		return msgAndBack(req, "닉네임을 입력해주세요.");
	}
	
	String email = req.getParameter("email");

	if (Util.isEmpty(loginId)) {
		return msgAndBack(req, "이메일을 입력해주세요.");
	}
	
	String cellphoneNo = req.getParameter("cellphoneNo");

	if (Util.isEmpty(loginId)) {
		return msgAndBack(req, "휴대전화 번호를 입력해주세요.");
	}
	

	Member oldMember = memberService.getMemberByLoginId(loginId);

	if (oldMember != null) {
	    return msgAndBack(req, "동일 아이디가 존재합니다.");
	}

	Map<String, Object> joinArgs = new HashMap<>();
	joinArgs.put("loginId", loginId);
	joinArgs.put("loginPw", loginPw);
	joinArgs.put("name", name);
	joinArgs.put("nickname", nickname);
	joinArgs.put("email", email);
	joinArgs.put("cellphoneNo", cellphoneNo);

	int newMemberId = memberService.join(joinArgs);

	Member member = memberService.getMemberByLoginIdAndEmail(loginId);

	ResultData sendWelcomeEmail = memberService.sendWelcomeEmail(member);

	if (sendWelcomeEmail.isFail()) {
	    return msgAndBack(req, sendWelcomeEmail.getMsg());
	}

	//6개월 뒤 날짜 계산
	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //날짜 포멧	
	Date time = new Date(); //현재 날짜
	Calendar cal = Calendar.getInstance(); //날짜 계산을 위해 Calendar 추상클래스 선언 getInstance()메소드 사용	
	cal.setTime(time);
	cal.add(Calendar.MONTH, 3); //3개월 더하기
	String date = simpleDate.format(cal.getTime());
	System.out.println(date);
	//6개월 뒤 날짜 계산 끝 
	System.out.println(member.id);
	Container.attrService.setValue("member__" + member.id + "__extra__expireDateOfPw", "1", date);

	return msgAndReplace(req, nickname + "님 회원가입이 완료되었습니다.", "../home/main");
    }

    public String showLogin(HttpServletRequest req, HttpServletResponse resp) {

	return "usr/member/login";
    }

    public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
	String loginId = req.getParameter("loginId");
	String loginPw = req.getParameter("loginPwReal");

	Member member = memberService.getMemberByLoginId(loginId);

	if (member == null) {
	    return msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
	}

	if (member.getLoginPw().equals(loginPw) == false) {
	    return msgAndBack(req, "비밀번호가 일치하지 않습니다.");
	}

	HttpSession session = req.getSession();
	session.setAttribute("loginedMemberId", member.getId());
	
	return msgAndReplace(req,  member.getNickname() + "님 로그인을 환영합니다.", "../home/main");
    }

    public String doLogout(HttpServletRequest req, HttpServletResponse resp) {

	HttpSession session = req.getSession();
	session.removeAttribute("loginedMemberId");

	return msgAndReplace(req, "로그아웃 되었습니다.",  "../home/main");
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
	
	return json(req, new ResultData(resultCode, msg, "loginId", loginId));
    }

    public String showFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
	return "usr/member/findLoginId";
    }

    public String doFindLoginId(HttpServletRequest req, HttpServletResponse resp) {
	String name = req.getParameter("name");
	String email = req.getParameter("email");

	Member member = memberService.getMemberByNameAndEmail(name, email);

	if (member == null) {
	    return msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
	}

	return msgAndReplace(req, String.format("로그인 아이디는 %s 입니다.", member.getLoginId()), "../member/login");
    }

    public String showFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
	return "usr/member/findLoginPw";
    }

    public String doFindLoginPw(HttpServletRequest req, HttpServletResponse resp) {
	String loginId = req.getParameter("loginId");
	String email = req.getParameter("email");

	Member member = memberService.getMemberByLoginIdAndEmail(loginId);

	if (member == null) {
	    return msgAndBack(req, "일치하는 회원이 존재하지 않습니다.");
	}

	if (member.getEmail().equals(email) == false) {
	    return msgAndBack(req, "이메일주소가 일치하지 않습니다.");
	}

	ResultData sendTempLoginPwToEmailRs = memberService.sendTempLoginPwToEmail(member);

	if (sendTempLoginPwToEmailRs.isFail()) {
	    return msgAndBack(req, sendTempLoginPwToEmailRs.getMsg());
	}
	System.out.println(member.id);
	Container.attrService.setValue("member__" + member.id + "__extra__isUsingTempPassword", "1", null);

	return msgAndReplace(req, sendTempLoginPwToEmailRs.getMsg(), "../member/login");
    }

    public String showMemberModify(HttpServletRequest req, HttpServletResponse resp) {

	return "usr/member/memberModify";
    }

    public String doMemberModify(HttpServletRequest req, HttpServletResponse resp) {
	int loginedMemberId = (int) req.getAttribute("loginedMemberId");

	String loginPw = req.getParameter("loginPwReal");

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

	if (loginPw != null) {
	    Container.attrService.setValue("member__" + loginedMemberId + "__extra__isUsingTempPassword", "0", null);	    
	}

	//6개월 뒤 날짜 계산
	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //날짜 포멧	
	Date time = new Date(); //현재 날짜
	Calendar cal = Calendar.getInstance(); //날짜 계산을 위해 Calendar 추상클래스 선언 getInstance()메소드 사용	
	cal.setTime(time);
	cal.add(Calendar.MONTH, 6); //6개월 더하기
	String date = simpleDate.format(cal.getTime());
	System.out.println(date);
	//6개월 뒤 날짜 계산 끝 
	
	if (loginPw != null) {
	    Container.attrService.setValue("member__" + loginedMemberId + "__extra__expireDateOfPw", "1", date);
	}
	
	return msgAndReplace(req, nickname + "님 회원정보가 수정되었습니다.", String.format("memberModify"));
    }
}
