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
package com.xdtech.coder.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.coder.CodeCreater;
import com.xdtech.coder.Coder;
import com.xdtech.coder.CoderField;
import com.xdtech.common.utils.JsonUtil;
import com.xdtech.web.model.ResultMessage;

/**
 * 
 * @author max.zheng
 * @create 2014-10-30下午10:22:46
 * @since 1.0
 * @see
 */
@Component
@RequestMapping("/codeCreater.do")
public class CodeCreaterController {
	@RequestMapping(params = "codeCreater")
	public ModelAndView skipCodeCreater() {
		return new ModelAndView("codeCreater/codeCreater_ftl");
	}
	@RequestMapping(params = "createCode")
	@ResponseBody
	public ResultMessage createCode(String data) {
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("fields", CoderField.class);
		Coder coder = (Coder) JsonUtil.getObjByJson(data, Coder.class,classMap);
//		System.out.println(coder);
		CodeCreater.create(coder);
		return new ResultMessage();
	}
}
