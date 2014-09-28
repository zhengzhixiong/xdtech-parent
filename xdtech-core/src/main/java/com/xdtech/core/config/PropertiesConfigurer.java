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
package com.xdtech.core.config;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * @author max.zheng
 * @create 2014-9-28下午9:08:19
 * @since 1.0
 * @see
 */
public class PropertiesConfigurer extends PropertyPlaceholderConfigurer {
	private static Properties props = null;

	/**
	 * 根据applicationContext.xml启动时，加载配置信息
	 */
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		PropertiesConfigurer.props = props;
	}

	/**
	 * @Description: 读取Spring配置文件里所有properties文件里的value值
	 * @Param: key 要设置属性的key
	 * @Return: value 要设置属性的值
	 */
	public static String getValue(String key) {
		return props.getProperty(key);
	}

	public static boolean sysIsInitData() {
		String isInitData = props.getProperty("system.isInitData");
		return isInitData != null ? Boolean.valueOf(isInitData) : false;
	}

}
