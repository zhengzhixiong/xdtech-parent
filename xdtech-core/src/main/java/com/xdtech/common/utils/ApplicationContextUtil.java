package com.xdtech.common.utils;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author zzx
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;
	
//	private static ServletContext application;

	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		ApplicationContextUtil.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}

//	public static ServletContext getApplication() {
//		return application;
//	}
//
//	public static void setApplication(ServletContext application) {
//		ApplicationContextUtil.application = application;
//	}
	
	
}
