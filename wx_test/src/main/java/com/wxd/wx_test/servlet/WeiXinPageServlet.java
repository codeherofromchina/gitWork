package com.wxd.wx_test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.wxd.wx_test.service.WeiXinService;
import com.wxd.wx_test.util.HttpServletUtils;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@WebServlet("/weixinPage")
public class WeiXinPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String STATE = "state";

	private WeiXinService wxService = new WeiXinService();
	private WxMpUser wxUser = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("WeiXinPage.doGet");
		HttpServletUtils.printRequestParams(req);

		String openId = req.getParameter("openId");
		if (StringUtils.isNotBlank(openId)) {
			wxUser = wxService.getUserInfoByOpenId(openId);
		}

		if (wxUser == null) {
			String state = req.getParameter("state");
			String code = req.getParameter("code");
			if (STATE.equals(state)) { // 用户不存在，但是是回调，这里通过获取网页access_token来获取openId并查找用户
				try {
					WxMpOAuth2AccessToken wxOAuth2AccessToken = wxService.oauth2getAccessToken(code);
					openId = wxOAuth2AccessToken.getOpenId();
					wxUser = wxService.getUserInfoByOpenId(openId);
					System.out.println("当前访问用户:" + wxUser);
				} catch (WxErrorException e) {
					e.printStackTrace();
				}
			} else { // 用户不存在，又不是回调，则去授权
				String curl = req.getRequestURL().toString();
				String queryString = req.getQueryString();
				if (StringUtils.isNotEmpty(queryString)) {
					curl = curl + "?" + queryString;
				}
				String authorizationUrl = wxService.oauth2buildAuthorizationUrl(curl, "snsapi_userinfo", STATE);
				resp.sendRedirect(authorizationUrl);
				return;
			}
		}

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("      <HEAD>");
		out.println("    　　<TITLE>A Servlet</TITLE>");
		out.println("    　　<meta http-equiv=\"content-type\" " + "content=\"text/html; charset=utf-8\">");
		out.println("　　 </HEAD>");
		out.println("       <BODY>");
		out.println(wxUser.toString());
		out.println("<img src ='"+wxUser.getHeadImgUrl()+"' />");
		out.println("     </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("WeiXinPage.doPost");
		HttpServletUtils.printRequestParams(req);

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("      <HEAD>");
		out.println("    　　<TITLE>A Servlet</TITLE>");
		out.println("    　　<meta http-equiv=\"content-type\" " + "content=\"text/html; charset=utf-8\">");
		out.println("　　 </HEAD>");
		out.println("       <BODY>");
		out.println("             Hello AnnotationServlet.doPost");
		out.println("     </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
