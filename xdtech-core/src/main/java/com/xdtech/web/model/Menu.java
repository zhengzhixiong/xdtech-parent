package com.xdtech.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xdtech.common.fomat.TreeFormat;

public class Menu implements Serializable,TreeFormat<Menu, Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String icon;
	
	private String url;
	
	private String code;
	
	private Menu parent;
	
	private List<Menu> children = new ArrayList<Menu>();
	
	

	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Menu(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void addChildren(Menu child) {
		if(this.children == null) {
            this.children = new ArrayList<Menu>();
        }
        this.children.add(child);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	} 
	
	
	
	
}
