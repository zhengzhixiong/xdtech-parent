/*
 * Project Name: feui
 * File Name: TestDirective.java
 * Copyright: Copyright(C) 1985-2014 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.web.freemark.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;
import org.springframework.web.servlet.view.AbstractTemplateView;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class TestTag implements TemplateDirectiveModel
{

	public void execute(Environment env, Map params,
						TemplateModel[] loopVars, TemplateDirectiveBody body)
																				throws TemplateException, IOException
	{
		for (TemplateModel templateModel : loopVars)
		{
			templateModel.toString();
		}

		// 真正开始处理输出内容
		Writer out = env.getOut();
		StringBuffer sb = new StringBuffer();
		sb.append("dfsdfdsfdsfsd");
		sb.append(params.get("name"));
		out.write(sb.toString());
		
		/*
		if (body != null)
		{
			out.write(getContent(params));
			body.render(out);
		}*/
		out.flush();
	}

}

class Content
{
	private String title;
	private String content;
	/**
	 * TODO 构造函数说明，请确保和下面的block tags之间保留一行空行
	 * 
	 * @param string
	 * @param string2
	 */
	public Content(String string, String string2)
	{
		title = string;
		content = string2;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	
	

}
