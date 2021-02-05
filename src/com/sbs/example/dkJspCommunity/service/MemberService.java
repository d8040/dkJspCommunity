package com.sbs.example.dkJspCommunity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.App;
import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.MemberDao;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.dto.ResultData;
import com.sbs.example.util.Util;

public class MemberService {

    private MemberDao memberDao;
    private EmailService emailService;
    private AttrService attrService;

    public MemberService() {
	memberDao = Container.memberDao;
	emailService = Container.emailService;
	attrService = Container.attrService;
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

    public ResultData sendTempLoginPwToEmail(Member actor) {
	// 메일 제목과 내용 만들기
	String siteName = App.getSiteName();
	String siteLoginUrl = App.getLoginUrl();
	String title = "[" + siteName + "] 임시 패스워드 발송";
	String tempPassword = Util.getTempPassword(6);
	String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
	body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\">로그인 하러가기</a>";

	Map<String, Object> rs = new HashMap<>();

	// 이메일 발송
	int sendRs = emailService.send(actor.getEmail(), title, body);

	if (sendRs != 1) {
	    return new ResultData("F-1", "이메일 발송에 실패하였습니다.");
	}
	setTempPassword(actor, tempPassword);

	int memberIdByUsingTempPassword = attrService.setValue("member__" + actor.getId() + "__extra__isUsingTempPassword", "1", null);

	String resultMsg = String.format("고객님의 새 임시 패스워드가 %s (으)로 발송되었습니다.", actor.getEmail());
	return new ResultData("S-1", resultMsg, "email", actor.getEmail());
    }

    private void setTempPassword(Member actor, String tempPassword) {
	Map<String, Object> modifyParam = new HashMap<>();
	modifyParam.put("id", actor.getId());
	modifyParam.put("loginPw", Util.sha256(tempPassword));
	modify(modifyParam);
    }

    public void modify(Map<String, Object> param) {
	memberDao.modify(param);
    }

    public ResultData sendWelcomeEmail(Member member) {
	// 메일 제목과 내용 만들기
	String siteName = App.getSiteName();
	String siteLoginUrl = App.getLoginUrl();
	String title = "[" + siteName + "] 회원가입 완료";
	String tempPassword = Util.getTempPassword(6);
	String body = "<h1>환영합니다.</h1>";
	body += "<a href=\"" + siteLoginUrl + "\" target=\"_blank\">로그인 하러가기</a>";

	Map<String, Object> rs = new HashMap<>();

	// 이메일 발송
	int sendRs = emailService.send(member.getEmail(), title, body);

	if (sendRs != 1) {
	    return new ResultData("F-1", "이메일 발송에 실패하였습니다.");
	}
	setTempPassword(member, tempPassword);

	String resultMsg = String.format("고객님의 새 임시 패스워드가 %s (으)로 발송되었습니다.", member.getEmail());
	return new ResultData("S-1", resultMsg, "email", member.getEmail());
    }

}
