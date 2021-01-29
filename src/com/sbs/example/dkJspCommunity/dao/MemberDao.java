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

    public Member getMemberByLoginId(String id) {
	SecSql sql = new SecSql();
	sql.append("SELECT *");
	sql.append(", T.relId AS extra_memberId");
	sql.append(", T.type2Code AS extra_typeCode");
	sql.append(", T.value AS extra_value");
	sql.append("FROM `member` AS M");
	sql.append("LEFT JOIN `attr` AS T");
	sql.append("ON (M.id = T.relId ");
	sql.append("AND T.type2Code = 'isUsingTempPassword')");
	sql.append("WHERE M.loginId=?", id);

	Map<String, Object> map = MysqlUtil.selectRow(sql);

	if (map.isEmpty()) {
	    return null;
	}
	return new Member(map);
    }

    public Member getMemberById(int loginedMemberId) {
	SecSql sql = new SecSql();	
	sql.append("SELECT *");
	sql.append(", T.relId AS extra_memberId");
	sql.append(", T.type2Code AS extra_typeCode");
	sql.append(", T.value AS extra_value");
	sql.append("FROM `member` AS M");
	sql.append("LEFT JOIN `attr` AS T");
	sql.append("ON (M.id = T.relId ");
	sql.append("AND T.type2Code = 'isUsingTempPassword')");
	sql.append("WHERE M.id=?", loginedMemberId);
	
	Map<String, Object> map = MysqlUtil.selectRow(sql);

	if (map.isEmpty()) {
	    return null;
	}
	return new Member(map);
    }

    public Member getMemberByNameAndEmail(String name, String email) {
	SecSql sql = new SecSql();
	sql.append("SELECT *");
	sql.append("FROM `member`");
	sql.append("WHERE name=?", name);
	sql.append("AND email=?", email);
	sql.append("ORDER BY id DESC LIMIT 1");

	Map<String, Object> map = MysqlUtil.selectRow(sql);

	if (map.isEmpty()) {
	    return null;
	}
	return new Member(map);
    }

    public Member getMemberByLoginIdAndEmail(String loginId) {
	SecSql sql = new SecSql();
	sql.append("SELECT *");
	sql.append("FROM `member`");
	sql.append("WHERE loginId=?", loginId);

	Map<String, Object> map = MysqlUtil.selectRow(sql);

	if (map.isEmpty()) {
	    return null;
	}
	return new Member(map);
    }

    public int modify(Map<String, Object> args) {
	SecSql sql = new SecSql();
	sql.append("UPDATE member");
	sql.append("SET updateDate = NOW()");

	boolean needToUpdate = false;

	if (args.get("loginPw") != null) {
	    needToUpdate = true;
	    sql.append(", loginPw = ?", args.get("loginPw"));
	}

	if (args.get("name") != null) {
	    needToUpdate = true;
	    sql.append(", `name` = ?", args.get("name"));
	}

	if (args.get("nickname") != null) {
	    needToUpdate = true;
	    sql.append(", nickname = ?", args.get("nickname"));
	}

	if (args.get("email") != null) {
	    needToUpdate = true;
	    sql.append(", email = ?", args.get("email"));
	}

	if (args.get("cellphoneNo") != null) {
	    needToUpdate = true;
	    sql.append(", cellphoneNo = ?", args.get("cellphoneNo"));
	}

	if (args.get("authLevel") != null) {
	    needToUpdate = true;
	    sql.append(", authLevel = ?", args.get("authLevel"));
	}

	if (needToUpdate == false) {
	    return 0;
	}

	sql.append("WHERE id = ?", args.get("id"));

	return MysqlUtil.update(sql);

    }

}
