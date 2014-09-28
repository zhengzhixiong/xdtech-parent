package com.xdtech.web.freemark.tags;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.xdtech.web.freemark.item.MenuButtonItem;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

public class MenuButtonTag extends EasyUiTag{
	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		List<MenuButtonItem> menuList = (List<MenuButtonItem>) params.get("menus");
		
	}

}
