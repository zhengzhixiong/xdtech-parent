package com.xdtech.common.service;

import java.util.List;
import java.util.Map;

import com.xdtech.core.model.BaseModel;
import com.xdtech.web.model.Pagination;

public interface IBaseService<T extends BaseModel> {

	public void save(final T entity);

	public void delete(final T entity);

	public void delete(final Long id);

	public T get(final Long id);

	public List<T> getAll();

	public Map<String, Object> loadPageAndCondition(Pagination pg,
			final Map<String, String> values);
	
//	public void executeUpdateBySql(String sql);
//	
//	public void executeSql(String sql,Object... values);
	
//	public Map<String, Object> loadPageCondition(Pagination pg,
//			final BaseCondition condition);

}
