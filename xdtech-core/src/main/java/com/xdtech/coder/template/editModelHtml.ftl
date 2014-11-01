<#macro elementType field>
	<#switch field.type>
		<#case "String">
			<input name="${field.name}"  class="easyui-validatebox" data-options="required: ${field.formField?c},value: '<#noparse>${</#noparse>(${modelName}Item.${field.name})<#noparse>!}</#noparse>', width: 200" />
			<#break>
		<#case "Long">
			<input name="${field.name}"  class="easyui-numberbox" data-options="required: ${field.formField?c},value: '<#noparse>${</#noparse>(${modelName}Item.${field.name})<#noparse>!}</#noparse>', width: 200" />
			<#break>
		<#case "Date">
			<input name="${field.name}"  class="easyui-datetimebox" data-options="required: ${field.formField?c},value: '<#noparse>${</#noparse>(${modelName}Item.${field.name})<#noparse>!}</#noparse>', width: 200" />
			<#break>
		<#default>
			<input name="${field.name}"  class="easyui-validatebox" data-options="required: ${field.formField?c},value: '<#noparse>${</#noparse>(${modelName}Item.${field.name})<#noparse>!}</#noparse>', width: 200" />
	</#switch>
	<#compress></#compress>
</#macro>
<table cellpadding="5">
	<input name="id" type="hidden" value="<#noparse>${</#noparse>(${modelName}Item.id)<#noparse>!}</#noparse>" />
	<#list fields as field>
	<#if field.name='id'>
	<#else>
		<#if field.formField>
	<tr>
		<td>${field.showName}:</td>
		<td>
			<@elementType field=field />
		</td>
	</tr>
		</#if>
	</#if>
</#list>
</table>
