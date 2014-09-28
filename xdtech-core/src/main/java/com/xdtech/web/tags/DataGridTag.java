package com.xdtech.web.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.web.model.DataGridColumn;

public class DataGridTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	private String id;
	//标题
	private String title;
	
	private String toolbar;
	private boolean nowrap = false;
	private boolean rownumbers = true;
	private String sortName;
	private String sortOrder = "asc";
	private String loadMsg="加载中,请稍候...";
	private boolean pagination = true;
	//是否列适适应大小
	private boolean fitColumns = true;
	private boolean singleSelect=true;
	private boolean collapsible = true;
	//数据请求连接
	private String url;
	
	private String method = "post";
	//点击行记录
	private String onClickRow;
	
	private boolean checkbox = false;
	
	private List<DataGridColumn> dataGridColumns = new ArrayList<DataGridColumn>();
	
//	private List<DataGridColumnTag> dataGridColumns = new ArrayList<DataGridColumnTag>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isFitColumns() {
		return fitColumns;
	}

	public void setFitColumns(boolean fitColumns) {
		this.fitColumns = fitColumns;
	}

	public boolean isSingleSelect() {
		return singleSelect;
	}

	public void setSingleSelect(boolean singleSelect) {
		this.singleSelect = singleSelect;
	}

	public boolean isCollapsible() {
		return collapsible;
	}

	public void setCollapsible(boolean collapsible) {
		this.collapsible = collapsible;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

	public String getOnClickRow() {
		return onClickRow;
	}

	public void setOnClickRow(String onClickRow) {
		this.onClickRow = onClickRow;
	}

	public String getToolbar() {
		return toolbar;
	}

	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}

	public boolean isNowrap() {
		return nowrap;
	}

	public void setNowrap(boolean nowrap) {
		this.nowrap = nowrap;
	}

	public boolean isRownumbers() {
		return rownumbers;
	}

	public void setRownumbers(boolean rownumbers) {
		this.rownumbers = rownumbers;
	}

	public String getLoadMsg() {
		return loadMsg;
	}

	public void setLoadMsg(String loadMsg) {
		this.loadMsg = loadMsg;
	}

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public void addColumn(DataGridColumnTag dataGridColumnTag) {
		DataGridColumn column = new DataGridColumn();
		BeanUtils.copyProperties(column, dataGridColumnTag);
		dataGridColumns.add(column);
	}
	
//	public void addColumn(DataGridColumnTag columnTag) {
//		if (columnTag!=null) {
//			dataGridColumns.add(columnTag);
//		}
//	}
	
	public int doStartTag() throws JspTagException {
		// 清空资源
		dataGridColumns.clear();
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
		sb.append("<script type=\"text/javascript\">")
		  .append("$(function(){")
		  .append(addJs())
		  .append("});")
		  .append("</script>");
		sb.append("<table id=\""+id+"\" data-options=\"fit:true,border:false\"></table>");
		return sb.toString();
	}
	
	private String addJs() {
		StringBuffer js = new StringBuffer();
		js.append("$('#" + id + "').datagrid({");
		if (title != null) {
			js.append("title:'" + title + "',");
		}
		js.append("nowrap:false,")
				.append("fit:true,")
				.append("toolbar:'#" + toolbar + "',")
				.append("fitColumns:true,")
				.append("rownumbers:true,")
				.append("singleSelect:" + singleSelect + ",")
				.append("checkbox:" + checkbox + ",")
				.append("loadMsg:'加载中,请稍候...',")
				.append("queryParams:{time:" + System.currentTimeMillis()
						+ "},")
				.append("pagination:" + pagination + ",")
				.append("url:'" + url + "',").append("idField:'id',")
				.append("method:'post',")
				.append("sortOrder:'"+sortOrder+"',");
		if (StringUtils.isNotBlank(sortName)) {
			js.append("sortName:"+sortName+",");
		}
		
		if (pagination) {
			//如果有分页的话
			js.append("pageList:[30,50,100,200,500],");
		}
		if (checkbox) {
			js.append("onLoadSuccess:function(data){").append("if(data){")
					.append("$.each(data.rows, function(index, item){")
					.append("if(item.checked){")
					.append("$('#" + id + "').datagrid('checkRow', index);")
					.append("}").append("});").append("}").append("},");
		}
		if (onClickRow != null) {
			js.append("onClickRow:function(index, data){" + onClickRow + "},");
		}

		js.append("columns:[[")
		  .append(addColumns()).append("]],")
		  .append("onDbClickRow:null,").append("});");
		return js.toString();

	}

	private String addColumns() {
		// 创建列
		StringBuffer columns = new StringBuffer();
		columns.append("");
		for(DataGridColumn column:dataGridColumns) {
			if ("id".equals(column.getField())) {
				columns.append("{field:'"+column.getField()+"',checkbox:true},");
			}else {
				columns.append("{field:'"+column.getField()+"',hidden:"+column.isHidden()+",title:'"+column.getFieldName()+"',editor:"+column.getEditor()+",sortable:"+column.getSortable()+",styler:"+column.getStyler()+",width:"+column.getWidth());
				if (StringUtils.isNotEmpty(column.getFormatter())) {
					columns.append(",formatter:function(value,row,index){"+column.getFormatter()+"}");
				}
				columns.append("},");	
						
			}
			
		}
//		for(DataGridColumnTag column:dataGridColumns) {
//			if ("id".equals(column.getField())) {
//				columns.append("{field:'"+column.getField()+"',checkbox:true},");
//			}else {
//				columns.append("{field:'"+column.getField()+"',hidden:"+column.isHidden()+",title:'"+column.getFieldName()+"',editor:"+column.getEditor()+",sortable:"+column.isSortable()+",styler:"+column.getStyler()+",width:"+column.getWidth()+"},");
//			}
//			
//		}
		
		return columns.toString();
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	

}
