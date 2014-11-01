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

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author max.zheng
 * @create 2014-11-1下午7:17:22
 * @since 1.0
 * @see
 */
public class Coder {

	private String moduleName;
	
	private String modelName;
	
	private String tableName;
	
	private String filePath;
	
	private List<CoderField> fields = new ArrayList<CoderField>();

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<CoderField> getFields() {
		return fields;
	}

	public void setFields(List<CoderField> fields) {
		this.fields = fields;
	}
	
	
}
