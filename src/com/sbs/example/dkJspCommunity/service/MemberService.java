package com.sbs.example.dkJspCommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.App;
import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.MemberDao;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.util.Util;

public class MemberService {

    private MemberDao memberDao;
    private EmailService emailService;

    public MemberService() {
	memberDao = Container.memberDao;
	emailService = Container.emailService;
    }

    public List<Member> getForPrintMembers() {
	return memberDao.getForPrintMembers();
    }

    public int join(Map<String, Object> joinArgs) {
	return memberDao.join(joinArgs);
    }

    public Member getMemberByLoginId(String loginId) {
	return memberDao.getMemberByLoginId(loginId);
    }

    public Member getMemberById(int loginedMemberId) {
	return memberDao.getMemberById(loginedMemberId);
    }

    public Member getMemberByNameAndEmail(String name, String email) {
	return memberDao.getMemberByNameAndEmail(name, email);
    }

    public Member getMemberByLoginIdAndEmail(String loginId) {
	return memberDao.getMemberByLoginIdAndEmail(loginId);
    }

    public Map<String, Object> sendTempLoginPwToEmail(Member actor) {
	// 메일 제목과 내용 만들기
	String siteName = App.getSite();
	String siteLoginUrl = App.getLoginUrl();
	String title = "[" + siteName + "] 임시 패스워드 발송";
	String tempPassword = Util.getTempPassword(6);
	String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
	body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\">로그인 하러가기</a>";

	Map<String, Object> rs = new HashMap<>();
	
	// 이메일 발송
	int sendRs = emailService.send(actor.getEmail(), title, body);
	
	if(sendRs == 1) {
	    rs.put("resultCode", "S-1");
	    rs.put("msg", String.format("임시 비밀번호가 %s(으)로 전송 되었습니다.", actor.getEmail()));
	    
	    // 고객 패스워드를 방금 생성한 임시패스워드로 변경
	    setTempPassword(actor, tempPassword);
	}
	else {
	    rs.put("resultCode", "F-1");
	    rs.put("msg", "이메일 발송에 실패했습니다.");
	}
	return rs;
    }

    private void setTempPassword(Member actor, String tempPassword) {
	Map<String, Object> modifyParam = new HashMap<>();
	modifyParam.put("id", actor.getId());
	modifyParam.put("loginPw", Util.sha256(tempPassword));
	modify(modifyParam);
    }

    private void modify(Map<String, Object> param) {
	memberDao.modify(param);
    }

}
