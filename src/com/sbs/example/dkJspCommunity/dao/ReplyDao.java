package com.sbs.example.dkJspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.dto.Reply;
import com.sbs.example.dkJspCommunity.mysqlutil.MysqlUtil;
import com.sbs.example.dkJspCommunity.mysqlutil.SecSql;

public class ReplyDao {

    public List<Reply> getForPrintRepliesByArticleId(int id) {
	List<Reply> replies = new ArrayList<>();

	SecSql sql = new SecSql();
	sql.append("SELECT *");
	sql.append(", M.name AS extra__writer");
	sql.append(", IFNULL(SUM(L.point), 0) AS extra__likePoint");
	sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra__likeOnlyPoint");
	sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra__dislikeOnlyPoint");
	sql.append("FROM reply AS R");
	sql.append("INNER JOIN `member` AS M");
	sql.append("ON R.memberId = M.id");
	sql.append("LEFT JOIN `like` AS L");
	sql.append("ON L.relTypeCode = 'reply'");
	sql.append("AND R.id = L.relId");
	sql.append("WHERE articleId = ?", id);
	sql.append("GROUP BY R.id");
	sql.append("ORDER BY R.id;");

	List<Map<String, Object>> replyMapList = MysqlUtil.selectRows(sql);

	for (Map<String, Object> replyMap : replyMapList) {
	    replies.add(new Reply(replyMap));
	}	

	return replies;
    }

    public int write(Map<String, Object> args) {
	SecSql sql = new SecSql();

	sql.append("INSERT INTO reply");
	sql.append("SET regDate = NOW()");
	sql.append(", updateDate = NOW()");
	sql.append(", articleId = ?", args.get("articleId"));
	sql.append(", memberId = ?", args.get("memberId"));
	if (args.get("parentReplyId") != null) {
	    sql.append(", parentReplyId = ?", args.get("parentReplyId"));
	}
	sql.append(", body = ?", args.get("replyBody"));

	return MysqlUtil.insert(sql);
    }

    public void delete(Map<String, Object> args) {
	SecSql sql = new SecSql();

	sql.append("DELETE FROM reply");
	sql.append("WHERE id = ?", args.get("id"));
	sql.append("AND memberId = ?", args.get("memberId"));

	MysqlUtil.delete(sql);
    }

    public Reply getForPrintRepliyByArticleId(int id) {
	SecSql sql = new SecSql();
	sql.append("SELECT R.*");
	sql.append(", M.name AS extra__writer");
	sql.append(", IFNULL(SUM(L.point), 0) AS extra__likePoint");
	sql.append(", IFNULL(SUM(IF(L.point > 0, L.point, 0)), 0) AS extra__likeOnlyPoint");
	sql.append(", IFNULL(SUM(IF(L.point < 0, L.point * -1, 0)), 0) extra__dislikeOnlyPoint");
	sql.append("FROM reply AS R");
	sql.append("INNER JOIN `member` AS M");
	sql.append("ON R.memberId = M.id");
	sql.append("LEFT JOIN `like` AS L");
	sql.append("ON L.relTypeCode = 'reply'");
	sql.append("AND R.id = L.relId");
	sql.append("WHERE R.id = ?", id);
	sql.append("GROUP BY R.id");

	Map<String, Object> map = MysqlUtil.selectRow(sql);

	if (map.isEmpty()) {
	    return null;
	}

	return new Reply(map);
    }

    public void doModify(Map<String, Object> modifyArgs) {
	SecSql sql = new SecSql();
	sql.append("UPDATE reply");
	sql.append("SET updateDate = NOW()");

	boolean needToUpdate = false;

	if (modifyArgs.get("replyBody") != null) {
	    needToUpdate = true;
	    sql.append(", body = ?", modifyArgs.get("replyBody"));
	}
	if (modifyArgs.get("reReplyBody") != null) {
	    needToUpdate = true;
	    sql.append(", body = ?", modifyArgs.get("reReplyBody"));
	}

	if (needToUpdate == false) {
	}

	sql.append("where id = ?", modifyArgs.get("replyId"));
	
	MysqlUtil.update(sql);
    }

}
