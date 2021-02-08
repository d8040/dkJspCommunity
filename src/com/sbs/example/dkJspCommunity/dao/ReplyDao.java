package com.sbs.example.dkJspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Reply;
import com.sbs.example.dkJspCommunity.mysqlutil.MysqlUtil;
import com.sbs.example.dkJspCommunity.mysqlutil.SecSql;

public class ReplyDao {

    public List<Reply> getForPrintRepliesByArticleId(int id) {
	List<Reply> replies = new ArrayList<>();

	SecSql sql = new SecSql();
	sql.append("SELECT *");
	sql.append(", M.name AS extra_writer");
	sql.append(", (SELECT COUNT(`like`) FROM `like` AS L WHERE articleId = R.id AND L.relTypeCode = 'reply' AND `like`=1) AS extra_likeCount");
	sql.append(", (SELECT COUNT(unlike) FROM `like` AS L WHERE articleId = R.id AND L.relTypeCode = 'reply' AND `unlike`=1) AS extra_unlikeCount");
	sql.append("FROM reply AS R");
	sql.append("LEFT JOIN `member` AS M");
	sql.append("ON R.memberId = M.id");
	sql.append("WHERE articleId = ?", id);
//	sql.append("ORDER BY R.id DESC;");

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

}
