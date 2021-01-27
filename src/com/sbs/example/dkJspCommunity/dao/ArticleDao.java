package com.sbs.example.dkJspCommunity.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Board;
import com.sbs.example.dkJspCommunity.mysqlutil.MysqlUtil;
import com.sbs.example.dkJspCommunity.mysqlutil.SecSql;

public class ArticleDao {

    public List<Article> getForPrintArticlesByBoardId(int boardId, int limitStart, int limitCount, String searchKeywordType, String searchKeyword) {
	List<Article> articles = new ArrayList<>();

	SecSql sql = new SecSql();
	sql.append("SELECT *");
	sql.append(", M.name AS extra_writer");
	sql.append(", B.code AS extra_boardCode");
	sql.append(", B.name AS extra_boardName");
	sql.append("FROM article AS A");
	sql.append("INNER JOIN `member` AS M");
	sql.append("ON A.memberId = M.id");
	sql.append("INNER JOIN `board` AS B");
	sql.append("ON A.boardId = B.id");
	if (boardId != 0) {
	    sql.append("WHERE boardId = ?", boardId);
	}

	if (searchKeyword != null) {
	    if (searchKeywordType == null || searchKeywordType.equals("title")) {
		sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
	    } else if (searchKeywordType.equals("body")) {
		sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
	    } else if (searchKeywordType.equals("titleAndBody")) {
		sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword, searchKeyword);
	    }
	}
	sql.append("ORDER BY A.id DESC");
	
	if ( limitCount != -1) {
	    sql.append("LIMIT ?, ?", limitStart, limitCount);
	}

	System.out.println(sql.getRawSql());

	List<Map<String, Object>> articleMapList = MysqlUtil.selectRows(sql);

	for (Map<String, Object> articleMap : articleMapList) {
	    articles.add(new Article(articleMap));
	}

	return articles;
    }

    public Article getForPrintArticleById(int id) {
	SecSql sql = new SecSql();
	sql.append("SELECT A.*");
	sql.append(", M.name AS extra_writer");
	sql.append(", B.name AS extra_boardName");
	sql.append(", B.code AS extra_boardCode");
	sql.append("FROM article AS A");
	sql.append("INNER JOIN `member` AS M");
	sql.append("ON A.memberId = M.id");
	sql.append("INNER JOIN `board` AS B");
	sql.append("ON A.boardId = B.id");
	sql.append("WHERE A.id = ?", id);

	Map<String, Object> map = MysqlUtil.selectRow(sql);

	if (map.isEmpty()) {
	    return null;
	}

	return new Article(map);
    }

    public Board getBoardById(int id) {
	SecSql sql = new SecSql();
	sql.append("SELECT B.*");
	sql.append("FROM board AS B");
	sql.append("WHERE B.id = ?", id);

	Map<String, Object> map = MysqlUtil.selectRow(sql);

	if (map.isEmpty()) {
	    return null;
	}

	return new Board(map);
    }

    public int write(Map<String, Object> args) {
	SecSql sql = new SecSql();
	sql.append("INSERT INTO article");
	sql.append("SET regDate = NOW()");
	sql.append(", updateDate = NOW()");
	sql.append(", boardId = ?", args.get("boardId"));
	sql.append(", memberId = ?", args.get("memberId"));
	sql.append(", title = ?", args.get("title"));
	sql.append(", body = ?", args.get("body"));

	return MysqlUtil.insert(sql);
    }

    public int modify(Map<String, Object> modifyArgs) {
	SecSql sql = new SecSql();
	sql.append("UPDATE article");
	sql.append("SET updateDate = NOW()");
	sql.append(", boardId = ?", modifyArgs.get("boardId"));
	sql.append(", body = ?", modifyArgs.get("body"));
	sql.append(", title = ?", modifyArgs.get("title"));
	sql.append("where id = ?", modifyArgs.get("id"));
	sql.append("and memberId = ?", modifyArgs.get("memberId"));

	return MysqlUtil.update(sql);
    }

    public Article getForPrintArticleByMemberIdAndId(int memberId, int id) {
	SecSql sql = new SecSql();
	sql.append("SELECT A.*");
	sql.append(", M.name AS extra_writer");
	sql.append(", B.name AS extra_boardName");
	sql.append(", B.code AS extra_boardCode");
	sql.append("FROM article AS A");
	sql.append("INNER JOIN `member` AS M");
	sql.append("ON A.memberId = M.id");
	sql.append("INNER JOIN `board` AS B");
	sql.append("ON A.boardId = B.id");
	sql.append("WHERE A.id = ?", id);
	sql.append("AND memberId = ?", memberId);

	Map<String, Object> map = MysqlUtil.selectRow(sql);

	if (map.isEmpty()) {
	    return null;
	}

	return new Article(map);
    }

    public int delete(Map<String, Object> delArgs) {
	SecSql sql = new SecSql();

	sql.append("DELETE FROM article");
	sql.append("WHERE id = ?", delArgs.get("id"));
	sql.append("AND memberId = ?", delArgs.get("memberId"));

	return MysqlUtil.delete(sql);
    }

    public int getArticlesCountByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
	SecSql sql = new SecSql();
	sql.append("SELECT COUNT(*) AS cnt");
	sql.append("FROM article AS A");
	sql.append("WHERE 1");

	if (searchKeyword != null) {
	    if (searchKeywordType == null || searchKeywordType.equals("title")) {
		sql.append("AND A.title LIKE CONCAT('%', ? '%')", searchKeyword);
	    } else if (searchKeywordType.equals("body")) {
		sql.append("AND A.body LIKE CONCAT('%', ? '%')", searchKeyword);
	    } else if (searchKeywordType.equals("titleAndBody")) {
		sql.append("AND (A.title LIKE CONCAT('%', ? '%') OR A.body LIKE CONCAT('%', ? '%'))", searchKeyword,
			searchKeyword);
	    }
	}

	return MysqlUtil.selectRowIntValue(sql);
    }
}
