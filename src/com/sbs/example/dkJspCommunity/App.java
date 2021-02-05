package com.sbs.example.dkJspCommunity;

public class App {
    public static boolean isProductMode() {
	String profilesActive = System.getProperty("spring.profiles.active");

	if (profilesActive == null) {
	    return false;
	}

	if (profilesActive.equals("production") == false) {
	    return false;
	}

	return true;
    }

    public static String getSiteName() {
	return "PHONEUS::포너스";
    }

    public static String getContextName() {
	if (isProductMode()) {
	    return "";
	}

	return "dkJspCommunity";
    }

    public static String getMainUrl() {
	return getAppUrl();
    }

    public static String getLoginUrl() {
	return getAppUrl() + "/usr/member/login";
    }

    public static String getAppUrl() {
	String appUrl = getSiteProtocol() + "://" + getSiteDomain();

	if (getSitePort() != 80 && getSitePort() != 443) {
	    appUrl += ":" + getSitePort();
	}

	if (getContextName().length() > 0) {
	    appUrl += "/" + getContextName();
	}

	return appUrl;
    }

    private static String getSiteProtocol() {
	if (isProductMode()) {
	    return "https";
	}

	return "http";
    }

    private static int getSitePort() {
	if (isProductMode()) {
	    return 443;
	}

	return 8080;
    }

    private static String getSiteDomain() {
	if (isProductMode()) {
	    return "beta.phoneus.net";
	}

	return "localhost";
    }
}
