package com.xdtech.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.model.BaseModel;
import com.xdtech.web.model.Pagination;
@Service
@Transactional
public class BaseService<T extends BaseModel>  {
	
	protected BaseDao<T> baseDao;
	
	public void save(final T entity) {
		baseDao.save(entity);
	}
	
	public void delete(final T entity) {
		baseDao.delete(entity);
	}
	
	public void delete(final Long id) {
		baseDao.delete(id);
	}
	
	public T get(final Long id) {
		return baseDao.get(id);
	}
	
	public List<T> getAll() {
		return baseDao.getAll();
	}
	
	public List<T> getAll(Class<?> cls) {
		return baseDao.findByClass(cls);
	}

	public BaseDao<T> getBaseDao() {
		return baseDao;
	}
	@Autowired
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	public Map<String, Object> loadPageAndCondition(Pagination pg,final Map<String,String> values) {
		return new HashMap<String, Object>();
	}
	
}
