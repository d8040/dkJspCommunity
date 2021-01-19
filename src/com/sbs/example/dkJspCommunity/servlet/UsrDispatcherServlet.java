package com.sbs.example.dkJspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.controller.UsrArticleController;
import com.sbs.example.dkJspCommunity.controller.UsrHomeController;
import com.sbs.example.dkJspCommunity.controller.UsrMemberController;

@WebServlet("/usr/*")
public class UsrDispatcherServlet extends DispatcherServlet {
	@Override
	protected String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName,
		String actionMethodName) {
		String jspPath = null;

		if (controllerName.equals("home")) {
			UsrHomeController usrHomeController = Container.usrHomeController;

			if (actionMethodName.equals("main")) {
				jspPath = usrHomeController.showMain(req, resp);
			}
		} else if (controllerName.equals("member")) {
			UsrMemberController usrMemberController = Container.usrMemberController;

			if (actionMethodName.equals("join")) {
				jspPath = usrMemberController.join(req, resp);
			} else if (actionMethodName.equals("doJoin")) {
				jspPath = usrMemberController.doJoin(req, resp);
			} else if (actionMethodName.equals("login")) {
				jspPath = usrMemberController.showLogin(req, resp);
			} else if (actionMethodName.equals("doLogin")) {
				jspPath = usrMemberController.doLogin(req, resp);
			} else if (actionMethodName.equals("doLogout")) {
				jspPath = usrMemberController.doLogout(req, resp);
			}
		} else if (controllerName.equals("article")) {
			UsrArticleController articleController = Container.articleController;

			if (actionMethodName.equals("list")) {
				jspPath = articleController.showList(req, resp);
			} else if (actionMethodName.equals("detail")) {
				jspPath = articleController.showDatail(req, resp);
			} else if (actionMethodName.equals("write")) {
				jspPath = articleController.showWrite(req, resp);
			} else if (actionMethodName.equals("doWrite")) {
				jspPath = articleController.doWrite(req, resp);
			} else if (actionMethodName.equals("modify")) {
				jspPath = articleController.showModify(req, resp);
			} else if (actionMethodName.equals("doModify")) {
				jspPath = articleController.doModify(req, resp);
			} else if (actionMethodName.equals("doDelete")) {
				jspPath = articleController.doDelete(req, resp);
			}
		}
		return jspPath;
	}
}
