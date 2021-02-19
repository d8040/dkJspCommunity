package com.sbs.example.dkJspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.dto.Like;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.dto.Reply;
import com.sbs.example.dkJspCommunity.mysqlutil.MysqlUtil;
import com.sbs.example.dkJspCommunity.mysqlutil.SecSql;

public class LikeDao {

    public int removePoint(String relTypeCode, int relId, int memberId) {
	SecSql sql = new SecSql();
	sql.append("DELETE FROM `like`");
	sql.append("WHERE 1");
	sql.append("AND relTypeCode = ?", relTypeCode);
	sql.append("AND relId = ? ", relId);
	sql.append("AND memberId = ?", memberId);

	return MysqlUtil.delete(sql);
    }

    public int setPoint(String relTypeCode, int relId, int memberId, int point) {
	SecSql sql = new SecSql();
	sql.append("INSERT INTO `like`");
	sql.append("SET regDate = NOW()");
	sql.append(", updateDate = NOW()");
	sql.append(", relTypeCode = ?", relTypeCode);
	sql.append(", relId = ? ", relId);
	sql.append(", memberId = ?", memberId);
	sql.append(", point = ?", point);

	System.out.println(sql.getRawSql());
	return MysqlUtil.insert(sql);
    }

    public int getPoint(String relTypeCode, int relId, int memberId) {
	SecSql sql = new SecSql();
	sql.append("SELECT IFNULL(SUM(L.point), 0) AS `point`");
	sql.append("FROM `like` AS L");
	sql.append("WHERE 1");
	sql.append("AND L.relTypeCode = ?", relTypeCode);
	sql.append("AND L.relId = ?", relId);
	sql.append("AND L.memberId = ?", memberId);

	return MysqlUtil.selectRowIntValue(sql);
    }

    public void modifyPoint(String relTypeCode, int relId, int actorId, int point) {
	SecSql sql = new SecSql();
	sql.append("UPDATE `like`");
	sql.append("SET `point` = ?", point);
	sql.append("WHERE relTypeCode = ?", relTypeCode);
	sql.append("AND relId = ?", relId);
	sql.append("AND memberId = ?", actorId);

	System.out.println(sql.getRawSql());
	MysqlUtil.update(sql);
    }

    public boolean isLikedArticle(int id, int memberId, String relTypeCode) {
	SecSql sql = new SecSql();

	sql.append("SELECT * FROM `like`");
	sql.append("WHERE `relTypeCode` = ?", relTypeCode);
	sql.append("AND `point` = 1");
	sql.append("AND `relId` = ?", id);
	if (memberId != 0) {
	    sql.append("AND `memberId` = ?", memberId);
	}

	Map<String, Object> recommendMap = MysqlUtil.selectRow(sql);

	if (!recommendMap.isEmpty()) {
	    return true;
	}

	return false;
    }

    public boolean isDislikedArticle(int id, int memberId, String relTypeCode) {
	SecSql sql = new SecSql();

	sql.append("SELECT * FROM `like`");
	sql.append("WHERE `relTypeCode` = ?", relTypeCode);
	sql.append("AND `point` = -1");
	sql.append("AND `relId` = ?", id);
	if (memberId != 0) {
	    sql.append("AND `memberId` = ?", memberId);
	}

	Map<String, Object> recommendMap = MysqlUtil.selectRow(sql);

	if (!recommendMap.isEmpty()) {
	    return true;
	}

	return false;
    }

    public List<Like> getForPintLikesByArticleId(int id, int memberId) {
	List<Like> likes = new ArrayList<>();

	SecSql sql = new SecSql();
	sql.append("SELECT * ");
	sql.append("FROM `like` AS L");
	sql.append("LEFT JOIN `reply` AS R");
	sql.append("ON L.relTypeCode = 'reply'");
	sql.append("AND L.relId = R.id");
	sql.append("AND L.memberId = ?", memberId);
	sql.append("WHERE R.articleId = ?", id);

	List<Map<String, Object>> likeMapList = MysqlUtil.selectRows(sql);

	for (Map<String, Object> replyMap : likeMapList) {
	    likes.add(new Like(replyMap));
	}

	return likes;
    }
}
