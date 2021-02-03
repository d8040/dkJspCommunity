package com.sbs.example.dkJspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Reply {
    public int id;
    public String body;
    public int memberId;
    public int articleId;
    public int parentReplyId;
    public String regDate;
    public String updateDate;
    public int hitsCount;
    public String extra_writer;
    
    public Reply(Map<String, Object> replyMap) {
	this.id = (int) replyMap.get("id");
	this.regDate = (String) replyMap.get("regDate");
	this.updateDate = (String) replyMap.get("updateDate");
	this.parentReplyId = (int) replyMap.get("parentReplyId");
	this.body = (String) replyMap.get("body");
	this.memberId = (int) replyMap.get("memberId");
	this.articleId = (int) replyMap.get("articleId");
	this.hitsCount = (int) replyMap.get("hitsCount");
	this.extra_writer = (String) replyMap.get("extra_writer");
    }
}
