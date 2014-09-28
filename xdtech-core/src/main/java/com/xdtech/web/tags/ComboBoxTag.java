package com.xdtech.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;



public class ComboBoxTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	protected String id;// ID
	protected String text;// 显示文本
	protected String url;//远程数据
	protected String name;//控件名称
	protected Integer width = 200;//宽度
	protected Integer listWidth;//下拉框宽度
	protected Integer listHeight;//下拉框高度
	protected boolean editable;//定义是否可以直接到文本域中键入文本
	protected boolean initNullSelect = true;//是否初始化空选项
	protected boolean multiple = false; //是否可以多选
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
//		ComboBox comboBox=new ComboBox();
//		comboBox.setText(text);
//		comboBox.setco(id);
		sb.append("<script type=\"text/javascript\">"
				+"$(function() {"
				+"$(\'#"+id+"\').combobox({"
				+"url:\'"+url+"&initNullSelect="+initNullSelect+"\',"
				+"editable:\'false\',"
				+"valueField:\'code\',"
				+"textField:\'text\'," 
				+"width:\'"+width+"\'," 
				+"listWidth:\'"+listWidth+"\'," 
				+"listHeight:\'"+listWidth+"\'," 
				+"multiple:"+multiple+","
				+"onChange:function(){"
				+"var val = $(\'#"+id+"\').combobox(\'getValues\');"
				+"$(\'#"+id+"hidden\').val(val);"
				+"}"
				+"});"
				+"});"
				+"</script>");
		sb.append("<input type=\"hidden\" name=\""+id+"\" id=\""+id+"hidden\" > "
				+"<input class=\"easyui-combobox\" "
				+"panelHeight=\"auto\" name=\""+name+"\" id=\""+id+"\" >");
		return sb;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getListWidth() {
		return listWidth;
	}
	public void setListWidth(Integer listWidth) {
		this.listWidth = listWidth;
	}
	public Integer getListHeight() {
		return listHeight;
	}
	public void setListHeight(Integer listHeight) {
		this.listHeight = listHeight;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public boolean isInitNullSelect() {
		return initNullSelect;
	}
	public void setInitNullSelect(boolean initNullSelect) {
		this.initNullSelect = initNullSelect;
	}
	public String getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	public String getUrl() {
		return url;
	}
	public String getName() {
		return name;
	}
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	
}

