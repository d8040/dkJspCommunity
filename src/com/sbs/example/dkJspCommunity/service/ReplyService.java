package com.sbs.example.dkJspCommunity.service;

import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.ReplyDao;
import com.sbs.example.dkJspCommunity.dto.Member;
import com.sbs.example.dkJspCommunity.dto.Reply;

public class ReplyService {

    private ReplyDao replyDao;
    private LikeService likeService;

    public ReplyService() {
	replyDao = Container.replyDao;
	likeService = Container.likeService;
    }

    public int write(Map<String, Object> writeArgs) {
	return replyDao.write(writeArgs);
    }

    public List<Reply> getForPrintRepliesByArticleId(int id) {
	return replyDao.getForPrintRepliesByArticleId(id);
    }

    public Reply getForPrintReplyById(int id) {
	return getForPrintReplyById(id, null);
    }

    public Reply getForPrintReplyById(int id, Member actor) {
	Reply reply = replyDao.getForPrintRepliyByArticleId(id);

	if (reply == null) {
	    return null;
	}

	if (actor != null) {
	    updateInfoForPrint(reply, actor);
	}

	return reply;
    }

    private void updateInfoForPrint(Reply reply, Member actor) {
	boolean actorCanLike = likeService.actorCanLike(reply, actor);
	boolean actorCanCancelLike = likeService.actorCanCancelLike(reply, actor);
	boolean actorCanDislike = likeService.actorCanDislike(reply, actor);
	boolean actorCanCancelDislike = likeService.actorCanCancelDislike(reply, actor);

	reply.getExtra().put("actorCanLike", actorCanLike);
	reply.getExtra().put("actorCanCancelLike", actorCanCancelLike);
	reply.getExtra().put("actorCanDislike", actorCanDislike);
	reply.getExtra().put("actorCanCancelDislike", actorCanCancelDislike);	
    }

    public void delete(Map<String, Object> args) {
	replyDao.delete(args);

    }

    public void doModify(Map<String, Object> modifyArgs) {
	replyDao.doModify(modifyArgs);
    }

}
