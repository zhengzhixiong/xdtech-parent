/*
 * Project Name: xdtech-core
 * File Name: ModelTabel.java
 * Copyright: Copyright(C) 1985-2015 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.coder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
 * @since 2015-1-24 上午10:54:15
 */
@Entity
@Table(name="CODER_MODEL_TABLE")
public class ModelTable extends BaseModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "TABLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	@Column(name="TABLE_NAME")
	private String tableName;
	@Column(name="MODULE_NAME")
	private String moduleName;
	@Column(name="MODEL_NAME")
	private String modelName;
	@Column(name="COMMENT")
	private String comment;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="modelTable")
	List<ModelField> modelFields = new ArrayList<ModelField>();

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public String getModelName()
	{
		return modelName;
	}

	public void setModelName(String modelName)
	{
		this.modelName = modelName;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public List<ModelField> getModelFields()
	{
		return modelFields;
	}

	public void setModelFields(List<ModelField> modelFields)
	{
		this.modelFields = modelFields;
	}
	
	
}
