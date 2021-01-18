package com.sbs.example.dkJspCommunity.service;

import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.MemberDao;
import com.sbs.example.dkJspCommunity.dto.Member;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService(){
		memberDao = Container.memberDao;
	}

	public List<Member> getForPrintMembers() {
		return memberDao.getForPrintMembers();
	}

	public int join(Map<String, Object> joinArgs) {
		return memberDao.join(joinArgs);
	}

}
