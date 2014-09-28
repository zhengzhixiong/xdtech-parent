package com.xdtech.web.tags;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;

public class ToolbarButtonTag extends TagSupport{
	private static final long serialVersionUID = 1L;
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
	
	public int doEndTag() throws JspTagException {
		Tag t = findAncestorWithClass(this, DataGridToolbarTag.class);
		DataGridToolbarTag parent = (DataGridToolbarTag) t;
		if (StringUtils.isNotBlank(operationCode)) {
			if (SecurityUtils.getSubject().isPermitted(operationCode)) {
				parent.setButton(iconCls, labelName, onclick,operationCode);
			}
		}else {
			parent.setButton(iconCls, labelName, onclick,operationCode);
		}
		
		return EVAL_PAGE;
	}
}
