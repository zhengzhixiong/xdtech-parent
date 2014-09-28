package com.xdtech.web.freemark.item;

import com.xdtech.common.utils.JsonUtil;

public class OperationItem {
	private String onClick;
	private String src;
	private String title;
	private String shiro;
	public String getOnClick() {
		return onClick;
	}
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getShiro() {
		return shiro;
	}
	public void setShiro(String shiro) {
		this.shiro = shiro;
	}
	public static void main(String[] args) {
		String array = "[{onclick:\"onclick\",src:\"src\",title:\"qwe\"}]";
		System.out.println(JsonUtil.getDTOList(array, OperationItem.class));
	}
	
}
