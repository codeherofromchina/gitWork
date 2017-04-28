package com.wxd.template.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class GenString {
	private static final Log LOGGER = LogFactory.getLog(GenString.class);
	private static final char[] ALL_CHAR= {'a','b','c','d','e','f','g','h','i','g','k','l','m','n','o','p','q','r',
			's','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0'};
	private static long start = 0;
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		//crack(15,new StringBuilder());
		long l = 8138741398091333632L / 100000L;
		System.out.println(l/60/60/24);
				
	}
	protected static long num = 1 ;
	protected static long quan = 1 ;
	
	
	public static void crack(int pwdLen,StringBuilder sBuilder) {
		if (pwdLen == 0) {
			num++;
			LOGGER.info(sBuilder.toString());
			if (num%100000 == 0) {
				quan++;
				num = 1;
				System.out.println(quan + ":" + sBuilder.toString() + ":" + (System.currentTimeMillis() - start));
			}
		} else {
			for (int i=0;i<ALL_CHAR.length;i++) {
				sBuilder.append(ALL_CHAR[i]);
				crack(pwdLen-1,sBuilder);
				sBuilder.setLength(sBuilder.length() - 1);
			}
		}
	}
}
