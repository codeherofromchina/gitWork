package com.wxd.wx_test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxd.wx_test.service.WeiXinService;
import com.wxd.wx_test.util.HttpServletUtils;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

@WebServlet("/weixinServer")
public class WeiXinServerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WeiXinService wxService = new WeiXinService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletUtils.printRequestParams(request);

		String echostr = request.getParameter("echostr"); // 获取随机字符串
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(echostr);
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpServletUtils.printRequestParams(req);
		BufferedReader reader = req.getReader();

		StringWriter sw = new StringWriter();
		String line;
		while ((line = reader.readLine()) != null) {
			sw.write(line);
		}

		WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(sw.toString());
		WxMpXmlOutMessage outMessage = wxService.route(inMessage);

		String outStr = "";
		if (outMessage != null) {
			outStr = outMessage.toXml();
		}
		resp.setContentType("application/xml; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(outStr);
		out.flush();
		out.close();
	}

}
