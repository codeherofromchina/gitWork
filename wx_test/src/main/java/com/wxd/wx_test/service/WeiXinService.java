package com.wxd.wx_test.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.wxd.wx_test.confing.WeiXinConfig;
import com.wxd.wx_test.db.UserInfoDB;
import com.wxd.wx_test.handler.ClickHandler;
import com.wxd.wx_test.handler.LogHandler;
import com.wxd.wx_test.handler.SubscribeHandler;
import com.wxd.wx_test.handler.UnsubscribeHandler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 这个类可以参考gitHub项目 ： https://github.com/Wechat-Group/weixin-java-tools
 * @author wangxiaodan
 *
 */
public class WeiXinService extends WxMpServiceImpl {
	private ExecutorService executorService ;
	private UserInfoDB userInfoDB = null;
	private WxMpMessageRouter router = null;

	public WeiXinService() {
		final WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId(WeiXinConfig.getAppid());// 设置微信公众号的appid
		config.setSecret(WeiXinConfig.getAppsecret());// 设置微信公众号的app corpSecret
		config.setToken(WeiXinConfig.getToken());// 设置微信公众号的token
		config.setAesKey(WeiXinConfig.getAeskey());// 设置消息加解密密钥
		super.setWxMpConfigStorage(config);

		userInfoDB = new UserInfoDB();
		executorService = Executors.newFixedThreadPool(100);
		this.refreshRouter();
	}

	private void refreshRouter() {
		final WxMpMessageRouter newRouter = new WxMpMessageRouter(this);
		// 记录所有事件的日志
		newRouter.rule().handler(new LogHandler()).next();
		// 关注事件
		newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_SUBSCRIBE)
				.handler(new SubscribeHandler()).end();
		// 取消关注事件
		newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_UNSUBSCRIBE)
				.handler(new UnsubscribeHandler()).end();
		// 取消关注事件
		newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_CLICK)
		.handler(new ClickHandler()).end();
		this.router = newRouter;
	}

	public WxMpXmlOutMessage route(WxMpXmlMessage message) {
		try {
			return this.router.route(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public WxMpUser getUserInfoByOpenId(String openId) {
		WxMpUser userWxInfo = null;
		try {
			userWxInfo = this.getUserService().userInfo(openId, null);
			this.userInfoDB.save(userWxInfo);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return userWxInfo;
	}
	
	
	public boolean existUserInfo(String openId) {
		return userInfoDB.getUserInfoByOpenid(openId) != null;
	}
	
	
	public Future<?> submit(Runnable runnable) {
		 return this.executorService.submit(runnable);
	}

}
