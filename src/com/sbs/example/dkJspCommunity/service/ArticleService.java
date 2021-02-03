package com.sbs.example.dkJspCommunity.service;

import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.ArticleDao;
import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Board;

public class ArticleService {

    private ArticleDao articleDao;

    public ArticleService() {
	articleDao = Container.articleDao;
    }

    public List<Article> getForPrintArticlesByBoardId(int boardId, int limitStart, int limitCount, String searchKeywordType, String searchKeyword) {
	return articleDao.getForPrintArticlesByBoardId(boardId, limitStart, limitCount, searchKeywordType, searchKeyword);
    }

    public Article getForPrintArticleById(int id) {
	return articleDao.getForPrintArticleById(id);
    }

    public Board getBoardById(int id) {
	return articleDao.getBoardById(id);
    }

    public int write(Map<String, Object> args) {
	return articleDao.write(args);
    }

    public int modify(Map<String, Object> modifyArgs) {
	return articleDao.modify(modifyArgs);
    }

    public Article getForPrintArticleByMemberIdAndId(int memberId, int id) {
	return articleDao.getForPrintArticleByMemberIdAndId(memberId, id);
    }

    public int delete(Map<String, Object> delArgs) {
	return articleDao.delete(delArgs);
    }

    public int getArticlesCountByBoardId(int boardId, String searchKeywordType, String searchKeyword) {
	return articleDao.getArticlesCountByBoardId(boardId, searchKeywordType, searchKeyword);
    }

    public void hitCount(int id) {
	articleDao.hitCount(id);
    }

    public int likeCount(int id) {
	return articleDao.likeCount(id);
    }

    public int hateCount(int id) {
	return articleDao.hateCount(id);
    }

    public int isLiked(int memberId, int articleId) {
	return articleDao.isLiked(memberId, articleId);
    }

    public int isHated(int memberId, int articleId) {
	return articleDao.isHated(memberId, articleId);
    }

}
