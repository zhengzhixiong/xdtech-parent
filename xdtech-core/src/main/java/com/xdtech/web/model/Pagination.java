package com.xdtech.web.model;

import com.xdtech.core.orm.PageRequest;

public class Pagination extends PageRequest{
	//下一页
	private boolean next;
	//上一页
	private boolean previous;
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public boolean isPrevious() {
		return previous;
	}
	public void setPrevious(boolean previous) {
		this.previous = previous;
	}
	
	
}
