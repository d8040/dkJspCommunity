package com.sbs.example.dkJspCommunity.service;

import java.util.List;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.LikeDao;
import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Like;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.dto.Reply;

public class LikeService {
    private static LikeDao likeDao;

    public LikeService() {
	likeDao = Container.likeDao;
    }

    public void setLikePoint(String relTypeCode, int relId, int actorId, int point) {
	likeDao.setPoint(relTypeCode, relId, actorId, point);
    }

    public void removePoint(String relTypeCode, int relId, int actorId, int point) {
	likeDao.removePoint(relTypeCode, relId, actorId);
    }

    public void modifyPoint(String relTypeCode, int relId, int actorId, int point) {
	likeDao.modifyPoint(relTypeCode, relId, actorId, point);
    }
    
    public static boolean isLikedArticle(int id, int memberId, String relTypeCode) {
	return likeDao.isLikedArticle(id, memberId, relTypeCode);
    }

    public static boolean isDislikedArticle(int id, int memberId, String relTypeCode) {
	return likeDao.isDislikedArticle(id, memberId, relTypeCode);
    }

    public boolean actorCanLike(Article article, Member actor) {
	return likeDao.getPoint("article", article.getId(), actor.getId()) == 0;
    }

    public boolean actorCanCancelLike(Article article, Member actor) {
	return likeDao.getPoint("article", article.getId(), actor.getId()) > 0;
    }

    public boolean actorCanDislike(Article article, Member actor) {
	return likeDao.getPoint("article", article.getId(), actor.getId()) == 0;
    }

    public boolean actorCanCancelDislike(Article article, Member actor) {
	return likeDao.getPoint("article", article.getId(), actor.getId()) < 0;
    }

    public boolean actorCanLike(Reply reply, Member actor) {
	return likeDao.getPoint("reply", reply.getId(), actor.getId()) == 0;
    }

    public boolean actorCanCancelLike(Reply reply, Member actor) {
	return likeDao.getPoint("reply", reply.getId(), actor.getId()) > 0;
    }

    public boolean actorCanDislike(Reply reply, Member actor) {
	return likeDao.getPoint("reply", reply.getId(), actor.getId()) == 0;
    }

    public boolean actorCanCancelDislike(Reply reply, Member actor) {
	return likeDao.getPoint("reply", reply.getId(), actor.getId()) < 0;
    }

    public List<Like> getForPintLikesByArticleId(int id, int memberId) {	
	return likeDao.getForPintLikesByArticleId(id, memberId);
    }

}
