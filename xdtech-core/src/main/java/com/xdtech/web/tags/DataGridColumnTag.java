package com.xdtech.web.tags;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;


public class DataGridColumnTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private String field;
	private Integer width;
	private String align;
	
	private String fieldName;
	
	private String editor;
	
	private String styler;
	
	private boolean sortable = true;
	
	private boolean hidden;
	
	private String formatter;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public int doEndTag() throws JspTagException {
		Tag t = findAncestorWithClass(this, DataGridTag.class);
		DataGridTag parent = (DataGridTag) t;
		parent.addColumn(this);
//		parent.addColumn(this);
		return EVAL_PAGE;
	}
	public String getStyler() {
		return styler;
	}
	public void setStyler(String styler) {
		this.styler = styler;
	}
	public boolean isSortable() {
		return sortable;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	
	

}
