package com.sbs.example.dkJspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.controller.AdmMemberController;

@WebServlet("/adm/*")
public class AdmDispatcherServlet extends DispatcherServlet {
	@Override
	protected String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName, String actionMethodName) {
		String jspPath = null;
		
		if (controllerName.equals("member")) {
			AdmMemberController admMemberController = Container.admMemberController;

			if (actionMethodName.equals("list")) {
				jspPath = admMemberController.showList(req, resp);
			}
		}
		return jspPath;
	}
}
