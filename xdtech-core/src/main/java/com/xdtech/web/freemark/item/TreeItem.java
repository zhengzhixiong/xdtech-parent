package com.xdtech.web.freemark.item;

import java.util.ArrayList;
import java.util.List;

public class TreeItem {
	private Long id;
	
	private String text;
	
	private String iconCls;
	
	private String state;
	
	private TreeItem parent;
	
	private List<TreeItem> children = new ArrayList<TreeItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public TreeItem getParent() {
		return parent;
	}

	public void setParent(TreeItem parent) {
		this.parent = parent;
	}

	public List<TreeItem> getChildren() {
		return children;
	}

	public void setChildren(List<TreeItem> children) {
		this.children = children;
	}
}
