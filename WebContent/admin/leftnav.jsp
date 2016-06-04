<!DOCTYPE html>
<html lang="en">
<head>
  <title>Left nav</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="../css/bootstrap.min.css">
  <script src="../jquery-easyui-1.4.5/jquery.min.js"></script>
  <script src="../jquery-easyui-1.4.5/bootstrap.min.js"></script>
  <style>
   

 .navbar-fixed-left {
    width: 100%;
    position: fixed;
    border-radius: 0;
    height: 100%;
    background-color: #333;
    overflow: auto;
    
   
  }
   .navbar-fixed-left .navbar-nav > li {
    float: none; 
    width: 139px;
    padding: 10px 10px 10px 50px;
    text-align: center;
    
  }

  </style>
  
  <script type="text/javascript">
  $(function() {
	    $("ul.dropdown-menu").on("click", "[data-stopPropagation]", function(e) {
	        e.stopPropagation();
	    });
	});
    </script>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-left" >

  <ul class="nav navbar-nav" >
  
   <li>
	  <a href="${pageContext.request.contextPath }/admin/Categorymanage.jsp" target="right">Category</a> 
   </li>
   
   <li>
	<a href="${pageContext.request.contextPath }/admin/Usermanage.jsp" target="right">User</a> 
  </li>
   <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Book<span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu">
		 <li><a href="${pageContext.request.contextPath }/admin/book_addUI" target="right" onclick="event.stopPropagation()">Add Book</a></li>
	     <li><a href="${pageContext.request.contextPath }/admin/book_list" target="right"onclick="event.stopPropagation()">View Book</a><li>
			
  	    </ul>
  </li>
  <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Order<span class="caret"onclick="event.stopPropagation()"></span></a>
		<ul class="dropdown-menu" role="menu">
		  <li><a href="${pageContext.request.contextPath }/admin/order_get?state=false" target="right"onclick="event.stopPropagation()">Unconfirm Order</a></li>
		  <li><a href="${pageContext.request.contextPath }/admin/order_get?state=true" target="right"onclick="event.stopPropagation()">Confirm Order</a></li>
			
  	    </ul>
  </li>
   
    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Sale<span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu">
		   <li><a href="${pageContext.request.contextPath }/admin/Salebyuser.jsp" target="right"onclick="event.stopPropagation()">By User</a></li>
		   <li><a href="${pageContext.request.contextPath }/admin/SalebyCate.jsp" target="right"onclick="event.stopPropagation()">By Category</a></li>
		   <li class="divider"></li>
	       <li><a href="${pageContext.request.contextPath }/admin/Salebyyear.jsp" target="right"onclick="event.stopPropagation()">By Year</a></li>
		   <li><a href="${pageContext.request.contextPath }/admin/Salebymonth.jsp" target="right"onclick="event.stopPropagation()">By Month</a></li>
		   <li><a href="${pageContext.request.contextPath }/admin/Salebyday.jsp" target="right"onclick="event.stopPropagation()">By Day</a></li>
			
  	    </ul>
  </li>

 
<

</body>
</html>