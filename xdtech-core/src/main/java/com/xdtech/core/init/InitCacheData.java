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
package com.xdtech.core.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author max.zheng
 * @create 2014-10-12下午9:45:09
 * @since 1.0
 * @see
 */
public class InitCacheData {
	public static Map<String, List> dictionary = new HashMap<String, List>();

	public static Map<String, List> getDictionary() {
		return dictionary;
	}

	public static void setDictionary(Map<String, List> dictionary) {
		InitCacheData.dictionary = dictionary;
	}
	
	public static List getDictionaryList(String key) {
		return dictionary.get(key);
	}

}
