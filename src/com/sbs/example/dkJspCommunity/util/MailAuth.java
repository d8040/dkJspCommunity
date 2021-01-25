package com.sbs.example.dkJspCommunity.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator {
    
    PasswordAuthentication pa;
    
    public MailAuth(String mainId, String mainPw) {
	pa = new PasswordAuthentication(mainId, mainPw);
    }
    
    public PasswordAuthentication getPasswordAuthentication() {
	return pa;
    }

}
