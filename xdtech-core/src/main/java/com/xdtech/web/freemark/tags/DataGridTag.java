/*
 * Project Name: feui
 * File Name: DataGridTag.java
 * Copyright: Copyright(C) 1985-2014 ZKTeco Inc. All rights reserved.
 */
package com.xdtech.web.freemark.tags;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;

import com.xdtech.common.utils.JsonUtil;
import com.xdtech.web.freemark.item.GridColumn;
import com.xdtech.web.freemark.item.OperationItem;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

/**
 * easyui datagrid 自定义标签
 * @author <a href="max.zheng@zkteco.com">郑志雄</>
 * @version 1.0.0
 * @since 2014-9-11 下午6:05:45
 */
public class DataGridTag extends EasyUiTag
{
	//	$(function() {
	//		$('#dg').datagrid({
	//				title:'freeMark结合easyui',
	//	            height:'auto', //高度 
	//	            pageSize: 30,//每页显示的记录条数，默认为10 
	//	            pageList: [10, 30, 50, 100],//可以设置每页记录条数的列表
	//				collapsible:false,
	//				iconCls:'icon-ok',
	//				fitColumns:false,
	//				resizeHandle:'right',
	//				rownumbers:false,
	//				pagination:true,
	//				loadMsg:'数据加载中，请稍等...',
	//			    url:'test.do?loadGridDatas',
	//			    fit: true,
	//			    columns:[[
	//			    		{field:'name',title:'名称'},
	//			    		{field:'sex',title:'性别'},
	//			    		{field:'address',title:'地址'}
	//			    ]]
	//			});
	//		$('#dg').datagrid('getPager').pagination({ 
	//		     beforePageText: '第',//页数文本框前显示的汉字  
	//	         afterPageText: '页    共 {pages} 页',  
	//	         displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	//		});
	//	});

	/*
	 * (非 Javadoc)
	 * render
	 * <p>对override方法的补充说明，若不需要则删除此行</p>
	 * 
	 * @param env
	 * 
	 * @param params
	 * 
	 * @param body
	 * 
	 * @throws IOException
	 * 
	 * @throws TemplateException
	 * 
	 * @see com.zzx.freemark.tag.SecureTag#render(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateDirectiveBody)
	 */
	@Override
	public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException
	{

		try
		{
			String className = params.get("item").toString();
			boolean pagination = params.get("pagination") != null ? Boolean.valueOf(params.get("pagination").toString()) : true;
			
			String tableId = params.get("id") == null ? UUID.randomUUID().toString() : params.get("id").toString();
			StringBuffer sb = new StringBuffer("<table id=\"" + tableId + "\" data-options=\"border:false\"></table>");
			sb.append("<script type=\"text/javascript\">")
					.append("var "+tableId+" = null;")
					.append("$(function() {")
					.append(tableId+" = $('#"+tableId+"');")
					.append(tableId+".datagrid({")
					.append(addProperties("url", params.get("url"), true))
					.append(addProperties("height", "auto", true))
					.append(addProperties("title",params.get("title"),true))
					.append(addProperties("striped", "false", true))
					.append(addProperties("fit", "true", false))
					.append(addProperties("selectOnCheck", params.get("selectOnCheck")!= null ? params.get("selectOnCheck"):false, false))//勾选checkbox自动选中当前行，默认是false
					.append(addProperties("checkOnSelect", params.get("checkOnSelect")!= null ? params.get("checkOnSelect"):false, false))//选中当前行自动勾选checkbox，默认是false
					.append(addProperties("toolbar", params.get("toolbar"), true))
					.append(addProperties("loadMsg", "数据加载中，请稍等...", true))
					.append(addProperties("queryParams", "{time : new Date().getTime()}", false))
					.append(addProperties("rownumbers", params.get("rownumbers")!= null ? params.get("rownumbers"):true, false))
					.append(addProperties("singleSelect", params.get("singleSelect")!= null ? params.get("singleSelect"):true, false))
					.append(addProperties("checkbox", params.get("checkbox"), false))
					.append(addProperties("idField", params.get("idField")!= null ? params.get("idField"):"id", true))
					.append(addEvent(params))
					.append(createPagination(pagination))
					.append(createColumns(className,params.get("idField")!=null,params.get("operations")))
					.append("});");
			if (pagination)
			{
				sb.append("$('#"+tableId+"').datagrid('getPager').pagination({")
				  .append("beforePageText: '第',") //页数文本框前显示的汉字  
			      .append("afterPageText: '页    共 {pages} 页',")
			      .append("displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',")
			      .append("});");   
			}
			//添加列表默认刷新方法
			sb.append("function "+tableId+"_refresh() { tableId.datagrid('reload'); }");
			
			
			sb.append("});");
			sb.append("</script>");
			// 真正开始处理输出内容
			writeBody(env, sb.toString());
		}
		catch (Exception e)
		{
			
		}

	}

	

	/**
	 * 添加事件相关操作
	 * @author max.zheng
	 * @create 2014-9-29下午9:23:33
	 * @modified by
	 * @param params
	 * @return
	 */
	private String addEvent(Map params) {
		StringBuffer sb = new StringBuffer();
		if (params.get("onClickRow")!=null) {
			sb.append("onClickRow: function(rowIndex, rowData){"+params.get("onClickRow")+";},");
		}
		return sb.toString();
	}



	private String createPagination(boolean pagination)
	{
		StringBuffer sb = new StringBuffer();
		if (pagination)
		{
			sb.append("pageSize: 30,") 				//每页显示的记录条数，默认为10
			.append("pageList:[10, 30, 50, 100],") //可以设置每页记录条数的列表
			.append("pagination:true,");
		}
		return sb.toString();
	}

	private String createColumns(String className,boolean showChecked,Object operations) throws ClassNotFoundException
	{
		Class itemCls = Class.forName(className);
		Field[] fields = itemCls.getDeclaredFields();
		StringBuffer sb = new StringBuffer("columns:[[");
		if (showChecked) {
			sb.append("{field : 'id',checkbox : true},");
		}
		
		for (Field field : fields)
		{
			GridColumn gridColumn = field.getAnnotation(GridColumn.class);
			if (gridColumn != null)
			{
				sb.append("{field:'" + field.getName() + "',width:"+gridColumn.width()+",title:'" + gridColumn.title()+"',");
				if (gridColumn.formatter()!=null&&gridColumn.formatter().length>0) {
					sb.append("formatter:function(value, row, index) {");
					for (int i = 0; i < gridColumn.formatter().length; i++) {
						String kv = gridColumn.formatter()[i];
						String[] kval = kv.split("=",2);
//						kval[1] = kval[1].replaceAll("", replacement)
						if (i==0) {
							sb.append("if ((value+'')=='"+kval[0]+"') { return '"+kval[1]+"'; }"); 
						}else if (showChecked) {
							sb.append("else if ((value+'')=='"+kval[0]+"') { return '"+kval[1]+"'; }"); 
						}else {
							sb.append("else { return '--';}");
						}
					
					}
					sb.append("},");
				}
				sb.append("},");
			}
		}
		
		if (operations!=null&&!operations.toString().trim().equals("")) {
			List<OperationItem> items = JsonUtil.getDTOList(operations.toString(), OperationItem.class);
			StringBuffer operationItems = new StringBuffer("var rowId = row.id;var str = '';str +='");
			for (OperationItem item : items) {
//				<img  onclick=\"lookUserInfo('+rowId+')\" src=\"plugins/xdtech/images/notes/note.png\" title=\"查看\"/>
				//权限过滤
				if (StringUtils.isEmpty(item.getShiro())||(SecurityUtils.getSubject().isPermitted(item.getShiro()))) {
					operationItems.append("<img  onclick=\""+item.getOnClick()+"('+rowId+')\" src=\""+item.getSrc()+"\" title=\""+item.getTitle()+"\"/>&nbsp;");
				}
			}
			operationItems.append("';return str;");
			sb.append("{field :'action',title : '操作',width : 200,formatter : function(value, row, index) {"+operationItems+"}},");
		}
		sb.append("]],");
		return sb.toString();
	}

}
