package com.wxd.wx_test.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class UserInfoDB {
	private static List<WxMpUser> userInfoSet = new ArrayList<WxMpUser>();

	public WxMpUser getUserInfoByOpenid(String openId) {
		for (WxMpUser u : userInfoSet) {
			if (u.getOpenId().equals(openId)) {
				return u;

			}
		}
		return null;
	}

	public boolean save(WxMpUser userInfo) {
		if (userInfo == null) {
			return false;
		}
		String openId = userInfo.getOpenId();
		if (StringUtils.isNotBlank(openId)) {
			if (this.getUserInfoByOpenid(openId) == null) {
				System.out.println("保存用户：" + userInfo.toString());
				return userInfoSet.add(userInfo);
			}
		}
		return false;
	}

}
