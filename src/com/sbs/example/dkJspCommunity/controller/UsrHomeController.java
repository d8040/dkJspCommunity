package com.sbs.example.dkJspCommunity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsrHomeController extends Controller {

	public String showMain(HttpServletRequest req, HttpServletResponse resp) {

		return "usr/home/main";
	}
}
