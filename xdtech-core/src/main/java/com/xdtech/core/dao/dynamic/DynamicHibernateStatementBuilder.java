package com.xdtech.core.dao.dynamic;

import java.io.IOException;
import java.util.Map;

/**
 * 动态sql/hql语句组装器
 * @author WangXuzheng
 *
 */
public interface DynamicHibernateStatementBuilder {
	/**
	 * hql语句map
	 * @return
	 */
	public Map<String,String> getNamedHQLQueries();
	/**
	 * sql语句map
	 * @return
	 */
	public Map<String,String> getNamedSQLQueries();
	/**
	 * 初始化
	 * @throws IOException 
	 */
	public void init() throws IOException;
}
