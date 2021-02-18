package com.sbs.example.dkJspCommunity.service;

import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.ArticleDao;
import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Board;
import com.sbs.example.dkJspCommunity.dto.Member;

public class ArticleService {

    private ArticleDao articleDao;
    private LikeService likeService;

    public ArticleService() {
	likeService = Container.likeService;
	articleDao = Container.articleDao;
    }

    public List<Article> getForPrintArticlesByBoardId(int boardId, int limitStart, int limitCount, String searchKeywordType, String searchKeyword) {
	return articleDao.getForPrintArticlesByBoardId(boardId, limitStart, limitCount, searchKeywordType, searchKeyword);
    }

    public Article getForPrintArticleById(int id) {
//	return articleDao.getForPrintArticleById(id);
	return getForPrintArticleById(id, null);
    }

    public Article getForPrintArticleById(int id, Member actor) {
	Article article = articleDao.getForPrintArticleById(id);

	if (article == null) {
		return null;
	}

	if (actor != null) {
		updateInfoForPrint(article, actor);
	}

	return article;
    }

    private void updateInfoForPrint(Article article, Member actor) {
	boolean actorCanLike = likeService.actorCanLike(article, actor);
	boolean actorCanCancelLike = likeService.actorCanCancelLike(article, actor);
	boolean actorCanDislike = likeService.actorCanDislike(article, actor);
	boolean actorCanCancelDislike = likeService.actorCanCancelDislike(article, actor);

	article.getExtra().put("actorCanLike", actorCanLike);
	article.getExtra().put("actorCanCancelLike", actorCanCancelLike);
	article.getExtra().put("actorCanDislike", actorCanDislike);
	article.getExtra().put("actorCanCancelDislike", actorCanCancelDislike);	
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

}
