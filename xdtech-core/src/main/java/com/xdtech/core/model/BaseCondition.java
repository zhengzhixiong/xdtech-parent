package com.xdtech.core.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public abstract class BaseCondition {
	protected boolean isItem = false;
	
	protected BaseItem baseItem;
	
	protected Map<String, String> queryConditions = new HashMap<String, String>();

	public boolean isItem() {
		return isItem;
	}

	public void setItem(boolean isItem) {
		this.isItem = isItem;
	}

	public BaseItem getBaseItem() {
		return baseItem;
	}

	public void setBaseItem(BaseItem baseItem) {
		this.isItem = true;
		this.baseItem = baseItem;
	}

	public Map<String, String> getQueryConditions() {
		return queryConditions;
	}

	public void setQueryConditions(Map<String, String> queryConditions) {
		this.queryConditions = queryConditions;
	}
	
	protected void addToMap(String key,String value) {
		if (StringUtils.isNotEmpty(value)) {
			queryConditions.put(key, value);
		}
		
	}
	
}
