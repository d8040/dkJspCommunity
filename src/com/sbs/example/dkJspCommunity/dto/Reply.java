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
    
    private Map<String, Object> extra;
    
    public String extra__writer;
    private int extra__likePoint;
    private int extra__likeOnlyPoint;
    private int extra__dislikeOnlyPoint;
    
    public Reply(Map<String, Object> replyMap) {
	this.id = (int) replyMap.get("id");
	this.regDate = (String) replyMap.get("regDate");
	this.updateDate = (String) replyMap.get("updateDate");
	this.parentReplyId = (int) replyMap.get("parentReplyId");
	this.body = (String) replyMap.get("body");
	this.memberId = (int) replyMap.get("memberId");
	this.articleId = (int) replyMap.get("articleId");
	this.hitsCount = (int) replyMap.get("hitsCount");
	if (replyMap.containsKey("extra__writer")) {
	    this.extra__writer = (String) replyMap.get("extra__writer");
	}
	if (replyMap.containsKey("extra__likePoint")) {
	    this.extra__likePoint = (int) replyMap.get("extra__likePoint");
	}
	if (replyMap.containsKey("extra__likeOnlyPoint")) {
	    this.extra__likeOnlyPoint = (int) replyMap.get("extra__likeOnlyPoint");
	}
	if (replyMap.containsKey("extra__dislikeOnlyPoint")) {
	    this.extra__dislikeOnlyPoint = (int) replyMap.get("extra__dislikeOnlyPoint");
	}
    }
}
