package com.sbs.example.dkJspCommunity.dto;

import java.util.Map;

import lombok.Data;

@Data
public class Like {
    private int id;
    private String regDate;
    private String updateDate;
    private int memberId;
    private int relId;
    private String relTypeCode;
    private int point;
    
    private Map<String, Object> extra;

    public Like(Map<String, Object> map) {
	this.id = (int) map.get("id");
	this.regDate = (String) map.get("regDate");
	this.updateDate = (String) map.get("updateDate");
	this.relTypeCode = (String) map.get("relTypeCode");
	this.memberId = (int) map.get("memberId");
	this.relId = (int) map.get("relId");
	this.point = (int) map.get("point");
    }

}
