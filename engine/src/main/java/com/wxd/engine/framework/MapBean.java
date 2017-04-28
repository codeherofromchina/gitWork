package com.wxd.engine.framework;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.wxd.engine.velocity.TemplateInfoEnum;
import com.wxd.engine.velocity.TemplateUtils;

public class MapBean {
	private String templateName;
	private Map<String, Object> map;
	private String fileName;
	private String filePath;

	public MapBean() {
	}

	public MapBean(TemplateInfoEnum tInfo, String fileName,Map<String, Object> map) {
		this.templateName = tInfo.getTemplateName();
		this.map = map;
		this.filePath = tInfo.getFolerPath();
		if (StringUtils.isBlank(fileName)) {
			this.fileName = templateName;
		} else {
			this.fileName = fileName;
		}
		
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void produceFile() {
		if (StringUtils.isBlank(templateName) || StringUtils.isBlank(fileName)) {
			System.err.println("---------------------");
			System.err.println("MapBean err[" + this + "]");
			System.err.println("---------------------");
			return;
		}
		if (map == null) {
			map = new HashMap<>();
		}
		TemplateUtils.meger(templateName, map, filePath + File.separator + fileName);
	}

	@Override
	public String toString() {
		return "MapBean [templateName=" + templateName + ", map=" + map + ", fileName=" + fileName + ", filePath="
				+ filePath + "]";
	}

	

	
}
