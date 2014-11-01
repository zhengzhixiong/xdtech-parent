<script type="text/javascript">
	function doSearch${modelName?cap_first}(value) {
		
	}

	//表格查询  
	function search${modelName?cap_first}() {
		var params = ${modelName}_list.datagrid('options').queryParams; //先取得 datagrid 的查询参数  
		printLog(params);
		${modelName}_list.datagrid('reload'); //设置好查询参数 reload 一下就可以了  
	}

	function add${modelName?cap_first}() {
		createFromWindow("新增消息信息", "${modelName}.do?edit${modelName?cap_first}", "${modelName}.do?save${modelName?cap_first}",
				function(data) {
					search${modelName?cap_first}();
				});
	}

	function edit${modelName?cap_first}() {
		var row = ${modelName}_list.datagrid('getSelected');
		if (row) {
			createFromWindow("编辑消息信息", "${modelName}.do?edit${modelName?cap_first}&${modelName}Id=" + row.id,
					"${modelName}.do?save${modelName?cap_first}", function(data) {
						search${modelName?cap_first}();
					});
		} else {
			showMsg('请选择编辑记录.');
		}

	}
	
	function delete${modelName?cap_first}(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = ${modelName}_list.datagrid('getSelections');
			id = rows[0].id;
		}
		if (id) {
			$.messager.confirm('询问', '你确认要删除消息吗?', function(r) {
				if (r) {
					$.post('${modelName}.do?delete${modelName?cap_first}', {
						id : id
					}, function(result) {
						console.log(result);
						if (result.success) {
							${modelName}_list.datagrid('reload'); // reload the user data
						}
						showMsg(result.msg);
					}, 'json');
				}
			});
		} else {
			showMsg("请选择操作记录.");
		}
	}

	function deleteSelect${modelName?cap_first}(){
	    var row = ${modelName}_list.datagrid('getSelected');
	    if (row){
	        $.messager.confirm('询问','你确认要删除消息吗?',function(r){
	            if (r){
	                $.post('${modelName}.do?delete${modelName?cap_first}',{id:row.id},function(result){
	                	console.log(result);
	                    if (result.success){
	                        ${modelName}_list.datagrid('reload');    // reload the user data
	                    }
	                    showMsg(result.msg);
	                },'json');
	            }
	        });
	    }else {
	    	showMsg("请选择操作记录.");
	    }
	}
</script>
<#noparse>
<#assign operations = "[\{shiro:'message:${modelName}:refresh',onClick:'look${modelName?cap_first}',src:'plugins/xdtech/images/notes/note.png',title:'查看'},
	   					\{shiro:'message:${modelName}:edit',onClick:'edit${modelName?cap_first}',src:'plugins/xdtech/images/notes/note_edit.png',title:'编辑'},
	   					\{shiro:'message:${modelName}:send',onClick:'send${modelName?cap_first}',src:'plugins/xdtech/images/notes/note_go.png',title:'发送'},
	   					\{shiro:'message:${modelName}:delete',onClick:'delete${modelName?cap_first}',src:'plugins/xdtech/images/notes/note_delete.png',title:'删除'}
	  				   ]"/>
</#noparse>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false">
		
		<<#noparse>@</#noparse>eu.datagrid id="${modelName}_list" title="消息列表" toolbar="#${modelName}list_search" idField="id" operations="<#noparse>${operations!}</#noparse>"
			 url="${modelName}.do?loadList" rownumbers="true"
			 item="com.xdtech.message.vo.${modelName?cap_first}Item"/>
		
		<div id="${modelName}list_search">
			<div id="${modelName}_toolbar" style="height: auto">
				
				<<#noparse>@</#noparse>eu.linkbutton showName="新增" shiro="message:${modelName}:add" iconCls="icon-add" onclick="add${modelName?cap_first}()"/>
				<<#noparse>@</#noparse>eu.linkbutton showName="删除" shiro="message:${modelName}:delete" iconCls="icon-remove" onclick="deleteSelect${modelName?cap_first}()"/>
				<<#noparse>@</#noparse>eu.linkbutton showName="编辑" shiro="message:${modelName}:edit" iconCls="icon-edit" onclick="edit${modelName?cap_first}()"/>
				<<#noparse>@</#noparse>eu.linkbutton showName="刷新" shiro="message:${modelName}:refresh" iconCls="icon-reload" onclick="search${modelName?cap_first}()"/>
				
			</div>
		</div>
	</div>
</div>