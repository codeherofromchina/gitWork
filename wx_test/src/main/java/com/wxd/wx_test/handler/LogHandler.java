package com.wxd.wx_test.handler;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.wxd.wx_test.service.WeiXinService;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 
 * @author Binary Wang
 *
 */
@Component
public class LogHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {
		// 这里 是接受到的信息打印处理类
		String json = gson.toJson(wxMessage);
		System.out.println( "接受到的消息为：" + json);
		String openId = wxMessage.getFromUser();
		WeiXinService wxService = (WeiXinService)wxMpService;
		if (!wxService.existUserInfo(openId)) {
			// 获取用户信息
			wxService.getUserInfoByOpenId(wxMessage.getFromUser());
		}
		
		return null;
	}

}
