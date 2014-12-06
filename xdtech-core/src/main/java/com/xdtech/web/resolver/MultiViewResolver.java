package com.xdtech.web.resolver;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class MultiViewResolver implements ViewResolver {

	private Map<String, ViewResolver> resolvers;

	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		if (viewName.contains("jsp") || viewName.contains("ftl")||viewName.contains("jrxml")) {
			int n = viewName.lastIndexOf("_"); // 获取
			// viewName(modelAndView中的名字)看其有没有下划线
			if (n == (-1)) {
				return null; // 没有则直接返回空
			}

			// 有的话截取下划线后面的字符串 这里一般是jsp,ftl,vm与配置文件中的<entry key="ftl">的key匹配
			String suffix = viewName.substring(n + 1);
			// 根据下划线后面的字符串去获取托管的视图解析类对象
			ViewResolver resolver = resolvers.get(suffix);

			// 取下划线前面的部分 那时真正的资源名.比如我们要使用hello.jsp 那viewName就应该是hello_jsp
			viewName = viewName.substring(0, n);
			if (resolver != null)
				return resolver.resolveViewName(viewName, locale);
		} else {
			// 默认采用jsp视图解析
			ViewResolver resolver = resolvers.get("jsp");
			return resolver.resolveViewName(viewName, locale);
		}

		return null;
	}

	public Map<String, ViewResolver> getResolvers() {
		return resolvers;
	}

	public void setResolvers(Map<String, ViewResolver> resolvers) {
		this.resolvers = resolvers;
	}
}

