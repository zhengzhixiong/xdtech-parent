<script type="text/javascript">
	function doSearch${modelName?cap_first}(value) {
		
	}
	
	//表格查询  
	function reload${modelName?cap_first}() {
		var params = ${modelName}_list.datagrid('options').queryParams; //先取得 datagrid 的查询参数  
		printLog(params);
		${modelName}_list.datagrid('reload'); //设置好查询参数 reload 一下就可以了  
		${modelName}_list.datagrid('unselectAll');
		${modelName}_list.datagrid('uncheckAll');
	}


	function add${modelName?cap_first}() {
		createFromWindow("新增信息", "${modelName}.do?edit${modelName?cap_first}", "${modelName}.do?save${modelName?cap_first}",
				function(data) {
					reload${modelName?cap_first}();
				});
	}
	
	function delete${modelName?cap_first}() {
		var rows = ${modelName}_list.datagrid('getChecked');
	    if (rows&&rows.length>0){
	        $.messager.confirm('询问','你确认要删除吗?',function(r){
	            if (r){
	            	var ids = "";
	            	for(var i=0; i<rows.length; i++){
	            		ids += rows[i].id+",";
	            	}
	                $.post('${modelName}.do?delete${modelName?cap_first}Items',{ids:ids},function(result){
	                    if (result.success){
	                    	reload${modelName?cap_first}();    
	                    }
	                    showMsg(result.msg);
	                },'json');
	            }
	        });
	    }else {
	    	showMsg("请选择操作记录.");
	    }
	}

	function edit${modelName?cap_first}() {
		var rows = ${modelName}_list.datagrid('getChecked');
		printLog(rows);
		if (rows&&rows.length>0) {
			if(rows.length>1) {
				showMsg('请选择一条操作记录.');
				return;
			}
			createFromWindow("编辑信息", "${modelName}.do?edit${modelName?cap_first}&${modelName}Id=" + rows[0].id,
					"${modelName}.do?save${modelName?cap_first}", function(data) {
						reload${modelName?cap_first}();
					},true);
		} else {
			showMsg('请选择操作记录.');
		}

	}
	
	
	//操作按钮触发
	function operationsLook${modelName?cap_first}(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = ${modelName}_list.datagrid('getSelections');
			id = rows[0].id;
		}
		if (id) {
			createFromWindow("查看信息", "${modelName}.do?edit${modelName?cap_first}&${modelName}Id=" + id,
					null, null,false);
		} else {
			showMsg('请选择查看记录.');
		}
	}
	
	function operationsEdit${modelName?cap_first}(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = ${modelName}_list.datagrid('getSelections');
			id = rows[0].id;
		}
		if (id) {
			createFromWindow("编辑信息", "${modelName}.do?edit${modelName?cap_first}&${modelName}Id=" + id,
					"${modelName}.do?save${modelName?cap_first}", function(data) {
						reload${modelName?cap_first}();
					},true);
		} else {
			showMsg('请选择编辑记录.');
		}
	}
	
	function operationsSend${modelName?cap_first}() {
		printLog('发送');
	}
	
	function operationsDelete${modelName?cap_first}(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = new_list.datagrid('getSelections');
			id = rows[0].id;
		}
		if (id) {
			$.messager.confirm('询问', '你确认要删除吗?', function(r) {
				if (r) {
					$.post('${modelName}.do?delete${modelName?cap_first}Items', {
						ids : id
					}, function(result) {
						if (result.success) {
							reload${modelName?cap_first}(); 
						}
						showMsg(result.msg);
					}, 'json');
				}
			});
		} else {
			showMsg("请选择操作记录.");
		}
	}
</script>

<#noparse><#assign</#noparse> operations = "[\{shiro:'${moduleName}:${modelName}:refresh',onClick:'operationsLook${modelName?cap_first}',src:'plugins/xdtech/images/notes/note.png',title:'查看'},
	   					\{shiro:'${moduleName}:${modelName}:edit',onClick:'operationsEdit${modelName?cap_first}',src:'plugins/xdtech/images/notes/note_edit.png',title:'编辑'},
	   					\{shiro:'${moduleName}:${modelName}:send',onClick:'operationsSend${modelName?cap_first}',src:'plugins/xdtech/images/notes/note_go.png',title:'发送'},
	   					\{shiro:'${moduleName}:${modelName}:delete',onClick:'operationsDelete${modelName?cap_first}',src:'plugins/xdtech/images/notes/note_delete.png',title:'删除'}
	  				   ]"/>

<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		
		<<#noparse>@</#noparse>eu.datagrid id="${modelName}_list" toolbar="#${modelName}list_search" idField="id" operations="<#noparse>${operations!}</#noparse>"
			 url="${modelName}.do?loadList" rownumbers="true"
			 item="com.xdtech.${moduleName}.vo.${modelName?cap_first}Item"/>
		
		<div id="${modelName}list_search">
			<div id="${modelName}_toolbar" style="height: auto">
				
				<<#noparse>@</#noparse>eu.linkbutton showName="新增" shiro="${moduleName}:${modelName}:add" iconCls="icon-add" onclick="add${modelName?cap_first}()"/>
				<<#noparse>@</#noparse>eu.linkbutton showName="删除" shiro="${moduleName}:${modelName}:delete" iconCls="icon-remove" onclick="delete${modelName?cap_first}()"/>
				<<#noparse>@</#noparse>eu.linkbutton showName="编辑" shiro="${moduleName}:${modelName}:edit" iconCls="icon-edit" onclick="edit${modelName?cap_first}()"/>
				<<#noparse>@</#noparse>eu.linkbutton showName="刷新" shiro="${moduleName}:${modelName}:refresh" iconCls="icon-reload" onclick="reload${modelName?cap_first}()"/>
				
			</div>
		</div>
	</div>
</div>