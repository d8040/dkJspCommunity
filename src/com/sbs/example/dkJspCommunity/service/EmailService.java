package com.sbs.example.dkJspCommunity.service;

public class EmailService {
    private String gmailId;
    private String gmailPw;
    private String from;
    private String fromName;
    
    public void init(String gmailId, String gmailPw, String from, String fromName) {
	this.gmailId = gmailId;
	this.gmailPw = gmailPw;
	this.from = from;
	this.fromName = fromName;
	
	System.out.println("메일 서비스 실행");
	System.out.println(gmailId);
    }
    
}
