package com.sbs.example.dkJspCommunity.service;

import java.util.List;
import java.util.Map;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.dao.ReplyDao;
import com.sbs.example.dkJspCommunity.dto.Reply;

public class ReplyService {

    private ReplyDao replyDao;
    
    public ReplyService() {
	replyDao = Container.replyDao;
    }

    public int write(Map<String, Object> writeArgs) {
	return replyDao.write(writeArgs);
    }

    public List<Reply> getForPrintrepliesByArticleId(int id) {
	return replyDao.getForPrintRepliesByArticleId(id);
    }

    public void delete(Map<String, Object> args) {
	replyDao.delete(args);
	
    }

}
