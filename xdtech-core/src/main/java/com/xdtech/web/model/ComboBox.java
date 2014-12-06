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
	private String value;
	private String name;
	private boolean selected;
	
	

	public ComboBox() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
