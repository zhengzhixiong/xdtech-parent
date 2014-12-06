package com.xdtech.web.freemark.tags;

import java.io.IOException;
import java.io.Writer;

import freemarker.core.Environment;

public abstract class EasyUiTag extends SecureTag{
	
	/**
	 * 
	 * @description 传入标签属性，判断属性值是否为空，为空返回"",不为空情况，返回，如：title：'titlevalue',
	 * @author max.zheng
	 * @create 2014-9-14下午8:46:04
	 * @modified by
	 * @param propName     属性名称
	 * @param propValue  属性值
	 * @param hasColon   是否需要包含冒号
	 * @return
	 */
	protected String addProperties(String propName, Object propValue,boolean hasColon) {
		StringBuffer rs = new StringBuffer("");
		if (propValue!=null) {
			rs.append(propName+":");
			if (hasColon) {
				rs.append("'"+propValue+"',");
			}else {
				rs.append(propValue+",");
			}
		}
		return rs.toString();
	}
	/**
	 * 
	 * 
	 * @author max.zheng
	 * @create 2014-11-30上午10:31:19
	 * @modified by
	 * @param propName
	 * @param propValue
	 * @param defaultValue
	 * @param hasColon
	 * @return
	 */
	protected String addProperties(String propName, Object propValue,Object defaultValue,boolean hasColon) {
		StringBuffer rs = new StringBuffer("");
		if (propValue==null||"".equals(propValue)) {
			propValue = defaultValue;
		}
		if (propValue!=null) {
			rs.append(propName+":");
			if (hasColon) {
				rs.append("'"+propValue+"',");
			}else {
				rs.append(propValue+",");
			}
		}
		return rs.toString();
	}
	
	protected void writeBody(Environment env,String content) throws IOException {
        if (env != null) {
        	// 真正开始处理输出内容
			Writer out = env.getOut();
			out.write(content);
			out.flush();
        }
    }
}
