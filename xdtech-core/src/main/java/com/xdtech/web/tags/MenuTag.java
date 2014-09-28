package com.xdtech.web.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.web.model.Menu;

public class MenuTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private List<Menu> menus;
	private List<String> subMenuList;
	
	public int doStartTag() throws JspTagException {
		subMenuList = new ArrayList<String>();
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}


	private String isNeedSubMenusLink(Menu parent,List<Menu> subMenus) {
		if (EmptyUtil.isNotEmpty(subMenus)) {
			return "menu:'#mm"+parent.getId()+"',";
		}else {
			return "";
		}
	}
	public String end() {	
		StringBuffer sb = new StringBuffer();
		sb.append("<div style=\"width:100%;\">");
		if(EmptyUtil.isNotEmpty(menus)) {
			for (Menu menu : menus) {
				sb.append("<a href=\"javascript:void(0)\"  id=\"mb"+menu.getId()+"\" class=\"easyui-menubutton\" data-options=\""+isNeedSubMenusLink(menu, menu.getChildren())+"iconCls:'"+menu.getIcon()+"'\">"+menu.getName()+"</a>");
				createSubMenus(menu,menu.getChildren());
			}
		}
		
		sb.append("</div>");
		//添加子菜单
	    for (String str : subMenuList) {
			sb.append(str);
		}
		return sb.toString();
	}

	
	
	private void createSubMenus(Menu parent,List<Menu> children) {
		// TODO Auto-generated method stub
		
		if (EmptyUtil.isNotEmpty(children)) {
			StringBuffer subSb = new StringBuffer();
			subSb.append("<div id=\"mm"+parent.getId()+"\" style=\"width:150px;\">");
			for (Menu subMenu : children) {
				if (EmptyUtil.isEmpty(subMenu.getChildren())) {
					subSb.append("<div onclick=\"addTab(\'"+subMenu.getName()+"\',\'"+subMenu.getUrl()+"&clickFunctionId="+subMenu.getId()+"\',\'"+subMenu.getIcon()+"\')\"  title=\""+subMenu.getName()+"\" url=\""+subMenu.getUrl()+"\" data-options=\"iconCls:'"+subMenu.getIcon()+"'\">"+subMenu.getName()+"</div> ");
				}else {
					createSub2Menus(subSb,subMenu.getChildren(),subMenu);
				}
				
//		        <div data-options="iconCls:'icon-undo'">Undo</div>  
//		        <div data-options="iconCls:'icon-redo'">Redo</div>  
//		        <div class="menu-sep"></div>  
//		        <div>Cut</div>  
//		        <div>Copy</div>  
//		        <div>Paste</div>  
//		        <div class="menu-sep"></div>  
//		        <div>  
//		            <span>Toolbar</span>  
//		            <div style="width:150px;">  
//		                <div>Address</div>  
//		                <div>Link</div>  
//		                <div>Navigation Toolbar</div>  
//		                <div>Bookmark Toolbar</div>  
//		                <div class="menu-sep"></div>  
//		                <div>New Toolbar...</div>  
//		            </div>  
//		        </div>  
//		        <div data-options="iconCls:'icon-remove'">Delete</div>  
//		        <div>Select All</div>  
		      
			}
			subSb.append("</div>");
			subMenuList.add(subSb.toString());
		}
	}

	private void createSub2Menus(StringBuffer subSb, List<Menu> children,Menu subParent) {
		subSb.append("<div data-options=\"iconCls:'"+subParent.getIcon()+"'\">");
		subSb.append("<span>"+subParent.getName()+"</span>");
		subSb.append("<div style=\"width:150px;\">");
		for (Menu sub2Menu : children) {
			if (EmptyUtil.isEmpty(sub2Menu.getChildren())) {
				subSb.append("<div data-options=\"iconCls:'"+sub2Menu.getIcon()+"'\">"+sub2Menu.getName()+"</div> ");
			}else {
				createSub2Menus(subSb, sub2Menu.getChildren(), sub2Menu);
			}
		}
		subSb.append("</div></div>");
		
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	

}
