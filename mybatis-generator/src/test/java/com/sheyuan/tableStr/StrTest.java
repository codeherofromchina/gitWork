package com.sheyuan.tableStr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class StrTest {
	public static void main(String[] args) throws IOException {
		// <table tableName="jm_store" domainObjectName="Store" />

		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"D:/tmp.txt")));

		String line;

		while ((line = reader.readLine()) != null) {
			handler(line);
		}

		reader.close();
	}

	public static void handler(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, ",");
		StringBuffer tableName = new StringBuffer();
		while (tokenizer.hasMoreTokens()) {
			tableName.setLength(0);

			tableName.append("<table tableName=\"");

			String tmp = tokenizer.nextToken().trim();
			tableName.append(tmp + "\" domainObjectName=\"");

			tmp = tmp.substring(3);
			String[] split = tmp.split("_");
			if(split.length <=1){
				continue;
			}
			for (int i = 0; i < split.length; ++i) {
				tableName.append(upOneChar(split[i]));
			}

			tableName.append("\" />");
			System.out.println(tableName);
		}

	}

	public static String upOneChar(String str) {
		try{
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}catch(Exception ex){
			return str;
		}
	}
}
