package com.xdtech.web.model;

import java.util.Map;

/**
 * $.ajax后需要接受的JSON
 * 
 * @author
 * 
 */
public class ResultMessage {

	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object obj = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数
	
	
	
	public ResultMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ResultMessage(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}


	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		if (success) {
			this.msg = "操作成功";
		}else {
			this.msg = "操作失败";
		}
		this.success = success;
	}
	
	public static ResultMessage getFailMessage() {
		return new ResultMessage(false,"操作失败");
	}

}
