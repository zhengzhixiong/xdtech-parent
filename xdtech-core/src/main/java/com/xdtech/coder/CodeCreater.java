/*
 * Copyright 2013-2014 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xdtech.coder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xdtech.coder.model.ModelField;
import com.xdtech.coder.model.ModelTable;
import com.xdtech.coder.vo.Coder;
import com.xdtech.common.service.impl.BaseService;
import com.xdtech.common.utils.ApplicationContextUtil;
import com.xdtech.common.utils.DateUtil;
import com.xdtech.common.utils.DateUtil.DateStyle;
import com.xdtech.web.freemark.tags.ShiroTags;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author max.zheng
 * @create 2014-10-26下午8:48:10
 * @since 1.0
 * @see
 */
public class CodeCreater {
	private static Configuration cfg = null;

	// 创建数据模型
	static Map<String, Object> rootMap = new HashMap<String, Object>();
	// 模块名称
	static String moduleName = "message";
	// 模型名称
	static String modelName = "new";
	// 表名称
	static String tableName = moduleName.toUpperCase() + "_"+ modelName.toUpperCase();

	static String fileUrl = "F:/zzx";



	
	public static boolean create(Coder coder) {
		boolean rs = true;
		rootMap.clear();
		moduleName = coder.getModel().getModuleName();
		modelName = coder.getModel().getModelName();
		tableName = coder.getModel().getTableName();
		BaseService baseService = ApplicationContextUtil.getContext().getBean(BaseService.class);
		ModelTable modelTable = coder.getModel();
		baseService.save(modelTable);
		List<ModelField> coderFields = new ArrayList<ModelField>();
		for (ModelField field : coder.getFields()) {
			field.setModelTable(modelTable);
			field.setId(null);
			baseService.save(field);
			coderFields.add(field);
		}
		modelTable.setModelFields(coderFields);
		
		rootMap.put("moduleName", moduleName);
		// 创建时间
		rootMap.put("createTime",
				DateUtil.getDate(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS));
		rootMap.put("modelName", modelName);
		rootMap.put("tableName", tableName);
		rootMap.put("fields", coderFields);
		
		try {
			init();
			processModel();
			processDao();
			processService();
			processModelItem();
			processController();
			processModelHtml();
			processModelEditHtml();
		} catch (Exception e) {
			e.printStackTrace();
			rs = false;
		} 
		return rs;
	}

	public static void main(String[] args) {
		try {
			init();
			processModel();
			processModelItem();
			processController();
			processService();
			processDao();
			
			processModelHtml();
			processModelEditHtml();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author max.zheng
	 * @throws Exception 
	 * @create 2014-10-28下午11:05:42
	 * @modified by
	 */
	private static void processModelEditHtml() throws Exception {
		Template template = cfg.getTemplate("editModelHtml.ftl");
		String javaModelUrl = fileUrl + "/edit" + toUpperCaseFirstOne(modelName) + ".html";
		createCodeFile(template, javaModelUrl);
	}

	/**
	 * 
	 * @author max.zheng
	 * @throws Exception 
	 * @create 2014-10-28下午11:05:38
	 * @modified by
	 */
	private static void processModelHtml() throws Exception {
		Template template = cfg.getTemplate("modelHtml.ftl");
		String javaModelUrl = fileUrl + "/" + modelName + ".html";
		createCodeFile(template, javaModelUrl);
	}

	/**
	 * 
	 * @author max.zheng
	 * @throws Exception 
	 * @create 2014-10-28下午11:05:33
	 * @modified by
	 */
	private static void processDao() throws Exception {
		Template template = cfg.getTemplate("modelDao.ftl");
		String javaModelUrl = fileUrl + "/" + toUpperCaseFirstOne(modelName) + "Dao.java";
		createCodeFile(template, javaModelUrl);
	}

	/**
	 * 
	 * @author max.zheng
	 * @throws Exception 
	 * @create 2014-10-28下午11:05:20
	 * @modified by
	 */
	private static void processService() throws Exception {
		Template template = cfg.getTemplate("modelService.ftl");
		String javaModelUrl = fileUrl + "/" + toUpperCaseFirstOne(modelName) + "Service.java";
		createCodeFile(template, javaModelUrl);
		
		template = cfg.getTemplate("modelServiceImpl.ftl");
		javaModelUrl = fileUrl + "/" + toUpperCaseFirstOne(modelName) + "ServiceImpl.java";
		createCodeFile(template, javaModelUrl);
	}

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-10-28下午11:05:17
	 * @modified by
	 */
	private static void processController() throws Exception {
		Template template = cfg.getTemplate("modelController.ftl");
		String javaModelUrl = fileUrl + "/" + toUpperCaseFirstOne(modelName) + "Controller.java";
		createCodeFile(template, javaModelUrl);
	}

	// 初始化工作
	public static void init() throws Exception {
		cfg = new Configuration();
		String path = CodeCreater.class.getResource("/") + "coderTemplate";
		path = path.substring(6).replaceAll("%20", " ");
		// 设置模板文件位置
		cfg.setDirectoryForTemplateLoading(new File(path));
//		cfg.setSharedVariable("shiro", new ElementTypeClassTags());
		File javaModelFile = new File(fileUrl);
		if (!javaModelFile.exists()) {
			javaModelFile.mkdir();
		}
	}

	public static void processModel() throws Exception {
		Template template = cfg.getTemplate("model.ftl");
		String javaModelUrl = fileUrl + "/" + toUpperCaseFirstOne(modelName) + ".java";
		createCodeFile(template, javaModelUrl);
	}

	private static void createCodeFile(Template template, String javaModelUrl) throws FileNotFoundException, TemplateException, IOException
	{
		File javaModelFile = new File(javaModelUrl);
		if (javaModelFile.exists())
		{
			javaModelFile.delete();
		}
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(
				javaModelFile));
		// 合并处理（模板 + 数据模型）
		template.process(rootMap, outputStreamWriter);
		outputStreamWriter.flush();
		outputStreamWriter.close();
	}

	/**
	 * 
	 * @author max.zheng
	 * @throws Exception 
	 * @create 2014-10-28下午10:53:19
	 * @modified by
	 */
	private static void processModelItem() throws Exception {
		Template template = cfg.getTemplate("modelItem.ftl");
		String javaModelUrl = fileUrl + "/" + toUpperCaseFirstOne(modelName) + "Item.java";
		createCodeFile(template, javaModelUrl);
	}
	
	public static String toUpperCaseFirstOne(String name) {
        char[] ch = name.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (i == 0) {
                ch[0] = Character.toUpperCase(ch[0]);
            } else {
                ch[i] = Character.toLowerCase(ch[i]);
            }
        }
        StringBuffer a = new StringBuffer();
        a.append(ch);
        return a.toString();
    }
}
