<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../jquery-easyui-1.4.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../jquery-easyui-1.4.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../jquery-easyui-1.4.5/demo/demo.css">
    <script type="text/javascript" src="../jquery-easyui-1.4.5/jquery.min.js"></script>
    <script type="text/javascript" src="../jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../jquery-easyui-1.4.5/jquery.edatagrid.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#dg').edatagrid({
            	url: 'user_get',
				saveUrl: 'user_create',
				updateUrl: 'user_update',
				destroyUrl: 'user_delete'
            });
        });
    </script>
    <script type="text/javascript">
        function doSearch(){
            $('#dg').datagrid('load',{
            	username: $('#username').val(),
            });
        }
    </script>
</head>
<body>

<h2>User Manage</h2>
<div class="adb-info" style="margin-bottom:10px">
    <div class="adb-tip icon-tip">&nbsp;</div>
    <div>Double click the row to begin editing.</div>
</div>

<table id="dg" title="Users" style="width:1000px;height:350px"
       toolbar="#toolbar" pagination="true" idField="id" iconCls="icon-search"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
		 <th field="id" width="50"editor="text}">User_ID</th>
         <th field="username" width="50"editor="{type:'validatebox',options:{required:true}}">Username</th>
         <th field="password" width="50"editor="{type:'validatebox',options:{required:true}}">Password</th>
          <th field="phone" width="50"editor="text">Phone</th>
          <th field="email" width="50" editor="{type:'validatebox',options:{validType:'email'}}">Email</th>
          <th field="address" width="50" editor="text">Address</th>
          <th field="role" width="50" editor="text">Role</th>
	</tr>
    </thead>
</table>


<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">New</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">Destroy</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">Save</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">Cancel</a>
    <div style="float:right;">
         <input  id="username" class="easyui-searchbox" data-options="prompt:'Input username',searcher:''" style="width:130px;vertical-align:middle;"></input>
		<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
    </div>

</div>





</body>
</html>