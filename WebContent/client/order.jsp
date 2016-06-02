
 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>客户端显示订单</title>
  </head>
   <div id="header">
            <%@include file="/client/head.jsp" %>     
   </div>
   
  
  <body style="text-align:center;">
  <c:if test="${user == null }">
  		login first
  </c:if>
  <c:if test="${user != null }">
    <h2>Order List</h2>
    <table width="60%" border="1" align="center" style="text-align: center;">
    	<tr>
    		<td>订单号</td>
    		<td>订单人</td>
    		<td>订单时间</td>
    		<td>订单总价</td>
    		<td>订单状态</td>
    		<td>操作</td>
    	</tr>
    	<c:forEach var="order" items="${orders }">
    		<tr>
    			<td>${order.id }</td>
	    		<td>${order.user.username }</td>
	    		<td>${order.ordertime }</td>
	    		<td>${order.price }</td>
	    		<td>${order.state==true?'Confirm':'Uncomfirm' }</td>
	    		<td>
	    			<a href="${pageContext.request.contextPath }/client/order_detail?order_id=${order.id}">More</a>
	    		</td>
    		</tr>
    	</c:forEach>  	
	</table>
	  </c:if>
  </body>

</html>
     
</body>
</html>