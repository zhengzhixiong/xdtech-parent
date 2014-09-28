package com.xdtech.web.freemark.tags;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

public class SearchBoxTag extends EasyUiTag {

	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		//<input id="userSearchBox" class="easyui-searchbox" data-options="prompt:'请输入用户名',searcher:doSearchUser"   />
		StringBuffer sb = new StringBuffer();
		String id = params.get("id")==null?UUID.randomUUID().toString():params.get("id").toString();
		sb.append("<input id=\""+id+"\" class=\"easyui-searchbox\"")
		  .append(" data-options=\"prompt:'"+params.get("prompt")+"',searcher:"+params.get("searcher")+"\"")
		  .append("/>");
		writeBody(env, sb.toString());
	}

}
