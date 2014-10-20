/*
 * Copyright 2013-2014 the original author or authors.
 *
 */
package com.xdtech.core.init;

import com.xdtech.common.service.BaseService;
import com.xdtech.core.model.BaseModel;

/**
 * 提供系统相关需要初始化接口类，继承该类会自动进行相关初始化动作
 * @author max.zheng
 * @create 2014-9-25下午9:31:49
 */
public interface SysInitOperation {
	public static String INIT_TO_DB_METHOD = "initingToDb";
	
	public static String INIT_TO_CACHE_METHOD = "initingToCache";
	/**
	 * 初始化数据到数据库方法
	 * @author max.zheng
	 * @create 2014-9-25下午9:38:24
	 * @modified by
	 */
	public void initingToDb(BaseService<BaseModel> baseService);
	/**
	 * 初始化数据到缓存方法
	 * @author max.zheng
	 * @create 2014-9-25下午9:38:24
	 * @modified by
	 */
	public void initingToCache(BaseService<BaseModel> baseService);

}
