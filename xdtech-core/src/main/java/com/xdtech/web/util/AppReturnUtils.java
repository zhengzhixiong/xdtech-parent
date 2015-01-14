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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * 
 * @author max.zheng
 * @create 2015-1-10下午5:21:26
 * @since 1.0
 * @see
 */
public class AppReturnUtils {
	
	public static void returnJsonpAsk(HttpServletRequest request,HttpServletResponse response,Object data) {
		try {  
		  System.out.println(request.getSession().getId());
          response.setContentType("text/plain");  
          response.setHeader("Pragma", "No-cache"); 
          response.setCharacterEncoding("UTF-8");
          response.setHeader("Cache-Control", "no-cache");  
          response.setDateHeader("Expires", 0);  
          Map<String,String> map = new HashMap<String,String>();   
          map.put("result", "content");  
          PrintWriter out = response.getWriter();       
          JSONObject resultJSON = JSONObject.fromObject(data); //根据需要拼装json  
          String jsonpCallback = request.getParameter("callback");//客户端请求参数  
          out.println(jsonpCallback+"("+resultJSON.toString(1,1)+")");//返回jsonp格式数据  
          out.flush();  
          out.close();  
        } catch (IOException e) {  
         e.printStackTrace();  
        }  

	}
}
