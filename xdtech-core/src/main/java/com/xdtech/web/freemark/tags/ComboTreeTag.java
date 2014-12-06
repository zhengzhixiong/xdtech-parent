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
 * @create 2014-11-30上午9:28:00
 * @since 1.0
 * @see
 */
public class ComboTreeTag extends EasyUiTag {

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
		// <input class="easyui-combotree"
		// data-options="url:'tree_data1.json',method:'get',required:true"
		// style="width:200px;">
		//标签方式
		StringBuffer sb = new StringBuffer();
		String id = params.get("id")==null?UUID.randomUUID().toString():params.get("id").toString();
		String name = params.get("name")==null?"none":params.get("name").toString();
		String width = params.get("width")==null?"159":params.get("width").toString();
		String multiple = params.get("multiple")==null?"":params.get("multiple").toString();
		String required = params.get("required")==null?"false":params.get("multiple").toString();
//		sb.append("<input id=\""+id+"\" style=\"width:"+width+"px;\" class=\"easyui-combotree\" name=\""+name+"\"")
//		  .append(" "+multiple)
//		  .append(" data-options=\"")
//		  .append(addProperties("url", params.get("url"), true))
//		  .append(addProperties("valueField", params.get("valueField")==null?"value":params.get("valueField"), true))
//		  .append(addProperties("textField", params.get("textField")==null?"name":params.get("textField"), true))
//		  .append("\"/>");
		
		//js方式
//		<script type="text/javascript">
//		$(function() {
//			$('#11111').combotree({
//			    url: 'group.do?usergroupTree&userId=${(userItem.id)!}',
//			    onChange: function (a,b) {
//			    	 $('#test').val($('#11111').combotree('getValues'));
//			    },
//			    required: true,
//			    multiple:true,
//			});
//		});
//	</script>	
//	<input id="11111" >
//	<input id="test" name="groupIds" hidden="true">
		
		//	<select id="11111" name="groupIds" class="easyui-combotree" data-options="url:'group.do?usergroupTree',method:'post'" multiple style="width:159px;"></select> -->
//		<@eu.comboTree id="123" url="group.do?usergroupTree&userId=${(userItem.id)!}" name="groupIds" multiple="multiple"/>
		
		sb.append("<script type=\"text/javascript\">")
		  .append("$(function() {")
		  .append("$('#"+id+"').combotree({")
		  .append(addProperties("url", params.get("url"), true))
		  .append("onChange : function(a, b) {")
		  .append("$('#"+id+"hidden').val( $('#"+id+"').combotree('getValues'));")
		  .append("},")
		  .append(addProperties("required", params.get("required"),false, true))
		  .append(addProperties("multiple", params.get("multiple"),false, true))
		  .append("});")
		  .append("});")
		  .append("</script>");
	   sb.append("<input id=\""+id+"\" style=\"width:159px\"/>")
	   	 .append("<input id=\""+id+"hidden\" name=\""+name+"\"  type=\"hidden\" />");
		writeBody(env, sb.toString());
	}

}

