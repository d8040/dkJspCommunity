package com.sbs.example.dkJspCommunity.service;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.LikeDao;
import com.sbs.example.dkJspCommunity.dto.Article;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.dto.Reply;

public class LikeService {
	private LikeDao likeDao;

	public LikeService() {
		likeDao = Container.likeDao;
	}

	public String setLikePoint(String relTypeCode, int relId, int actorId, int point) {
		int likePoint = likeDao.getPoint(relTypeCode, relId, actorId);

		System.out.println("likePoint: " + likePoint);

		if (likePoint != 0) {
			likeDao.modifyPoint(relTypeCode, relId, actorId, point);
		} else if (point == 0) {
			likeDao.removePoint(relTypeCode, relId, actorId);
		} else {
			likeDao.setPoint(relTypeCode, relId, actorId, point);
		}
		return null;
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
}
