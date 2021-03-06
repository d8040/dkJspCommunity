package com.sbs.example.dkJspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Member {
    public int id;
    public String regDate;
    public String updateDate;
    public String loginId;
    public String loginPw;
    public String name;
    public String nickname;
    public String email;
    public String cellphoneNo;
    public int adminLevel;
    private int extra__likePoint;

    public Member(Map<String, Object> map) {
	this.id = (int) map.get("id");
	this.regDate = (String) map.get("regDate");
	this.updateDate = (String) map.get("updateDate");
	this.loginId = (String) map.get("loginId");
	this.loginPw = (String) map.get("loginPw");
	this.name = (String) map.get("name");
	this.nickname = (String) map.get("nickname");
	this.email = (String) map.get("email");
	this.cellphoneNo = (String) map.get("cellphoneNo");
	this.adminLevel = (int) map.get("adminLevel");
	if (map.containsKey("extra__likePoint")) {
	    this.extra__likePoint = (int) map.get("extra__likePoint");
	}
    }

}
