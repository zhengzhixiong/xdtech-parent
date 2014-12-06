package com.xdtech.${moduleName}.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.${moduleName}.service.${modelName?cap_first}Service;
import com.xdtech.${moduleName}.vo.${modelName?cap_first}Item;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

/**
 * 
 * @author max.zheng
 * @create ${createTime}
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/${modelName}.do")
public class ${modelName?cap_first}Controller {
	@Autowired
	private ${modelName?cap_first}Service ${modelName}Service;
	@RequestMapping(params = "${modelName}")
	public ModelAndView skip${modelName?cap_first}() {
		return new ModelAndView("${moduleName}/${modelName}/${modelName}_ftl");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public Map<String, Object> loadList(HttpServletRequest request,Pagination pg) {
		Map<String, Object> results = null;
		if (StringUtils.isEmpty(request.getParameter("page"))) {
			//不分页处理
			results = ${modelName}Service.loadPageAndCondition(null, null);
		}else {
			results = ${modelName}Service.loadPageAndCondition(pg, null);
		}
		
		return results;
	}
	
	@RequestMapping(params = "edit${modelName?cap_first}")
	public ModelAndView edit${modelName?cap_first}(HttpServletRequest request,Long ${modelName}Id) {
		if (${modelName}Id!=null) {
			request.setAttribute("${modelName}Item", ${modelName}Service.load${modelName?cap_first}Item(${modelName}Id));
		}
		return new ModelAndView("${moduleName}/${modelName}/edit${modelName?cap_first}_ftl");
	}
	
	@RequestMapping(params = "save${modelName?cap_first}")
	@ResponseBody
	public ResultMessage save${modelName?cap_first}(${modelName?cap_first}Item item) {
		ResultMessage r = new ResultMessage();
		if (${modelName}Service.saveOrUpdate${modelName?cap_first}(item)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "delete${modelName?cap_first}Items")
	@ResponseBody
	public ResultMessage delete${modelName?cap_first}Items(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> ${modelName}Ids = new ArrayList<Long>();
			for (String id : tempIds) {
				${modelName}Ids.add(Long.valueOf(id));
			}
			${modelName}Service.delete${modelName?cap_first}Info(${modelName}Ids);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}

}
