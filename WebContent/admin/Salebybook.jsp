<html>
<head>
	<title>Bookstore Sale Manage</title>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link rel="stylesheet" type="text/css" href="../jquery-easyui-1.4.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../jquery-easyui-1.4.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../jquery-easyui-1.4.5/demo/demo.css">
    <script type="text/javascript" src="../jquery-easyui-1.4.5/jquery.min.js"></script>
    <script type="text/javascript" src="../jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../jquery-easyui-1.4.5/jquery.edatagrid.js"></script>
    
    <script type="text/javascript">
        function doSearch(){
            $('#dg').datagrid('load',{
            	search: 'book_search',
            	bookname: $('#bookname').val(),
            });
        }
    </script>
</head>

<body>

<h1>Orders Statics By Book</h1>

<table id="dg" title="My Orders" class="easyui-datagrid"
       url="salestatistic_bybook"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
	<thead>
	<tr>
		<th field="id" width="50"> Book_id</th>
		<th field="name" width="50">name</th>
		<th field="num" width="50">Sum of order</th>
		
	</tr>
	</thead>
</table>
<div id="toolbar">
    <div style="float:right;">
         <input  id="bookname" class="easyui-searchbox" data-options="prompt:'Input bookname',searcher:''" style="width:130px;vertical-align:middle;"></input>
		<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
    </div>

</div>
</body>
</html>