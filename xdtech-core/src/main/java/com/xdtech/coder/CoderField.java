/*
 * Copyright 2013-2014 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xdtech.coder;

import com.xdtech.web.freemark.item.GridColumn;

/**
 * 
 * @author max.zheng
 * @create 2014-10-26下午9:21:30
 * @since 1.0
 * @see
 */
public class CoderField {
//	@GridColumn(title="主题")
	private String beanName;
	@GridColumn(title="属性类型")
	private String type;
	@GridColumn(title="属性名称")
	private String name;
	@GridColumn(title="属性对应表字段名称")
	private String tableFieldName;
	//是否表单元素
	@GridColumn(title="是否表单元素")
	private boolean formField;
	//显示名称
	@GridColumn(title="显示名称")
	private String showName;
	//是否必填
	@GridColumn(title="是否必填")
	private boolean required;
	
	
	
	public CoderField() {
		super();
	}
	public CoderField(String beanName, String type, String name,
			String tableFieldName,String showName,boolean formField,boolean required) {
		super();
		this.beanName = beanName;
		this.type = type;
		this.name = name;
		this.tableFieldName = tableFieldName;
		this.showName = showName;
		this.formField = formField;
		this.required = required;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTableFieldName() {
		return tableFieldName;
	}
	public void setTableFieldName(String tableFieldName) {
		this.tableFieldName = tableFieldName;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public boolean isFormField() {
		return formField;
	}
	public void setFormField(boolean formField) {
		this.formField = formField;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	
	
	
	
}
