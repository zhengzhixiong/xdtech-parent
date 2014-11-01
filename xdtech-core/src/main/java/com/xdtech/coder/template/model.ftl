package com.xdtech.${moduleName}.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;

/**
 * 
 * @author max.zheng
 * @create ${createTime}
 * @since 1.0
 * @see 
 */
@Entity
@Table(name="${tableName}")
public class ${modelName?cap_first} extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;
<#list fields as field>
	<#if field.name='id'>
	@Id
	@Column(name = "${field.tableFieldName}")
	@GeneratedValue(strategy = GenerationType.AUTO)
	<#else>
	@Column(name="${field.tableFieldName}")
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
