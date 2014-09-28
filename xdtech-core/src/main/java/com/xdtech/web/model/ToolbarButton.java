package com.xdtech.web.model;

public class ToolbarButton {
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
	/**
	 * 权限编码
	 */
	private String operationCode;
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
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	
	
}
