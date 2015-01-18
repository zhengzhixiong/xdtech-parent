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
package com.xdtech.web.freemark.tags;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

/**
 * 
 * @author max.zheng
 * @create 2014-10-12下午8:15:59
 * @since 1.0
 * @see
 */
public class ComboBoxTag extends EasyUiTag {

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-12下午8:16:23
	 * @modified by
	 * @param env
	 * @param params
	 * @param body
	 * @throws IOException
	 * @throws TemplateException
	 */
	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
//		<input class="easyui-combobox"  name="language" data-options="
//                url:'combobox_data1.json',
//                method:'get',
//                valueField:'id',
//                textField:'text',
//                panelHeight:'auto',
//                icons:[{
//                    iconCls:'icon-add'
//                },{
//                    iconCls:'icon-cut'
//                }]
//        ">
		StringBuffer sb = new StringBuffer();
		String id = params.get("id")==null?UUID.randomUUID().toString():params.get("id").toString();
		String name = params.get("name")==null?"none":params.get("name").toString();
		String width = params.get("width")==null?"159":params.get("width").toString();
		sb.append("<input id=\""+id+"\" style=\"width:"+width+"px;\" class=\"easyui-combobox\" name=\""+name+"\"")
		  .append(" data-options=\"")
//		  .append(addProperties("width", params.get("width")==null?"150":params.get("width"), false))
		  .append(addProperties("url", params.get("url"), true))
		  .append(addProperties("required", params.get("required")==null?"false":params.get("required"), false))
		  .append(addProperties("valueField", params.get("valueField")==null?"value":params.get("valueField"), true))
		  .append(addProperties("textField", params.get("textField")==null?"name":params.get("textField"), true))
		  .append("\"/>");
		writeBody(env, sb.toString());
	}

}
