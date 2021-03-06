package com.xdtech.web.freemark.tags;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

public class TreeTag extends EasyUiTag {

	// <ul id="tt"></ul>
	// $('#tt').tree({
	// url:'tree_data.json'
	// });
	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		try {
			String treeId = params.get("id") == null ? UUID.randomUUID().toString() : params.get("id").toString();
			StringBuffer sb = new StringBuffer("<ul id=\"" + treeId + "\"></ul>");
			sb.append("<script type=\"text/javascript\">")
			  .append("var "+treeId+" = null;")
			  .append("$(function() {")
			  .append(treeId+" = $('#"+treeId+"');")
			  .append(treeId+".tree({")
			  .append(addProperties("method", params.get("method"), true))
			  .append(addProperties("url", params.get("url"), true))
			  .append(addProperties("checkbox", params.get("checkbox")==null?false:true, false))
			  .append(addProperties("animate", params.get("animate")==null?false:true, false))
			  .append(addProperties("lines", params.get("lines")==null?false:true, false));
			if (params.get("formatter")!=null) {
				sb.append("formatter:function(node){"+params.get("formatter")+";},");
			}
			if (params.get("onClick")!=null) {
				sb.append("onClick:function(node){"+params.get("onClick")+";},");
			}
			if (params.get("onCheck")!=null) {
				sb.append("onCheck:function(node,checked){"+params.get("onCheck")+";}");
			}
			  
			sb.append("});");
			sb.append("});");
			sb.append("</script>");
			// 真正开始处理输出内容
			writeBody(env, sb.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String addJsFunction(String functionName,String functionJs){
		//
		StringBuffer js = new StringBuffer("");
//		if (functionJs!=null) {
//			js.append(functionName+"")
//		}
		return js.toString();
	}
	

}
