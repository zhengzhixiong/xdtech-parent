package com.xdtech.web.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.web.model.TreeItem;

public class TreeTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private List<TreeItem> items;
	
	public int doStartTag() throws JspTagException {
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

	
	public String end() {	
		StringBuffer sb = new StringBuffer();
		sb.append("<ul class=\"easyui-tree\" data-options=\"animate:true,lines:true,onclick:clickUserGroupItem\"><li><span>Root</span>");
		if(EmptyUtil.isNotEmpty(items)) {
			sb.append("<ul>");
			for (TreeItem item : items) {
				
				sb.append(createSubItems(item,item.getChildren()));
			}
			sb.append("</ul>");
		}
		
		sb.append("</li></ul>");
		return sb.toString();
	}

	
	
	private String createSubItems(TreeItem parent,List<TreeItem> children) {
		// TODO Auto-generated method stub
		StringBuffer subSb = new StringBuffer();
		subSb.append("<li><span>"+parent.getText()+"</span>");
		if (EmptyUtil.isNotEmpty(children)) {
			subSb.append("<ul>");
			for (TreeItem item : children) {
				subSb.append(createSubItems(item, item.getChildren()));
//				<li data-options="state:'closed'">
//	                <span>Photos</span>
//	                <ul>
//	                    <li>
//	                        <span>Friend</span>
//	                    </li>
//	                    <li>
//	                        <span>Wife</span>
//	                    </li>
//	                    <li>
//	                        <span>Company</span>
//	                    </li>
//	                </ul>
//	            </li>
		      
			}
			subSb.append("</ul>");
		}
		subSb.append("</li>");
		return subSb.toString();
	}

	public List<TreeItem> getItems() {
		return items;
	}

	public void setItems(List<TreeItem> items) {
		this.items = items;
	}

	
	
	

}
