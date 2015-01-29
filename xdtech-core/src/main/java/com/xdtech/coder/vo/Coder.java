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
package com.xdtech.coder.vo;

import java.util.ArrayList;
import java.util.List;

import com.xdtech.coder.model.ModelField;
import com.xdtech.coder.model.ModelTable;

/**
 * 
 * @author max.zheng
 * @create 2014-11-1下午7:17:22
 * @since 1.0
 * @see
 */
public class Coder {

	private ModelTable model = new ModelTable();
	
	private List<ModelField> fields = new ArrayList<ModelField>();

	public ModelTable getModel()
	{
		return model;
	}

	public void setModel(ModelTable model)
	{
		this.model = model;
	}

	public List<ModelField> getFields() {
		return fields;
	}

	public void setFields(List<ModelField> fields) {
		this.fields = fields;
	}
	
	
}
