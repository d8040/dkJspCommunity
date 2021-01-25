package com.sbs.example.dkJspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Board {
	public Board(Map<String, Object> articleMap) {
		this.name = (String) articleMap.get("name");
		this.regDate = (String) articleMap.get("regDate");
		this.updateDate = (String) articleMap.get("updateDate");
		this.id = (int) articleMap.get("id");
		this.code = (String) articleMap.get("code");
	}
	public String name;
	public String regDate;
	public String updateDate;
	public int id;
	public String code;
	

}
