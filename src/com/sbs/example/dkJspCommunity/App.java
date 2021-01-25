package com.sbs.example.dkJspCommunity;

public class App {
    public static String getSite() {
	return "JSP Community";
    }
    private static String getContextName() {
	return "dkJspCommunity";
    }
    public static String getMainUrl() {
	return "Http://" + getSiteDomain() + ":" + getSitePort() + "/dkJspCommunity/usr/home/main";
    }
    private static int getSitePort() {
	return 8080;
    }
    private static String getSiteDomain() {
	return "localhost";
    }
    public static String getLoginUrl() {
	return "http://" + getSiteDomain() + ":" + getSitePort() + "/" + getContextName() + "/usr/member/login";
    }
}
