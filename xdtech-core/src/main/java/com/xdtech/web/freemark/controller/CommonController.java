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
package com.xdtech.web.freemark.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdtech.core.init.InitCacheData;

/**
 * 
 * @author max.zheng
 * @create 2014-10-12下午8:30:15
 * @since 1.0
 * @see
 */
@Component
@RequestMapping("/common.do")
public class CommonController {
	
	@RequestMapping(params = "loadComboBox")
	@ResponseBody
	public Object loadComboBox(String key) {
		return InitCacheData.dictionary.get(key);
	}

}
