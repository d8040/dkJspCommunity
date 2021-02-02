package com.sbs.example.dkJspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Like {
    private int id;
    private String regDate;
    private String updateDate;
    private int memberId;
    private int articleId;
    private int like;
    private int unlike;

    public Like(Map<String, Object> map) {
	this.id = (int) map.get("id");
	this.regDate = (String) map.get("regDate");
	this.updateDate = (String) map.get("updateDate");
	this.memberId = (int) map.get("memberId");
	this.articleId = (int) map.get("articleId");
	this.like = (int) map.get("like");
	this.unlike = (int) map.get("unlike");
    }

}
