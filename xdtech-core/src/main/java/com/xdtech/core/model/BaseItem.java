package com.xdtech.core.model;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseItem {
	protected Map<String, String> queryFields = new HashMap<String, String>();
	
//	protected Map<String, String> queryConditions = new HashMap<String, String>();

	public Map<String, String> getQueryFields() {
		return queryFields;
	}

	public void setQueryFields(Map<String, String> queryFields) {
		this.queryFields = queryFields;
	}

//	public Map<String, String> getQueryConditions() {
//		return queryConditions;
//	}
//
//	public void setQueryConditions(Map<String, String> queryConditions) {
//		this.queryConditions = queryConditions;
//	}
	
	
	
	
}
