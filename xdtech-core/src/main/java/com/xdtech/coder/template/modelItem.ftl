package com.xdtech.${moduleName}.vo;

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
	@GridColumn(title="${field.showName}")
	</#if>
	private ${field.type} ${field.name};
</#list>

<#list fields as field>
	public void set${field.name?cap_first}(${field.type} ${field.name}) {
		this.${field.name} = ${field.name};
	}
	public ${field.type} get${field.name?cap_first}() {
		return ${field.name};
	}
</#list>
}
