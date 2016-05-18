<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>显示订单</title>
  </head>
  
  <body style="text-align:center;">
    <h2>订单列表</h2>
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
	    		<td>${order.state==true?'Confirm':'Unconfirm' }</td>
	    		<td>
	    			<a href="${pageContext.request.contextPath }/admin/order?method=detail&order_id=${order.id}">View more</a>
	    			<a href="${pageContext.request.contextPath }/admin/order?method=delete&order_id=${order.id}">Delete</a>
	    		</td>
    		</tr>
    	</c:forEach>
    	
	</table>
  </body>
</html>
