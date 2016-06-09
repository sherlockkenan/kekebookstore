<html>
<head>
	<title style="text-align:center;">>Bookstore User Manage</title>
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
            	search: "salestatistic_search",
            	start_year: $('#start_year').val(),
            	start_month: $('#start_month').val(),
            	start_day: $('#start_day').val(),
            	end_year: $('#end_year').val(),
            	end_month: $('#end_month').val(),
            	end_day: $('#end_day').val(),
            });
        }
    </script>
    
</head>

<body>

<h1>Orders Statics By Day</h1>

<table id="dg" title="My Orders" class="easyui-datagrid"
       url="salestatistic_byday"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
	<thead>
	<tr>
		<th field="date" width="50">Day</th>
		<th field="num" width="50">Sum of order</th>
		
	</tr>
	</thead>
</table>

<div id="toolbar">
    
    <div style="float:right;">
         <div>start_date</div>
         <input  id="start_year" class="easyui-searchbox" data-options="prompt:'start_year',searcher:''" style="width:100px;vertical-align:middle;"></input>
         <input  id="start_month" class="easyui-searchbox" data-options="prompt:'start_month',searcher:''" style="width:100px;vertical-align:middle;"></input>
         <input  id="start_day" class="easyui-searchbox" data-options="prompt:'start_day',searcher:''" style="width:100px;vertical-align:middle;"></input>
         <div>end_date</div>
         <input  id="end_year" class="easyui-searchbox" data-options="prompt:'end_year',searcher:''" style="width:100px;vertical-align:middle;"></input>
         <input  id="end_month" class="easyui-searchbox" data-options="prompt:'end_month',searcher:''" style="width:100px;vertical-align:middle;"></input>
         <input  id="end_day" class="easyui-searchbox" data-options="prompt:'end_day',searcher:''" style="width:100px;vertical-align:middle;"></input>
        
		<a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
    </div>

</div>
</body>
</html>