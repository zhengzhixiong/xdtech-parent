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
package com.xdtech.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xdtech.common.utils.ApplicationContextUtil;

/**
 * 
 * @author max.zheng
 * @create 2014-10-12下午8:55:05
 * @since 1.0
 * @see
 */
public class MemoryListener implements ServletContextListener{

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-12下午8:55:17
	 * @modified by
	 * @param sce
	 */
	public void contextInitialized(ServletContextEvent contextEvent) {
		System.out.println("应用监听器启动......");
//		ApplicationContextUtil.setApplication(contextEvent.getServletContext());
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-10-12下午8:55:17
	 * @modified by
	 * @param sce
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
