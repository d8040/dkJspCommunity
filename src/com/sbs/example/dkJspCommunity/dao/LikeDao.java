package com.sbs.example.dkJspCommunity.dao;

import com.sbs.example.dkJspCommunity.mysqlutil.MysqlUtil;
import com.sbs.example.dkJspCommunity.mysqlutil.SecSql;

public class LikeDao {
     public void doLike(int memberId, int articleId, int like, int unlike, String relTypeCode) {
	SecSql sql = new SecSql();
	
	sql.append("INSERT INTO `like`");
	sql.append("(regDate, updateDate, memberId, articleId, `like`, unlike)");
	sql.append("VALUES (NOW(), NOW(), ?, ?, ?, ?)", memberId, articleId, like, unlike);
	sql.append("ON DUPLICATE KEY UPDATE");
	sql.append("updateDAte = NOW()");
	sql.append(", `like` = ?", like);
	sql.append(", unlike = ?", unlike);
	sql.append(", relTypeCode = ?", relTypeCode);

	MysqlUtil.insert(sql);
    }
}
