package com.xdtech.core.listener;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.xdtech.common.service.impl.BaseService;
import com.xdtech.common.utils.ClassUtil;
import com.xdtech.core.config.PropertiesConfigurer;
import com.xdtech.core.init.SysInitOperation;
import com.xdtech.core.model.BaseModel;
/**
 * spring容器启动加载配置文件完成监听器
 * 
 * @author max.zheng
 * @create 2014-9-25下午9:36:10
 * @since 1.0
 * @see
 */
@SuppressWarnings("rawtypes")
@Component
public class SysInitListener implements ApplicationListener {
	@Autowired
	private BaseService<BaseModel> baseService;
	
	
	private Logger log = Logger.getLogger(getClass());

	public void onApplicationEvent(ApplicationEvent event) {
		String eventSource = event.getSource().toString();
		if (eventSource.startsWith("Root WebApplicationContext:")) {
//			Root WebApplicationContext: startup date [Thu Sep 25 21:17:58 CST 2014]; root of context hierarchy
			System.out.println("spring 主配置文件加载完毕");
			if (PropertiesConfigurer.sysIsInitDataToDb()) {
				initSysData(SysInitOperation.INIT_TO_DB_METHOD);
				PropertiesConfigurer.setAlreadyInitDataToDb();
			}
			if (PropertiesConfigurer.sysIsInitDataToCache()) {
				initSysData(SysInitOperation.INIT_TO_CACHE_METHOD);
			}
		}
		else if (eventSource.startsWith("WebApplicationContext for namespace")) {
//			WebApplicationContext for namespace 'springmvc-servlet': startup date [Thu Sep 25 21:18:02 CST 2014]; parent: Root WebApplicationContext
			System.out.println("spring mvc 配置文件加载完毕");
		}
	}

	private void initSysData(String method) {
		System.out.println("系统初始化数据....."+method);
		Object initObject = null;
		Method convertMethod = null;
		for (Class<?> c : ClassUtil.getClasses("com.xdtech")) {
			if (SysInitOperation.class.isAssignableFrom(c)&&!SysInitOperation.class.equals(c)) {
				System.out.println(c.getName());
				try {
					initObject = c.newInstance();
					convertMethod = c.getMethod(method
							,BaseService.class);
					convertMethod.invoke(initObject,baseService);
				} catch (Exception e) {
					log.error("system init data error",e);
				}
			}
		}
	}

}
