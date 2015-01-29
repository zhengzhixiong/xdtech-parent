/*
 * Project Name: xdtech-core
 * File Name: ModelField.java
 * Copyright: Copyright(C) 1985-2015 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.coder.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xdtech.core.model.BaseModel;

/**
 * TODO 一句话功能简述，请确保和下面的block tags之间保留一行空行
 * <p>
 * TODO 功能详细描述，若不需要请连同上面的p标签一起删除
 * 
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version TODO 添加版本
 * @see 相关类或方法，不需要请删除此行
 * @since 2015-1-24 上午10:46:37
 */
@Entity
@Table(name="CODER_MODEL_FIELD")
public class ModelField extends BaseModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "FIELD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//属性名称
	@Column(name="FIELD_NAME")
	private String name;
	//属性类型
	@Column(name="FIELD_TYPE")
	private String type;
	//属性长度
	@Column(name="LENGTH")
	private String length;
	//备注
	@Column(name="COMMENT")
	private String comment;
	
	//属性对应的表字段
	@Column(name="TABLE_FIELD_NAME")
	private String tableFieldName;
	
	//默认值
	@Column(name="DEFAULT_VALUE")
	private String defaultValue;
	//表单元素是否必填
	@Column(name="REQUIRED")
	private boolean required;
	//是否是表单元素
	@Column(name="FORM_FIELD")
	private boolean formField;
	//表单显示label
	@Column(name="SHOW_NAME")
	private String showName;
	
	@ManyToOne
	@JoinColumn(name="TABLE_ID")
	private ModelTable modelTable;
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getLength()
	{
		return length;
	}
	public void setLength(String length)
	{
		this.length = length;
	}
	public String getDefaultValue()
	{
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}
	public boolean isFormField()
	{
		return formField;
	}
	public void setFormField(boolean formField)
	{
		this.formField = formField;
	}
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	public String getTableFieldName()
	{
		return tableFieldName;
	}
	public void setTableFieldName(String tableFieldName)
	{
		this.tableFieldName = tableFieldName;
	}
	public boolean isRequired()
	{
		return required;
	}
	public void setRequired(boolean required)
	{
		this.required = required;
	}
	public String getShowName()
	{
		return showName;
	}
	public void setShowName(String showName)
	{
		this.showName = showName;
	}
	public ModelTable getModelTable()
	{
		return modelTable;
	}
	public void setModelTable(ModelTable modelTable)
	{
		this.modelTable = modelTable;
	}
	
	
}
