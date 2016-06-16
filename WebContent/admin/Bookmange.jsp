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
            	url: 'book_get',
				saveUrl: 'book_update',
				updateUrl: 'book_update',
				destroyUrl: 'book_delete1'
            });
        });
    </script>
    <script type="text/javascript">
        function doSearch(){
            $('#dg').datagrid('load',{
            	url: 'book_search',
            	username: $('#username').val(),
            });
        }
    </script>
</head>
<body>

<h2>Book Manage</h2>
<div class="adb-info" style="margin-bottom:10px">
    <div class="adb-tip icon-tip">&nbsp;</div>
    <div>Double click the row to begin editing.</div>
</div>

<table id="dg" title="Users" style="width:1000px;height:350px"
       toolbar="#toolbar" pagination="true" idField="id" iconCls="icon-search"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
		 <th field="id" width="50"editor="text}">Book_ID</th>
         <th field="name" width="50"editor="{type:'validatebox',options:{required:true}}">Bookname</th>
         <th field="author" width="50"editor="text">Author</th>
          <th field="price" width="50"editor="text">price</th>
          <th field="image" width="50" editor="text">Image</th>
          <th field="description" width="50" editor="text">Description</th>
          <th field="category_id" width="50" editor="text">Category_id</th>
          <th field="number" width="50" editor="text">Number</th>
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