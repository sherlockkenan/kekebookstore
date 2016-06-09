<%@ page language="java" import="java.util.*,entity.*,service.Category_service,service.Book_service" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
   if(request.getAttribute("categories")==null){
	   response.sendRedirect("index");
   }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>Foreground</title>
   <link rel="stylesheet" href="../css/bootstrap.min.css">
  <script src="../jquery-easyui-1.4.5/jquery.min.js"></script>
  <script src="../jquery-easyui-1.4.5/bootstrap.min.js"></script>
  
    <style>
 .navbar-fixed-left {
    width: 200px;
    position: fixed;
    border-radius: 0;
    height: auto;
    background-color: #333;
    overflow: auto;
    margin-left: -150px;
    margin-top: 10px;
   
  }
   .navbar-fixed-left .navbar-nav > li {
    float: none; 
    width: auto;
    padding: 10px 10px 10px 40px;
    text-decoration: none;
   
    
  }

  </style>
  
  
  
  </head>
  
  <body style="text-align:center;">
    <script type="text/javascript">
		var xmlHttpRequest = null;
		function ajaxRequest(id) {
			if (window.ActiveXObject) {
				xmlHttpRequest = new ActionXObject("Microsoft.XMLHTTP");
			} else if (window.XMLHttpRequest) {
				xmlHttpRequest = new XMLHttpRequest();
			}

			if (xmlHttpRequest != null) {

				var infoMsg = new Object();
				infoMsg.id = id;
				var jsonstr = JSON.stringify(infoMsg);

	            xmlHttpRequest.open("POST","index_detail",true);
	            xmlHttpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	            xmlHttpRequest.onreadystatechange = ajaxCall; 
	            xmlHttpRequest.send("content="+jsonstr);
			
			}
		}

		function ajaxCall() {
			if (xmlHttpRequest.readyState == 4) {
				if (xmlHttpRequest.status == 200) {
					var rs="";
					var json = xmlHttpRequest.responseText;
					var obj = eval("(" + json +")"); 
					console.log(obj["id"]);
					rs="<div  style='height:150; margin-top:20px;'>"
					 +"<div id='image' style='float:left;'>"
					+"<img src='${pageContext.request.contextPath }/images/"+obj["image"]+ " 'height=150 width=100></div>"
					+"<div style='float:left; text-align:left;''>"
						+"<ul><li>"+obj["name"]
							+"</li>"
							+"<li>作者："+obj["author"]+"</li>"
							+"<li>售价："+obj["price"]+"</li>"
							+"<li><a href='${pageContext.request.contextPath }/client/cart_add?bookid="+obj["id"]+"'>加入购物车</a></li>"
							+"<li>详情："+obj["description"]+"</li></ul></div>"
					document.getElementById("bookandpage").innerHTML = rs;
				}
			}
		}
	</script>
    
    <div id="header">
            <%@include file="/client/head.jsp" %>  
    </div>
   
     <form action="${pageContext.request.contextPath }/client/index_searchbook" method="post" class="form-wrapper cf">
	  <input  type="text" name="book" placeholder="Search here..." required>
	  <button type="submit">Search</button>
    </form>
    
    <div id="content" style="margin:0 auto;width:840px;">
    	<div class="navbar navbar-inverse navbar-fixed-left" >
          
          <ul class="nav navbar-nav" >
           <li><a href="#" style=" font-size: 20px;">Catergory</a>
           <c:forEach var="category" items="${categories }">
    	  <li>
	       <a href="${pageContext.request.contextPath }/client/index_Category?category_id=${category.id}">${category.name}</a>
	       </li>
	    </c:forEach>
	    </ul>
  
    </div>	
    	
    	<div id="bookandpage" style="float:left; position:absolute; top: 150px;margin-left:300px;margin-top:20px;">   		
    		<div id="books">
    			<c:forEach var="book" items="${page.list }">
    				<div id="book"  style="height:150; margin-top:20px;">
    					<div id="image" style="float:left;">
    						<img src="${pageContext.request.contextPath }/images/${book.image}" height=150 width=100>
    					</div>
    					<div id="bookinfo" style="float:left; text-align:left;">
    				
    						<ul>
    							<li style="margin-bottom:10px">
    							<a  href="javascript:void(0);" onclick="ajaxRequest('${book.id}')">${book.name }</a>
    							</li>
    							
    							<li style="margin-bottom:10px">售价：${book.price }</li>
    					
    							<li style="margin-bottom:10px">
    								<a href="${pageContext.request.contextPath }/client/cart_add?bookid=${book.id}">加入购物车</a>
    							</li>
    						</ul>
    					</div>
    				</div>
    				<div style="clear:both"></div>
    			</c:forEach>
    		</div>
    		<div style="clear:both"></div>
    		<div id="page" style="margin-top:20px; text-align:center;">
    			当前第[${page.pagenum }]页 &nbsp;&nbsp;
			    <c:forEach var="pagenum" begin="${page.startpage }" end="${page.endpage }">
			    	[<a href="${pageContext.request.contextPath }/client/index?pagenum=${pagenum}&category_id=${param.category_id}">${pagenum }</a>]
			    </c:forEach>
			    &nbsp;&nbsp;
			    总共[${page.totalpage }]页，共[${page.totalrecord }]条记录
    		</div>
    	</div>
    </div>
  </body>
</html>
