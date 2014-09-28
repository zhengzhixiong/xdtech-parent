package com.xdtech.web.tags;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 下拉多选框
 * @author zhixiong
 *
 */
public class ComboTreeTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private String url;
	
	private String name;
	
	private boolean multiple = false;
	
	private boolean required = false;//是否必填
	
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	public StringBuffer end() {
		String id = UUID.randomUUID().toString();
		StringBuffer sb = new StringBuffer();

		sb.append("<script type=\"text/javascript\">"
				+"$(function() {"
				+"$(\'#"+id+"\').combotree({"
				+"url:\'"+url+"',"
				+"required:"+required+","
				+"multiple:"+multiple+""
				+"});"
				+"});"
				+"</script>");
		sb.append("<input id=\""+id+"\" name=\""+name+"\" />");
		
//		sb.append("<select id=\""+id+"\" name=\""+name+"\" class=\"easyui-combotree\" data-options=\"url:'"+url+"',required:true\"");
//		if (multiple) {
//			sb.append(" multiple");
//		}
//		sb.append("></select>");
		return sb;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	
	

}
