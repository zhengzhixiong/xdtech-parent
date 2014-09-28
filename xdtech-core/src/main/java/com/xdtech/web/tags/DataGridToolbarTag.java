package com.xdtech.web.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.xdtech.common.utils.EmptyUtil;
import com.xdtech.web.model.ToolbarButton;

public class DataGridToolbarTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private String id;
	private List<ToolbarButton> buttons = new ArrayList<ToolbarButton>(); 
	private String style;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setButton(String iconCls,String labelName,String onclick,String operationCode) {
		ToolbarButton button = new ToolbarButton();
		button.setIconCls(iconCls);
		button.setLabelName(labelName);
		button.setOperationCode(operationCode);
		button.setOnclick(onclick);
		buttons.add(button);
	}
	
	public int doStartTag() throws JspTagException {
		// 清空资源
		buttons.clear();
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
		sb.append("<div id="+id);
		if (StringUtils.isBlank(style)) {
			sb.append(" style=\"height:auto\">");
		}else {
			sb.append(" style=\"height:auto;"+style+"\">");
		}
		if (EmptyUtil.isNotEmpty(buttons)) {
			for (ToolbarButton button : buttons) {
				sb.append(" <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" data-options=\"iconCls:'"+button.getIconCls()+"',plain:true\" onclick=\""+button.getOnclick()+"\">"+button.getLabelName()+"</a>");
			}
		}
		sb.append("</div>");
		return sb.toString();
	}
}
