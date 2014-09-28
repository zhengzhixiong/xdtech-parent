package com.xdtech.web.freemark.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xdtech.common.fomat.TreeFormat;

public class MenuButtonItem implements Serializable,TreeFormat<MenuButtonItem, Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String icon;
	
	private String url;
	
	private String code;
	
	private Boolean iframe;
	
	private MenuButtonItem parent;
	
	private List<MenuButtonItem> children = new ArrayList<MenuButtonItem>();
	
	

	public MenuButtonItem() {
		super();
	}
	
	public MenuButtonItem(Long id) {
		super();
		this.id = id;
	}




	public MenuButtonItem(Long id, String name, String icon, String url,
			String code, MenuButtonItem parent) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.url = url;
		this.code = code;
		this.parent = parent;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public MenuButtonItem getParent() {
		return parent;
	}

	public void setParent(MenuButtonItem parent) {
		this.parent = parent;
	}

	public List<MenuButtonItem> getChildren() {
		return children;
	}

	public void setChildren(List<MenuButtonItem> children) {
		this.children = children;
	}

	public void addChildren(MenuButtonItem child) {
		if(this.children == null) {
            this.children = new ArrayList<MenuButtonItem>();
        }
        this.children.add(child);
	}

	public Boolean getIframe() {
		return iframe;
	}

	public void setIframe(Boolean iframe) {
		this.iframe = iframe;
	}

	
	
	
	
}
