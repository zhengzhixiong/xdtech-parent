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

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * @author max.zheng
 * @create 2014-10-29下午10:43:36
 * @since 1.0
 * @see
 */
public class ElementTypeClassTags implements TemplateDirectiveModel{

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-29下午10:44:26
	 * @modified by
	 * @param env
	 * @param params
	 * @param loopVars
	 * @param body
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		render(env, params, body);
	}
	
	public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException
	{

		try
		{
			StringBuffer sb = new StringBuffer("");
			
			// 真正开始处理输出内容
			if (env != null) {
	        	// 真正开始处理输出内容
				Writer out = env.getOut();
				out.write(sb.toString());
				out.flush();
	        }
		}
		catch (Exception e)
		{
			
		}

	}

}
