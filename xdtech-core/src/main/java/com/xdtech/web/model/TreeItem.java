package com.xdtech.web.model;

import java.util.ArrayList;
import java.util.List;

import com.xdtech.common.fomat.TreeFormat;

public class TreeItem implements TreeFormat<TreeItem, Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String text;
	private TreeItem parent;
	private boolean checked =false;
	private String state = "open";// 是否展开(open,closed)
	private List<TreeItem> children = new ArrayList<TreeItem>();
	//数节点附加属性
	private Object attributes;
	
	private String content="yellow";
	
	
	public TreeItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TreeItem(Long id) {
		super();
		this.id = id;
	}
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
	public List<TreeItem> getChildren() {
		return children;
	}
	public void setChildren(List<TreeItem> children) {
		this.children = children;
	}
	public TreeItem getParent() {
		// TODO Auto-generated method stub
		return parent;
	}
	public void addChildren(TreeItem child) {
		if(this.children == null) {
            this.children = new ArrayList<TreeItem>();
        }
        this.children.add(child);
	}
	public void setParent(TreeItem parent) {
		this.parent = parent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Object getAttributes() {
		return attributes;
	}
	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	
	
	
}
