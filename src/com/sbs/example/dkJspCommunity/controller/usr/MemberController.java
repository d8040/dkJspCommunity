package com.sbs.example.dkJspCommunity.controller.usr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.service.MemberService;

public class MemberController {

	private MemberService memberService;
	
	public MemberController() {
		memberService = Container.memberService;
	}

	public String showList(HttpServletRequest req, HttpServletResponse resp) {
		List<Member> members = memberService.getForPrintMembers();
		
		req.setAttribute("members", members);
		
		return "/adm/member/list";
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
		
		
		Map<String, Object> joinArgs = new HashMap<>();
		joinArgs.put("loginId", loginId);
		joinArgs.put("loginPw", loginPw);
		joinArgs.put("name", name);
		joinArgs.put("nickname", nickname);
		joinArgs.put("email", email);
		joinArgs.put("cellphoneNo", cellphoneNo);
				
		int newMemberId = memberService.join(joinArgs);
		
		req.setAttribute("alertMsg", name + "님 회원가입이 완료되었습니다.");
		req.setAttribute("replaceUrl", String.format("list?boardId=1"));
		return "common/redirect";
	}

}
