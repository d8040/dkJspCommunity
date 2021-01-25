package com.sbs.example.dkJspCommunity.servlet;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.service.EmailService;
import com.sbs.example.util.Util;

@WebServlet(name = "loadAppConfig", urlPatterns = { "/loadConfig" }, loadOnStartup = 1)
public class ConfigServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);

	ServletContext context = getServletContext();
	InputStream inStream = context.getResourceAsStream("/META-INF/config.json");

	Map<String, Object> configMap = Util.getJsonMapFromFile(inStream);
	
	String gmailId = (String) configMap.get("gmailId");
	String gmailPw = (String) configMap.get("gmailPw");
	
	EmailService emailService = Container.emailService;
	emailService.init(gmailId, gmailPw, "phoneus.adm@gmail.com", "phoneus.net");
	
//	emailService.sendMail("phoneus.adm@gmail.com", "메일 테스트 입니다.", "내용 입니다.");
    }
}
