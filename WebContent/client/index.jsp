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
      body,
      input,
      button {
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
      }
      .container { width: 800px; text-align: right; }

      input[type=text],
      input[type=password] {
        font-size: 13px;
        min-height: 32px;
        margin: 0;
        padding: 7px 8px;
        outline: none;
        color: #333;
        background-color: #fff;
        background-repeat: no-repeat;
        background-position: right center;
        border: 1px solid #ccc;
        border-radius: 3px;
        box-shadow: inset 0 1px 2px rgba(0,0,0,0.075);
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        transition: all 0.15s ease-in;
        -webkit-transition: all 0.15s ease-in 0;
        vertical-align: middle;
      }
      .button {
        position: relative;
        display: inline-block;
        margin: 0;
        padding: 8px 15px;
        font-size: 13px;
        font-weight: bold;
        color: #333;
        text-shadow: 0 1px 0 rgba(255,255,255,0.9);
        white-space: nowrap;
        background-color: #eaeaea;
        background-image: -moz-linear-gradient(#fafafa, #eaeaea);
        background-image: -webkit-linear-gradient(#fafafa, #eaeaea);
        background-image: linear-gradient(#fafafa, #eaeaea);
        background-repeat: repeat-x;
        border-radius: 3px;
        border: 1px solid #ddd;
        border-bottom-color: #c5c5c5;
        box-shadow: 0 1px 3px rgba(0,0,0,.05);
        vertical-align: middle;
        cursor: pointer;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        -webkit-touch-callout: none;
        -webkit-user-select: none;
        -khtml-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        -webkit-appearance: none;
      }
      .button:hover,
      .button:active {
        background-position: 0 -15px;
        border-color: #ccc #ccc #b5b5b5;
      }
      .button:active {
        background-color: #dadada;
        border-color: #b5b5b5;
        background-image: none;
        box-shadow: inset 0 3px 5px rgba(0,0,0,.15);
      }
      .button:focus,
      input[type=text]:focus,
      input[type=password]:focus {
        outline: none;
        border-color: #51a7e8;
        box-shadow: inset 0 1px 2px rgba(0,0,0,.075), 0 0 5px rgba(81,167,232,.5);
      }
     
      label[for=search] {
        display: block;
        text-align: left;
      }
      #search label {
        font-weight: 200;
        padding: 5px 0;
      }
      #search input[type=text] {
        font-size: 18px;
        width: 705px;
      }
      #search .button {
        padding: 10px;
        width: 90px;
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
   
     <form class="container" action="${pageContext.request.contextPath }/client/index_searchbook" method="post" class="form-wrapper cf">
	  <input type="text" name="book">
    <input class="button" type="submit" value="Search">
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
