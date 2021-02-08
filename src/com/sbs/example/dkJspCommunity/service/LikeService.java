package com.sbs.example.dkJspCommunity.service;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.LikeDao;

public class LikeService {
    private LikeDao likeDao;

    public LikeService() {
	likeDao = Container.likeDao;
    }

	public void setLikePoint(String relTypeCode, int relId, int actorId, int point) {
		if (point == 0) {
			likeDao.removePoint(relTypeCode, relId, actorId);
		} else {
			likeDao.setPoint(relTypeCode, relId, actorId, point);
		}
	}

}
