package com.wxd.engine.velocity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.wxd.engine.framework.FolerUtil;

public class TemplateUtils {
	private static final String TEMPLATE_PATH = "template/";
	private static final String ENCODING = "UTF-8";
	private static VelocityEngine ve = new VelocityEngine();

	static {
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init();
	}

	/**
	 * 获取制定名称的模板
	 * 
	 * @param templateName
	 * @return
	 */
	public static Template getTemplate(String templateName) {
		return ve.getTemplate(TEMPLATE_PATH + templateName,ENCODING);
	}

	public static void meger(Template template, Context context, Writer writer) {
		template.merge(context, writer);
	}

	public static void meger(String template, Context context, Writer writer) {
		Template t = getTemplate(template);
		meger(t, context, writer);
	}

	public static void meger(String template, Map<String, Object> context, Writer writer) {
		VelocityContext ctx = new VelocityContext();
		for (Entry<String, Object> entry : context.entrySet()) {
			ctx.put(entry.getKey(), entry.getValue());
		}
		meger(template, ctx, writer);
	}

	public static String meger(String template, Map<String, Object> context) {
		StringWriter sw = new StringWriter();
		try {
			meger(template, context, sw);
			return sw.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				sw.close();
			} catch (IOException e) {
				System.err.println("内存泄漏");
				e.printStackTrace();
			}
		}

	}

	public static void meger(String template, Map<String, Object> context, String fileName) {
		BufferedWriter writer = null;
		try {
			File file = new File(fileName);
			FolerUtil.mkdir(file.getParentFile());
			writer = new BufferedWriter(new FileWriter(file));
			meger(template, context, writer);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.err.println("内存泄漏");
					e.printStackTrace();
				}
			}
		}
	}
}
