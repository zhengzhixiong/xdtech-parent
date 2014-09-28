package com.xdtech.web.model;


/**
 * 后台向前台返回JSON，用于easyui的datagrid
 * 
 * @author
 * 
 */
public class ComboBox {

	private String code;
	private String text;
	private boolean selected;
	
	

	public ComboBox(boolean initNullSelect) {
		super();
		if (initNullSelect) {
			text = "--";
			code = "";
			selected = true;
		}
	}

	public ComboBox(String code, String text, boolean selected) {
		super();
		this.code = code;
		this.text = text;
		this.selected = selected;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
