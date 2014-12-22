package com.xdtech.${moduleName}.vo;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import com.xdtech.web.freemark.item.GridColumn;

/**
 * 
 * @author max.zheng
 * @create ${createTime}
 * @since 1.0
 * @see
 */
public class ${modelName?cap_first}Item implements Serializable{
	private static final long serialVersionUID = 1L;
<#list fields as field>
	<#if field.name='id'>
	<#else>
	@GridColumn(title="${field.showName}",width=100)
	</#if>
	private ${field.type} ${field.name};
</#list>

<#list fields as field>
	<#if field.type='Date'>
	public void set${field.name?cap_first}(String ${field.name}) {
		this.${field.name} = ${field.name};
	}
	public String get${field.name?cap_first}() {
		return ${field.name};
	}
	<#else>
	public void set${field.name?cap_first}(${field.type} ${field.name}) {
		this.${field.name} = ${field.name};
	}
	public ${field.type} get${field.name?cap_first}() {
		return ${field.name};
	}
	</#if>
	
</#list>
}
