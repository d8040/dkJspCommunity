package com.sbs.example.dkJspCommunity.service;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.LikeDao;

public class LikeService {
    private LikeDao likeDao;

    public LikeService() {
	likeDao = Container.likeDao;
    }

    public void doLike(int memberId, int articleId, int like, int unlike) {
	likeDao.doLike(memberId, articleId, like, unlike);
    }

}
