<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>Order detail </title>
  </head>
  
  <body style="text-align:center;">
   <div id="header">
            <%@include file="/client/head.jsp" %>     
   </div>
  	<h3>Oreder detial</h3>
    <table border="1" width="50%" align="center" style="text-align: center;">
    	<tr>
    		<td>Bookname</td>
    		<td>Price</td>
    		<td>Quantity</td>
    		<td>Subtotal</td>
    	</tr>
    	<c:forEach var="orderitem" items="${order.orderitems }">
    	<tr>
    		<td>${orderitem.book.name }</td>
    		<td>${orderitem.book.price }</td>
    		<td>${orderitem.quantity }</td>
    		<td>${orderitem.price }</td>
    	</tr>
    	</c:forEach>
    	
    	<tr>
    		<td>Total</td>
    		<td colspan="3">${order.price }</td>
    	</tr>
    	
    </table>
    
    <h3>User Information</h3>
    <table border="1" width="50%" align="center" style="text-align: center;">  
    	<tr>
    		<td>User</td>
    		<td>Phone</td>
    		<td>Address</td>
    		<td>Email</td>
    	</tr>
    	<tr>
    		<td>${order.user.username }</td>
    		<td>${order.user.phone }</td>   	
    		<td>${order.user.address }</td>
    		<td>${order.user.email }</td>
    	</tr>
	</table>
  </body>
</html>
