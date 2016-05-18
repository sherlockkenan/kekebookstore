<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>后台左侧导航</title>
    <style >
    	.menu{
    		display:none;
    		margin-left:10px;}
    		
    		body {
    		
    margin: 0;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 100%;
    background-color: #333;
    position: fixed;
    height: 100%;
    overflow: auto;
}

li a {
    display: block;
    color: #000;
    padding: 8px 0 8px 16px;
    text-decoration: none;
}

li a.active {
    background-color: #4CAF50;
    color: white;
}

li a:hover:not(.active) {
    background-color: #555;
    color: white;
}

    </style>
    <script type="text/javascript">
    	function test(e){
//  		$(e).style.display = $(e).style.display == 'block'? 'none':'block';
			var element = document.getElementById(e);
			element.style.display = element.style.display == 'block'? 'none':'block';
    	}
    </script>
  </head>
  <body>
  	<ul>
  	    <br/><br/>
  		<li>
		  	<a href="${pageContext.request.contextPath }/admin/Categorymanage.jsp" target="right">Category Manage</a> 
  		</li>
  		<br/><br/>
  		<li>
		  	<a href="#" onclick="test('menu1')">Book Manage
		  		<div id="menu1" class="menu">
				  	 <a href="${pageContext.request.contextPath }/admin/book?method=addUI" target="right">Add Book</a><br/>
				  	 <a href="${pageContext.request.contextPath }/admin/book?method=list" target="right">View Book</a>
			  	</div>
  			</a> 
  		</li>
  		<br/><br/>
  		<li>
		  	<a href="${pageContext.request.contextPath }/admin/Usermanage.jsp" target="right">User Manage

  			</a> 
  		</li>
  		<br/><br/>
  		<li>
		  	<a href="#" onclick="test('menu2')">Order Manage
		  		<div id="menu2" class="menu">
				  	 <a href="${pageContext.request.contextPath }/admin/order?method=get&state=false" target="right">Unconfirm Order</a><br/>
				  	 <a href="${pageContext.request.contextPath }/admin/order?method=get&state=true" target="right">Confirm Order</a>
				</div>
  			</a> 
  		</li>
  		<br/><br/>
  		<li>
		  	<a href="#" onclick="test('menu3')">Sale statistics
		  		<div id="menu3" class="menu">
				  	 <a href="${pageContext.request.contextPath }/admin/Salebyuser.jsp" target="right">By User</a><br/>
				  	 <a href="${pageContext.request.contextPath }/admin/SalebyCate.jsp" target="right">By Category</a><br/>
				  	 <a href="#" onclick="test('menu4')">By Time</a>
				  	      <div id="menu4" class="menu">
				  	         <a href="${pageContext.request.contextPath }/admin/Salebyyear.jsp" target="right">By Year</a><br/>
				  	         <a href="${pageContext.request.contextPath }/admin/Salebymonth.jsp" target="right">By Month</a><br/>
				  	         <a href="${pageContext.request.contextPath }/admin/Salebyday.jsp" target="right">By Day</a><br/>
				  	      </div>
				</div>
  			</a> 
  		</li>
  		<br/><br/>
  	</ul>
 
  </body>
</html>