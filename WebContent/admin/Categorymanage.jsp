
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
            	url: 'category?method=getall',
				saveUrl: 'category?method=create',
				updateUrl: 'category?method=update',
				destroyUrl: 'category?method=delete'
            });
        });
    </script>
    <script type="text/javascript">
        function doSearch(){
            $('#tt').datagrid('load',{
                title: $('#title').val(),
            });
        }
    </script>
</head>
<body>

<h2>Category manage</h2>
<div class="adb-info" style="margin-bottom:10px">
    <div class="adb-tip icon-tip">&nbsp;</div>
    <div>Double click the row to begin editing.</div>
</div>

<table id="dg" title="Users" style="width:1000px;height:500px"
       toolbar="#toolbar" pagination="true" idField="id"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
		 <th field="id" width="50"editor="text}">Category_id</th>
         <th field="name" width="50"editor="{type:'validatebox',options:{required:true}}">Name</th>
          <th field="description" width="50"editor="text">Description</th>
         
	</tr>
    </thead>
</table>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">New</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">Destroy</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">Save</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">Cancel</a>
</div>



