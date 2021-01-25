package com.sbs.example.dkJspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Article {
	public int id;
	public String title;
	public String body;
	public int memberId;
	public int boardId;
	public String regDate;
	public String updateDate;
	public String extra_writer;
	public String extra_boardName;
	public String extra_boardCode;
	public int hitsCount;

	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = (String) articleMap.get("regDate");
		this.updateDate = (String) articleMap.get("updateDate");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.memberId = (int) articleMap.get("memberId");
		this.boardId = (int) articleMap.get("boardId");
		this.hitsCount = (int) articleMap.get("hitsCount");
		if(articleMap.containsKey("extra_writer")) {
			this.extra_writer = (String)articleMap.get("extra_writer");
		}
		if(articleMap.containsKey("extra_boardName")) {
			this.extra_boardName = (String)articleMap.get("extra_boardName");
		}
		if(articleMap.containsKey("extra_boardCode")) {
			this.extra_boardCode = (String)articleMap.get("extra_boardCode");
		}
	}

}
