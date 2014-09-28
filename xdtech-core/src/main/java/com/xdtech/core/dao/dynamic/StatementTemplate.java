package com.xdtech.core.dao.dynamic;

import freemarker.template.Template;

public class StatementTemplate {
	public static String HQL = "HQL";
	public static String SQL = "SQL";
	
	private String sqlType;
	
	private Template template;

	public StatementTemplate(String sqlType, Template template) {
		super();
		this.sqlType = sqlType;
		this.template = template;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
	
	
}
