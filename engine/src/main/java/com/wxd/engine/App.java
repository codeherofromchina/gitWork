package com.wxd.engine;

import com.wxd.engine.framework.FrameWork;

/**
 * 程序入口
 * @author wangxiaodan
 *
 */
public class App {
	
	public static void main(String[] args) throws Exception {
		FrameWork framework = new FrameWork();
		framework.genProject();
	}
}
