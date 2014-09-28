package com.xdtech.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;


public class ButtonTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;
//	private ToolbarButton toolbarButton;
	/**
	 * 权限编码
	 */
	private String operationCode;
	/**
	 * 图标
	 */
	private String iconCls;
	/**
	 * 点击方法
	 */
	private String onclick;
	/**
	 * 显示名称
	 */
	private String labelName;
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
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
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotBlank(operationCode)) {
			if (SecurityUtils.getSubject().isPermitted(operationCode)) {
				sb.append("<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'"+iconCls+"',plain:true\" onclick=\""+onclick+"\">"+labelName+"</a>");
			}
		}
		return sb;
	}

}
