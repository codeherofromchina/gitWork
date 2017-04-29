package com.wxd.wx_test.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wxd.wx_test.service.WeiXinService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * 
 * @author Binary Wang
 *
 */
@Component
public class ClickHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
            Map<String, Object> context, WxMpService wxMpService,
            WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        
        WeiXinService wxService = (WeiXinService)wxMpService;
        
        wxService.submit(new Runnable() {
			@Override
			public void run() {
		        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		        templateMessage.setToUser(openId);
		        templateMessage.setTemplateId("3xH4pe00JZptL6AyY2Ei5gATG6f4kPtu_hQyDunjuMw");
		        List<WxMpTemplateData> dataList = new ArrayList<>();
		        WxMpTemplateData data = new WxMpTemplateData();
		        data.setName("name");
		        data.setValue("王晓丹");
		        data.setColor("#173177");
		        dataList.add(data);
		        
		        templateMessage.setData(dataList);
		        // 发送一个模板消息
		        try {
					wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
				} catch (WxErrorException e) {
					e.printStackTrace();
				}
			}
		});
        
        return null;
    }

}
