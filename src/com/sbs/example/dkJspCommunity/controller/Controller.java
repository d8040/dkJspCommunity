package com.sbs.example.dkJspCommunity.controller;

import javax.servlet.http.HttpServletRequest;

import com.sbs.example.dkJspCommunity.dto.ResultData;

public class Controller {
    protected String msgAndBack(HttpServletRequest req, String msg) {
	req.setAttribute("alertMsg", msg);
	req.setAttribute("historyBack", true);
	return "common/redirect";
    }

    protected String msgAndReplace(HttpServletRequest req, String msg, String replaceUrl) {
	req.setAttribute("alertMsg", msg);
	req.setAttribute("replaceUrl", replaceUrl);
	return "common/redirect";
    }

    protected String json(HttpServletRequest req, ResultData resultData) {
	req.setAttribute("data", resultData);
	return "common/json";
    }
    
    protected String json(HttpServletRequest req, ResultData resultData, ResultData resultData2) {
	req.setAttribute("data", resultData);
	req.setAttribute("data2", resultData2);
	return "common/json";
    }
}
