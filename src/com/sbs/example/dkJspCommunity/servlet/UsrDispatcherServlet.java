package com.sbs.example.dkJspCommunity.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.example.dkJspCommunity.container.Container;
import com.sbs.example.dkJspCommunity.controller.UsrArticleController;
import com.sbs.example.dkJspCommunity.controller.UsrHomeController;
import com.sbs.example.dkJspCommunity.controller.UsrLikeController;
import com.sbs.example.dkJspCommunity.controller.UsrMemberController;
import com.sbs.example.dkJspCommunity.controller.UsrReplyController;

@WebServlet("/usr/*")
public class UsrDispatcherServlet extends DispatcherServlet {
	@Override
	protected String doAction(HttpServletRequest req, HttpServletResponse resp, String controllerName, String actionMethodName) {
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
			} else if (actionMethodName.equals("memberModify")) {
				jspPath = usrMemberController.showMemberModify(req, resp);
			} else if (actionMethodName.equals("doMemberModify")) {
				jspPath = usrMemberController.doMemberModify(req, resp);
			} else if (actionMethodName.equals("findLoginId")) {
				jspPath = usrMemberController.showFindLoginId(req, resp);
			} else if (actionMethodName.equals("doFindLoginId")) {
				jspPath = usrMemberController.doFindLoginId(req, resp);
			} else if (actionMethodName.equals("findLoginPw")) {
				jspPath = usrMemberController.showFindLoginPw(req, resp);
			} else if (actionMethodName.equals("doFindLoginPw")) {
				jspPath = usrMemberController.doFindLoginPw(req, resp);
			} else if (actionMethodName.equals("login")) {
				jspPath = usrMemberController.showLogin(req, resp);
			} else if (actionMethodName.equals("doLogin")) {
				jspPath = usrMemberController.doLogin(req, resp);
			} else if (actionMethodName.equals("doLogout")) {
				jspPath = usrMemberController.doLogout(req, resp);
			} else if (actionMethodName.equals("getLoginIdDup")) {
				jspPath = usrMemberController.getLoginIdDup(req, resp);
			}
		} else if (controllerName.equals("article")) {
			UsrArticleController articleController = Container.usrArticleController;

			if (actionMethodName.equals("list")) {
				jspPath = articleController.showList(req, resp);
			} else if (actionMethodName.equals("detail")) {
				jspPath = articleController.showDetail(req, resp);
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
			} else if (actionMethodName.equals("doLike")) {
				jspPath = articleController.doLike(req, resp);
			} else if (actionMethodName.equals("doHate")) {
				jspPath = articleController.doHate(req, resp);
			} else if (actionMethodName.equals("doLikeCancel")) {
				jspPath = articleController.doLikeCancel(req, resp);
			} else if (actionMethodName.equals("doHateCancel")) {
				jspPath = articleController.doHateCancel(req, resp);
			}
		} else if (controllerName.equals("reply")) {
			UsrReplyController replyController = Container.usrReplyController;

			if (actionMethodName.equals("doReplyWrite")) {
				jspPath = replyController.doReplyWrite(req, resp);
			} else if (actionMethodName.equals("doReplyDelete")) {
				jspPath = replyController.doReplyDelete(req, resp);
			} else if (actionMethodName.equals("doReplyModify")) {
				jspPath = replyController.doReplyModify(req, resp);
			} else if (actionMethodName.equals("doReplyWriteAjax")) {
				jspPath = replyController.doReplyWriteAjax(req, resp);
			} else if (actionMethodName.equals("getReplies")) {
				jspPath = replyController.getReplies(req, resp);
			}
		} else if (controllerName.equals("like")) {
			UsrLikeController likeController = Container.usrLikeController;
			
			if (actionMethodName.equals("doLikeAjax")) {
				jspPath = likeController.doLikeAjax(req, resp);
			} else if (actionMethodName.equals("doDislikeAjax")) {
				jspPath = likeController.doDislikeAjax(req, resp);
			} else if (actionMethodName.equals("doReplyLikeAjax")) {
				jspPath = likeController.doReplyLikeAjax(req, resp);
			} else if (actionMethodName.equals("doReplyDislikeAjax")) {
				jspPath = likeController.doReplyDislikeAjax(req, resp);
			} 
		}
		return jspPath;
	}
}
