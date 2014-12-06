package com.xdtech.web.freemark.tags;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

public class LinkButtonTag extends EasyUiTag {

	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		// <a href="javascript:void(0)" class="easyui-linkbutton"
		// iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
		StringBuffer sb = new StringBuffer();
		if (params.get("shiro")==null||(SecurityUtils.getSubject().isPermitted(params.get("shiro").toString()))) {
			sb.append("<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\"")
			  .append(" iconCls=\""+params.get("iconCls").toString()+"\"")
			  .append(" plain=\"true\"")
			  .append(" onclick=\""+params.get("onclick")+"\"")
			  .append(">")
			  .append(params.get("showName"));
		}
		
		
		sb.append("</a>");
		writeBody(env, sb.toString());
	}

}
