package com.sbs.example.dkJspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.mysqlutil.MysqlUtil;
import com.sbs.example.dkJspCommunity.mysqlutil.SecSql;

public class MemberDao {

	public List<Member> getForPrintMembers() {
		List<Member> members = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM member");
		sql.append("ORDER BY id DESC");

		List<Map<String, Object>> memberMapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> memberMap : memberMapList) {
			members.add(new Member(memberMap));
		}

		return members;
	}

	public int join(Map<String, Object> joinArgs) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO member");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", joinArgs.get("loginId"));
		sql.append(", loginPw = ?", joinArgs.get("loginPw"));
		sql.append(", name = ?", joinArgs.get("name"));
		sql.append(", nickname = ?", joinArgs.get("nickname"));
		sql.append(", email = ?", joinArgs.get("email"));
		sql.append(", cellphoneNo = ?", joinArgs.get("cellphoneNo"));

		return MysqlUtil.insert(sql);
	}

}
